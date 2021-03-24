package com.jaycema.binderdemo.my

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return MyStub()
    }
}

class MyStub:Binder(), MyAidl {
    init {
        this.attachInterface(this, "wodetianya")
    }

    override fun my(query: String): String {
        return "aaa$query"
    }

    override fun asBinder(): IBinder {
        return this
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        when(code) {
            INTERFACE_TRANSACTION -> {
                reply?.writeString("wodetianya")
                return true
            }
            FIRST_CALL_TRANSACTION -> {
                data.enforceInterface("wodetianya")
                val s = data.readString()?: ""
                reply?.writeNoException()
                reply?.writeString(my(s))
                return true
            }
        }

        return super.onTransact(code, data, reply, flags)
    }

    companion object {
        fun asInterface(iBinder: IBinder):MyAidl {
            val local = iBinder.queryLocalInterface("wodetianya")
            if (local != null) {
                return local as MyAidl
            }
            return Proxy(iBinder)
        }
    }

}

class Proxy(val mRemote: IBinder) :MyAidl {
    override fun my(query: String): String {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try {
            data.writeInterfaceToken("wodetianya")
            data.writeString(query)
            mRemote.transact(Binder.FIRST_CALL_TRANSACTION, data, reply, 0)
            reply.readException()
            return reply.readString() ?: ""
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    override fun asBinder(): IBinder {
        return mRemote
    }

}