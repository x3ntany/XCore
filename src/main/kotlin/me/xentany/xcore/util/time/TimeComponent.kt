package me.xentany.xcore.util.time

data class TimeComponent(
  val totalSeconds: Long,
  val totalMinutes: Long,
  val totalHours: Long,
  val days: Long,
  val hours: Int,
  val minutes: Int,
  val seconds: Int
) {

  companion object {

    @JvmStatic
    fun of(unit: TimeUnit, value: Long): TimeComponent {
      val totalSeconds = (value * unit.toConvertToSeconds).toLong()
      val days = totalSeconds / 86400
      val hours = (totalSeconds / 3600) % 24
      val minutes = (totalSeconds / 60) % 60
      val seconds = totalSeconds % 60

      return TimeComponent(
        totalSeconds = totalSeconds,
        totalMinutes = totalSeconds / 60,
        totalHours = totalSeconds / 3600,
        days = days,
        hours = hours.toInt(),
        minutes = minutes.toInt(),
        seconds = seconds.toInt()
      )
    }
  }
}