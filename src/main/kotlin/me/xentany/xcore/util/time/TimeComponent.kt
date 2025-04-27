package me.xentany.xcore.util.time

data class TimeComponent(
  val totalSeconds: Long,
  val totalMinutes: Long,
  val totalHours: Long,
  val days: Long,
  val hours: Int,
  val minutes: Int,
  val seconds: Int
)