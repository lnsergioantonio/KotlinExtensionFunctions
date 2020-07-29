import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding

/**
 * Runs a FragmentTransaction, then calls commit().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

/**
 * Extension method to display toast text for Fragment.
 */
fun Fragment?.toast(text: String, duration: Int = Toast.LENGTH_LONG) = this?.let { activity.toast(text, duration) }

fun Fragment?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { activity.toast(textId, duration) }

/**
 * Extension method location enabled
 */
fun Fragment?.isLocationEnabled() = this?.let { activity.isLocationEnabled() }?:false

/**
 * Extension method is network connected
 */
fun Fragment?.isNetworkConnected() = this?.let { activity.isNetworkConnected() }?:false

/**
 * Extension method to display notification text for Fragment.
 */
inline fun Fragment?.notification(channelId:String, body: NotificationCompat.Builder.() -> Unit) = this?.let { activity?.notification(channelId,body) }

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Fragment.showSoftKeyboard() {
    activity?.showSoftKeyboard()
}

/**
 * Get extras by lazy
 */
inline fun <reified T: Any> Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)