package me.xentany.xcore.util.database

data class DatabaseConfig(
  val jdbcUrl: String,
  val username: String,
  val password: String,
  val maximumPoolSize: Int = 10,
  val minimumIdle: Int = 2,
  val poolName: String = "XCore-HikariPool",
  val dataSourceProperties: Map<String, String> = emptyMap()
) {

  class Builder {

    private var jdbcUrl: String = ""
    private var username: String = ""
    private var password: String = ""
    private var maximumPoolSize: Int = 10
    private var minimumIdle: Int = 2
    private var poolName: String = "XCore-HikariPool"
    private val dataSourceProperties: MutableMap<String, String> = mutableMapOf()

    fun jdbcUrl(jdbcUrl: String) = apply { this.jdbcUrl = jdbcUrl }
    fun username(username: String) = apply { this.username = username }
    fun password(password: String) = apply { this.password = password }
    fun maximumPoolSize(size: Int) = apply { this.maximumPoolSize = size }
    fun minimumIdle(idle: Int) = apply { this.minimumIdle = idle }
    fun poolName(poolName: String) = apply { this.poolName = poolName }
    fun dataSourceProperty(key: String, value: String) = apply { this.dataSourceProperties[key] = value }

    fun build(): DatabaseConfig {
      return DatabaseConfig(
        jdbcUrl = this.jdbcUrl,
        username = this.username,
        password = this.password,
        maximumPoolSize = this.maximumPoolSize,
        minimumIdle = this.minimumIdle,
        poolName = this.poolName,
        dataSourceProperties = this.dataSourceProperties.toMap()
      )
    }
  }

  companion object {

    fun builder() = Builder()
  }
}