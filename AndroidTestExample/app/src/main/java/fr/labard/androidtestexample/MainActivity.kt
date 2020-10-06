package fr.labard.androidtestexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s = Stats(arrayOf(1, 2, 3, 4, 5, 6))
        textview.text = s.getMean().toString() + s.getSum().toString()
    }
}