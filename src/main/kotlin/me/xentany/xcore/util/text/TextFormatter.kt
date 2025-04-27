package me.xentany.xcore.util.text

object TextFormatter {

  private val pattern = "\\{([a-zA-Z0-9_]+)}".toRegex()

  @JvmStatic
  fun format(template: String, values: Map<String, Any?>): String =
    this.pattern.replace(template) { matchResult ->
      val key = matchResult.groupValues[1]

      values[key]?.toString() ?: matchResult.value
    }
}