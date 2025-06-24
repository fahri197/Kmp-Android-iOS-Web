package com.kmpdemo.local.pref

interface KeyValueStorage {
    fun putString(key: String, value: String)
    fun getString(key: String, default: String = ""): String
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, default: Boolean = false): Boolean
    fun remove(key: String)
}