package com.hellokotlin

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class HelloModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "HelloModule"
  }

  @ReactMethod
  fun getHello(promise: Promise) {
    promise.resolve("Ooi! Está vindo do Kotlin isso aqui.")
  }
}