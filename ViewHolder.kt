import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Access toast from any VideHolder
 */
fun ViewHolder.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    itemView.context.toast(message, length)
}

/**
 * findById view into viewholder
 */
inline fun <reified T:View> ViewHolder.find(componentId:Int):T{
    return itemView.find(componentId) as T
}
