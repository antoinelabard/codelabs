package fr.labard.livedataexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var model: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = RecordViewModel()

        val recordObserver = Observer { _: Observable, _: Any ->
            fun onChanged(n: Int) {
                textview.text = n.toString()
            }
        }
        model.records.value.observe(this, recordObserver)
        button.setOnClickListener {
            model.records.postValue(1)
        }

    }
}
