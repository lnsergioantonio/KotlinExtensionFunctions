import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension method to provide Date related functions.
 */

private enum class DateExpr {
    YEAR, MONTH, DAY,
    HOUR, MINUTE, SECOND,
    WEEK, DAY_YEAR, WEEK_YEAR
}

fun Long.date(pattern: String = "yyyy-MM-dd HH:mm:ss"): String? = SimpleDateFormat(pattern, Locale("ES", "MX")).format(this)

fun Long.year() = getData(this, DateExpr.YEAR)

fun Long.month() = getData(this, DateExpr.MONTH)

fun Long.day() = getData(this, DateExpr.DAY)

fun Long.week() = getData(this, DateExpr.WEEK)

fun Long.hour() = getData(this, DateExpr.HOUR)

fun Long.minute() = getData(this, DateExpr.MINUTE)

fun Long.second() = getData(this, DateExpr.SECOND)

fun Long.dayOfYear() = getData(this, DateExpr.DAY_YEAR)

fun Long.weekOfYear() = getData(this, DateExpr.WEEK_YEAR)

fun Long.dateOnly(split: String = "-") = "${year()}$split${month()}$split${day()}"

fun Long.timeOnly(split: String = ":") = "${hour()}$split${minute()}$split${second()}"

fun Int.isLeapYear() = (this % 4 == 0) && (this % 100 != 0) || (this % 400 == 0)

private fun getData(timeMillis: Long, expr: DateExpr): String {
    val cal = Calendar.getInstance()
    cal.time = Date(timeMillis)
    return when (expr) {
        DateExpr.YEAR -> cal[Calendar.YEAR].toString()
        DateExpr.MONTH -> (cal[Calendar.MONTH] + 1).toString().prefix0()
        DateExpr.DAY -> cal[Calendar.DAY_OF_MONTH].toString().prefix0()
        DateExpr.WEEK -> {
            val week = arrayOf("Desconocido", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")
            week[cal.get(Calendar.DAY_OF_WEEK)]
        }
        DateExpr.HOUR -> cal[Calendar.HOUR_OF_DAY].toString().prefix0()
        DateExpr.MINUTE -> cal[Calendar.MINUTE].toString().prefix0()
        DateExpr.SECOND -> cal[Calendar.SECOND].toString().prefix0()
        DateExpr.DAY_YEAR -> cal[Calendar.DAY_OF_YEAR].toString()
        DateExpr.WEEK_YEAR -> cal[Calendar.WEEK_OF_YEAR].toString()
    }
}

private fun String.prefix0() = if (length == 1) "0$this" else this