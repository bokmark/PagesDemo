package com.jaycema.binderdemo.my

import android.os.IInterface

interface MyAidl : IInterface {

    fun my(query: String): String
}
