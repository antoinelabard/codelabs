package com.example.serviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private lateinit var mService: ExampleService
//    private var mBound: Boolean = false
//    private val connection = object: ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val binder = service as ExampleService.LocalBinder
//            mService = binder.getService()
//            mBound = true
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            mBound = false
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener {
            Intent(this, SimpleService::class.java).also { intent -> startService(intent) }
        }
        stop.setOnClickListener {
            stopService(Intent(applicationContext, SimpleService::class.java))
        }
    }
//
//    override fun onStart() {
//        super.onStart()
//        Intent(this, ExampleService::class.java).also { intent ->
//            bindService(intent, connection, Context.BIND_AUTO_CREATE)
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unbindService((connection))
//        mBound = false
//    }
}