import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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