import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Show the view if [condition] returns true
 * (visibility = View.VISIBLE)
 */
inline fun View.showIf(condition: () -> Boolean) : View {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.INVISIBLE)
 */
fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Hide the view if [predicate] returns true
 * (visibility = View.INVISIBLE)
 */
inline fun View.hideIf(predicate: () -> Boolean) : View {
    if (visibility != View.INVISIBLE && predicate()) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

/**
 * Remove the view if [predicate] returns true
 * (visibility = View.GONE)
 */
inline fun View.removeIf(predicate: () -> Boolean) : View {
    if (visibility != View.GONE && predicate()) {
        visibility = View.GONE
    }
    return this
}

/**
 * Toggle a view's visibility
 */
fun View.toggleVisibility() : View {
    if (visibility == View.INVISIBLE) {
        visibility = View.VISIBLE
    } else {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Toggle a view's visibility
 */
fun View.toggleVisibleIf(condition: () -> Boolean) : View {
    visibility = if (condition()) {
        View.VISIBLE
    }else{
        View.INVISIBLE
    }
    return this
}

/**
 * Toggle a view's visibility
 */
fun View.toggleRemoveIf(condition: () -> Boolean) : View {
    visibility = if (condition()) {
        View.GONE
    }else{
        View.VISIBLE
    }
    return this
}


/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

inline fun <reified T:View> View.find(componentId:Int):T{
    return findViewById(componentId) as T
}