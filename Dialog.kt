import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

inline fun Activity.alert(title: CharSequence? = null, message: CharSequence? = null, func: AlertDialogHelper.() -> Unit = {}): AlertDialog? {
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Activity.alert(titleResource: Int = 0, messageResource: Int = 0, func: AlertDialogHelper.() -> Unit = {}): AlertDialog? {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Fragment.alert(title: CharSequence? = null, message: CharSequence? = null, func: AlertDialogHelper.() -> Unit = {}): AlertDialog? {
    return context?.run {
        AlertDialogHelper(this, title, message).apply {
            func()
        }.create()
    }
}

inline fun Fragment.alert(titleResource: Int = 0, messageResource: Int = 0, func: AlertDialogHelper.() -> Unit = {}): AlertDialog? {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return context?.run {
        AlertDialogHelper(this, title, message).apply {
            func()
        }.create()
    }
}

class AlertDialogHelper(context: Context, title: CharSequence?, message: CharSequence?) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    private var dialog: AlertDialog? = null

    var cancelable: Boolean = true

    init {
        builder.setTitle(title)
        builder.setMessage(message)
    }

    fun positiveButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        builder.setPositiveButton(textResource) { dialog, which -> func?.invoke() }
    }

    fun positiveButton(text: CharSequence, func: (() -> Unit)? = null) {
        builder.setPositiveButton(text) { dialog, which -> func?.invoke() }
    }

    fun negativeButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        builder.setNeutralButton(textResource) { dialogInterface, i ->  func?.invoke()}
    }

    fun negativeButton(text: CharSequence, func: (() -> Unit)? = null) {
        builder.setNeutralButton(text) { dialogInterface, i ->  func?.invoke()}
    }

    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog? {
        dialog = builder
            .setCancelable(cancelable)
            .create()
        return dialog
    }
}