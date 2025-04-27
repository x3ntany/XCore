package me.xentany.xcore.util.time

enum class TimeUnit(val toConvertToSeconds: Double) {

  MILLISECONDS(0.001),
  SECONDS(1.0),
  MINUTES(60.0),
  HOURS(3600.0),
  DAYS(86400.0)
}