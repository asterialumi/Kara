package lumi.projects.kara.utils

import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import lumi.projects.kara.app.KaraApp

// --- ACTIVITY EXTENSIONS ---
fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
fun Activity.getButtonView(id: Int): Button = findViewById<Button>(id)
fun Activity.getTextView(id: Int): TextView = findViewById<TextView>(id)
fun Activity.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Activity.start(toClass: Class<*>?) = startActivity(Intent(this, toClass))
fun Activity.app(): KaraApp = application as KaraApp

// --- FRAGMENT EXTENSIONS ---
fun Fragment.getEditTextValue(id: Int) = requireView().findViewById<EditText>(id).text.toString()
fun Fragment.getButtonView(id: Int): Button = requireView().findViewById<Button>(id)
fun Fragment.getTextView(id: Int): TextView = requireView().findViewById<TextView>(id)
fun Fragment.toast(msg: String) = Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
fun Fragment.start(toClass: Class<*>?) = startActivity(Intent(requireContext(), toClass))
fun Fragment.app(): KaraApp = requireActivity().application as KaraApp