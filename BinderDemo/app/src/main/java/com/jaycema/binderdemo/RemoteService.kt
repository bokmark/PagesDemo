package com.jaycema.binderdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {

    val b = object : IMyAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ): String {
            return "basicTypes anInt$anInt, aLong$aLong, aBoolean$aBoolean, aFloat$aFloat, aDouble$aDouble, aString$aString"
        }

        override fun basicTypes2(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ): String {
            return "basicTypes2 anInt$anInt, aLong$aLong, aBoolean$aBoolean, aFloat$aFloat, aDouble$aDouble, aString$aString"
        }

        override fun basicTypes3(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ): String {
            return "basicTypes3 anInt$anInt, aLong$aLong, aBoolean$aBoolean, aFloat$aFloat, aDouble$aDouble, aString$aString"
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return b
    }
}