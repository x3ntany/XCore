package me.xentany.xcore.util.hook.luckperms

import net.luckperms.api.LuckPerms
import net.luckperms.api.model.user.User
import net.luckperms.api.node.types.InheritanceNode
import net.luckperms.api.node.types.MetaNode
import net.luckperms.api.node.types.PermissionNode
import org.bukkit.OfflinePlayer
import java.time.Duration
import java.util.UUID
import kotlin.text.get

class LuckPermsUser(
  val user: User,
  val api: LuckPerms
) {

  val uniqueId: UUID = this.user.uniqueId

  fun hasPermission(node: String): Boolean =
    this.user.cachedData.permissionData.checkPermission(node).asBoolean()

  fun addPermission(node: String): Boolean {
    val permissionNode = PermissionNode.builder(node).value(true).build()
    val changed = this.user.data().add(permissionNode)

    this.api.userManager.saveUser(this.user)

    return changed.wasSuccessful()
  }

  fun removePermission(node: String): Boolean {
    val permissionNode = PermissionNode.builder(node).build()
    val changed = this.user.data().remove(permissionNode)

    this.api.userManager.saveUser(this.user)

    return changed.wasSuccessful()
  }

  fun getGroups(): Set<String> =
    this.user.nodes
      .filterIsInstance<InheritanceNode>()
      .mapTo(mutableSetOf()) { it.groupName }

  fun addGroup(group: String): Boolean {
    val inheritanceNode = InheritanceNode.builder(group).build()
    val changed = this.user.data().add(inheritanceNode)

    this.api.userManager.saveUser(this.user)

    return changed.wasSuccessful()
  }

  fun removeGroup(group: String): Boolean {
    val inheritanceNode = InheritanceNode.builder(group).build()
    val changed = this.user.data().remove(inheritanceNode)

    this.api.userManager.saveUser(this.user)

    return changed.wasSuccessful()
  }

  fun addGroupTemporary(group: String, duration: Duration): Boolean {
    val inheritanceNode = InheritanceNode.builder(group).expiry(duration).build()
    val changed = this.user.data().add(inheritanceNode)

    this.api.userManager.saveUser(this.user)

    return changed.wasSuccessful()
  }

  fun getPrefix(): String? = this.user.cachedData.metaData.prefix
  fun getSuffix(): String? = this.user.cachedData.metaData.suffix
  fun getMeta(key: String): String? = this.user.cachedData.metaData.getMetaValue(key)

  fun setMeta(key: String, value: String): Boolean {
    val metaNode = MetaNode.builder(key, value).build()
    val changed = this.user.data().add(metaNode)
    this.api.userManager.saveUser(this.user)
    return changed.wasSuccessful()
  }

  fun removeMeta(key: String): Boolean {
    val nodesToRemove = this.user.nodes
      .filterIsInstance<MetaNode>()
      .filter { it.metaKey == key }
    var changed = false

    for (metaNode in nodesToRemove) {
      val result = this.user.data().remove(metaNode)

      changed = changed || result.wasSuccessful()
    }

    this.api.userManager.saveUser(this.user)

    return changed
  }

  companion object {

    fun load(uuid: UUID, callback: (LuckPermsUser?) -> Unit) {
      val api = LuckPermsProvider.get() ?: return callback(null)

      api.userManager.loadUser(uuid).thenAccept { user ->
        callback(if (user == null) null else LuckPermsUser(user, api))
      }
    }

    fun getIfLoaded(uuid: UUID): LuckPermsUser? {
      val api = LuckPermsProvider.get() ?: return null
      val user = api.userManager.getUser(uuid) ?: return null

      return LuckPermsUser(user, api)
    }

    fun getIfLoaded(player: OfflinePlayer): LuckPermsUser? =
      this.getIfLoaded(player.uniqueId)
  }
}