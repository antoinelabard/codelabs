package fr.labard.alertdialogexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Show toast")
                    .setMessage("Do you want to show the toast?")
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setPositiveButton("yes") { _: DialogInterface, _: Int ->
                Toast.makeText(this@MainActivity, "Toast shown!", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("no") { _: DialogInterface, _: Int ->
                Toast.makeText(this@MainActivity, "Toast not shown! (really?)", Toast.LENGTH_LONG).show()
            }
            .show()
        }
    }
}
