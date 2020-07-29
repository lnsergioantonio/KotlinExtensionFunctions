import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.isEmail(error: String): Boolean {
    if (text.toString().trim().isEmail())
        return true
    this.error = error
    return false
}

fun TextInputEditText.isPhone(error: String): Boolean {
    if (text.toString().trim().isPhone())
        return true
    this.error = error
    return false
}

fun TextInputEditText.isRequired(error: String): Boolean {
    if (text.toString().trim().isEmpty()){
        this.error = error
        return true
    }
    return false
}