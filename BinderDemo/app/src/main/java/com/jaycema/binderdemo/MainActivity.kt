package com.jaycema.binderdemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.jaycema.binderdemo.my.MyService
import com.jaycema.binderdemo.my.MyStub
import java.util.concurrent.locks.ReentrantReadWriteLock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        val sv = findViewById<ScrollView>(R.id.sv)
        val vp = findViewById<ViewPager>(R.id.vp)
        val rwLock = ReentrantReadWriteLock()
        rwLock.readLock().lock()

/*
        bindService(
            Intent(MainActivity@ this, RemoteService::class.java),
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("mainAct", "onServiceConnected name $name, service $service")
                    val mybinder = IMyAidlInterface.Stub.asInterface(service)
                    tv.setText(mybinder.basicTypes(1, 2, false, 0.1f, 2.0, "xxx"))
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("mainAct", "onServiceDisconnected name $name")
                }

            },
            BIND_AUTO_CREATE
        )*/

        bindService(
            Intent(MainActivity@ this, MyService::class.java),
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("mainAct", "onServiceConnected name $name, service $service")
                    val mybinder1 = service?.let { MyStub.asInterface(it) }
                    tv.setText(mybinder1?.my("11111"))
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("mainAct", "onServiceDisconnected name $name")
                }

            },
            BIND_AUTO_CREATE
        )

    }
}