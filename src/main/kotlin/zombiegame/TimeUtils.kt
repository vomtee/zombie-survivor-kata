package zombiegame

import java.time.Clock
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField.HOUR_OF_DAY
import java.time.temporal.ChronoField.MINUTE_OF_HOUR
import java.util.*
import zombiegame.TimeUtils.Companion.SHORT_LOCAL_TIME_FORMAT


const val FIXED_TIME = "1970-01-01T12:34:56.00Z"
const val ZONE_ID = "UTC"

class TimeUtils {
    companion object {
        val FIXED_CLOCK = Clock.fixed(Instant.parse(FIXED_TIME), ZoneId.of(ZONE_ID))
        val SHORT_LOCAL_TIME_FORMAT = DateTimeFormatterBuilder()
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .toFormatter(Locale.GERMANY)
    }
}

fun LocalTime.toShortFormat() : String {
    return this.format(SHORT_LOCAL_TIME_FORMAT)
}

fun Clock.toShortFormat(): String {
    return LocalTime.now(this).toShortFormat()
}