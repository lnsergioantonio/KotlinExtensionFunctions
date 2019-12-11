import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension method to show toast for String.
 */
//fun String.toast(isShortToast: Boolean = true) = toast(this, isShortToast)

/**
 * Extension method to get md5 string.
 */
fun String.md5() = encrypt(this, "MD5")

/**
 * Extension method to get sha1 string.
 */
fun String.sha1() = encrypt(this, "SHA-1")

/**
 * Extension method to check if String is Phone Number.
 */
fun String.isPhone(): Boolean {
    val p = "^1([34578])\\d{9}\$".toRegex()
    return matches(p)
}

/**
 * Extension method to check if String is Email.
 */
fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}

/**
 * Extension method to check if String is Number.
 */
fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}

fun String.isIdcard(): Boolean {
    val p18 =
        "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]\$".toRegex()
    val p15 =
        "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]\$".toRegex()
    return matches(p18) || matches(p15)
}

/**
 * Extension method to check String equalsIgnoreCase
 */
fun String.equalsIgnoreCase(other: String) =
    this.toLowerCase(Locale("ES", "MX")).contentEquals(other.toLowerCase(Locale("ES", "MX")))

/**
 * Extension method to get encrypted string.
 */
private fun encrypt(string: String?, type: String): String {
    if (string.isNullOrEmpty()) {
        return ""
    }
    val md5: MessageDigest
    return try {
        md5 = MessageDigest.getInstance(type)
        val bytes = md5.digest(string.toByteArray())
        bytes2Hex(bytes)
    } catch (e: NoSuchAlgorithmException) {
        ""
    }
}

/**
 * Extension method to convert byteArray to String.
 */
private fun bytes2Hex(bts: ByteArray): String {
    var des = ""
    var tmp: String
    for (i in bts.indices) {
        tmp = Integer.toHexString(bts[i].toInt() and 0xFF)
        if (tmp.length == 1) {
            des += "0"
        }
        des += tmp
    }
    return des
}

/**
 * String date formatted
 */
private enum class DateFormat(val pattern: String) {
    YEAR_MONTH_DAY("yyyy-MM-dd"),
    DAY_MONTH_YEAR("dd-MM-yyyy")
}

fun String.toFortmatDate(
    patternOrigin: String = DateFormat.YEAR_MONTH_DAY.pattern,
    patternFinal: String = DateFormat.DAY_MONTH_YEAR.pattern
): String {
    val formatter = SimpleDateFormat(patternOrigin, Locale("ES", "MX"))
    var formattedDate = "Invalid date"
    try {
        formattedDate = formatter.parse(this).let {
            formatter.applyPattern(patternFinal)
            formatter.format(it)
        }
    } catch (exception: ParseException) {
    }
    return formattedDate
}
