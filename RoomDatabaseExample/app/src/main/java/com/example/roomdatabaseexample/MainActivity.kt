package com.example.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            RecordDatabase::class.java,
            "record_database"
        ).build()

        val a = arrayListOf(
            RecordEntity(
                0,
                "2020/06/22",
                0
            ),
            RecordEntity(
                0,
                "2020/06/21",
                1
            )
        )
        var s = ""

        Thread(Runnable {
            db.recordDao().insert(a[0])
            db.recordDao().insert(a[1])
            s = db.recordDao().getAll().toString()
            runOnUiThread {textview.text = s}
        }).start()
    }
}
