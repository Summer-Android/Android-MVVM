package com.app.utils.ktx

import com.blankj.utilcode.util.TimeUtils
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

const val PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss"
const val PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:SSSS"
const val PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm"
const val PATTERN_YEAR_MONTH_DAY = "yyyy-MM-dd"
const val PATTERN_YEAR_MONTH_DAY_CHINESE = "yyyy年MM月dd日"
const val PATTERN_MONTH_DAY = "MM-dd"
const val PATTERN_MONTH_DAY_CHINESE = "MM月dd日"
const val PATTERN_MONTH_DAY_HOURS_MINUTES = "MM-dd HH:mm"
const val PATTERN_24HOURS_MINUTES = "HH:mm"
const val PATTERN_12HOURS_MINUTES = "hh:mm"

const val ONE_MINUTE_MILLIS = 60 * 1000L                     // 1分钟
const val ONE_HOUR_MILLIS = 60 * 60 * 1000L                  // 1小时

const val ONE_DAY_MILLIS = 1 * 24 * 60 * 60 * 1000L         // 1天
const val TWO_DAYS_MILLIS = 2 * 24 * 60 * 60 * 1000L        // 2天
const val SEVEN_DAYS_MILLIS = 7 * 24 * 60 * 60 * 1000L      // 7天
const val THIRTY_DAYS_MILLIS = 30 * 24 * 60 * 60 * 1000L    // 30

fun Int.dateFormat(): String {
    if (this > 0) return dateFormat(PATTERN_DEFAULT)
    return ""
}

fun Int.dateFormat(format: String): String {
    if (this > 0) {
        val sdf = TimeUtils.getSafeDateFormat(format)
        return sdf.format(this)
    }
    return ""
}

fun Long.dateFormat(): String {
    if (this > 0) return dateFormat(PATTERN_DEFAULT)
    return ""
}

fun Long.dateFormat(format: String): String {
    if (this > 0) {
        val sdf = TimeUtils.getSafeDateFormat(format)
        return sdf.format(this)
    }
    return ""
}

fun Long.dateFormatUTC(): String {
    return TimeUtils.getSafeDateFormat(PATTERN_DEFAULT).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }?.format(Date(this)).orEmpty()
}

fun Date.dateFormat(): String {
    return dateFormat(PATTERN_DEFAULT)
}

fun Date.dateFormat(format: String): String {
    val sdf = TimeUtils.getSafeDateFormat(format)
    return sdf.format(this)
}

fun String.toDate(): Date? {
    if (this.isEmpty()) return null

    return toDate(PATTERN_DEFAULT)
}

fun String.toDate(format: String): Date? {
    if (this.isEmpty()) return null

    val dateFormat = TimeUtils.getSafeDateFormat(format)
    try {
        return dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun String.timeParse(): Long {
    if (this.isNotEmpty()) {
        val dateFormat = TimeUtils.getSafeDateFormat(PATTERN_DEFAULT)
        return try {
            dateFormat.parse(this)?.time ?: 0
        } catch (e: Exception) {
            0
        }
    }
    return 0
}

/**
 * 获取当天结束时的时间：23:59:59
 */
fun getTheDayEndTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.HOUR_OF_DAY, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    calendar.set(Calendar.MILLISECOND, 59)
    return calendar.timeInMillis
}

/**
 * 获取当天开始时间：00:00:00
 */
fun getTheDayStartTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

/**
 * 获取前一天开始时间：00:00:00
 */
fun getTheDayBeforeStartTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1) // 设置时间为前一天
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

/**
 * 获取前两天时间：00:00:00
 */
fun getTwoDaysBeforeTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 2) // 设置时间为前两天天
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

/**
 * 获取今年开始时间：00:00:00
 */
fun getTheYearStartTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.setTimeInMillis(System.currentTimeMillis())
    calendar.add(Calendar.YEAR, 0)
    calendar.add(Calendar.DATE, 0)
    calendar.add(Calendar.MONTH, 0)
    calendar.set(Calendar.DAY_OF_YEAR, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

/**
 * 是毫秒
 */
fun Long.isMilliseconds(): Boolean {
    return this.toString().length == 13
}

/**
 * 秒To毫秒
 */
fun Long.toMilliseconds(): Long {
    return this * 1000
}