package lumi.projects.kara.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import lumi.projects.kara.R
import lumi.projects.kara.app.KaraApp



// --- ACTIVITY EXTENSIONS ---
fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
fun Activity.getButtonView(id: Int): Button = findViewById<Button>(id)
fun Activity.getTextView(id: Int): TextView = findViewById<TextView>(id)
fun Activity.snack(msg: String) = findViewById<View>(android.R.id.content).snack(msg)
fun Activity.start(toClass: Class<*>?) = startActivity(Intent(this, toClass))
fun Activity.app(): KaraApp = application as KaraApp
fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let { imm.hideSoftInputFromWindow(it.windowToken, 0) }
}

fun Activity.startClear(toClass: Class<*>) {
    val intent = Intent(this, toClass)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
    finish()
}


// --- FRAGMENT EXTENSIONS ---
fun Fragment.getEditTextValue(id: Int) = requireView().findViewById<EditText>(id).text.toString()
fun Fragment.getButtonView(id: Int): Button = requireView().findViewById<Button>(id)
fun Fragment.getTextView(id: Int): TextView = requireView().findViewById<TextView>(id)
fun Fragment.snack(msg: String) = requireView().snack(msg)
fun Fragment.start(toClass: Class<*>?) = startActivity(Intent(requireContext(), toClass))
fun Fragment.app(): KaraApp = requireActivity().app()
fun Fragment.hideKeyboard() = requireActivity().hideKeyboard()
fun Fragment.color(id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.showInputDialog(title: String, hint: String, onResult: (String) -> Unit) {
    val input = EditText(requireContext()).apply { this.hint = hint }
    android.app.AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setView(input)
        .setPositiveButton("Add") { _, _ ->
            val text = input.text.toString()
            if (text.isNotEmpty()) onResult(text)
        }
        .setNegativeButton("Cancel", null)
        .show()
}

fun Fragment.startClear(toClass: Class<*>) {
    val intent = Intent(requireContext(), toClass)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}



// --- VIEW EXTENSIONS ---
fun View.visible() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }
fun View.showIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}
fun View.snack(msg: String) {
    val snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)

    // Get the actual View of the Snackbar
    val snackbarView = snackbar.view

    // Add margins to make it "float"
    val params = snackbarView.layoutParams as FrameLayout.LayoutParams
    params.setMargins(64, 0, 64, 120)
    params.width = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    params.gravity = android.view.Gravity.CENTER_HORIZONTAL or android.view.Gravity.BOTTOM

    snackbarView.layoutParams = params
    snackbarView.setBackgroundColor(context.getColor(R.color.kara_primary))

    snackbar.show()
}



// --- LONG EXTENSIONS ---
fun Long.toStopwatchFormat(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = (this / (1000 * 60 * 60))
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

fun Long.toStatFormat(): String {
    val hours = this / (1000 * 60 * 60)
    val minutes = (this / (1000 * 60)) % 60
    return "${hours}h ${minutes}m"
}

fun Long.toDateString(): String {
    val sdf = java.text.SimpleDateFormat("EEEE, MMM dd", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}



// --- SPECIAL EXTENSIONS ---
fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun RecyclerView.setup(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
    this.setHasFixedSize(true) // Performance boost
}
