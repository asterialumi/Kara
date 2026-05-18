package lumi.projects.kara.utils

import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import lumi.projects.kara.app.KaraApp

fun Activity.getEditTextValue(id: Int) =
    findViewById<EditText>(id).text.toString()

fun Activity.getButtonView(id: Int): Button =
    findViewById<Button>(id)

fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.start(toClass: Class<*>?) {
    startActivity(Intent(this, toClass))
}

fun Activity.app(): KaraApp =
    application as KaraApp