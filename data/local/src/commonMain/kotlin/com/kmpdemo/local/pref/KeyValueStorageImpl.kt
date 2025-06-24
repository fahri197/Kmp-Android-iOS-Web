package com.kmpdemo.local.pref

import com.russhwolf.settings.Settings

class KeyValueStorageImpl(private val settings: Settings) : KeyValueStorage {

    override fun putString(key: String, value: String) {
        settings.putString(key, value)
    }

    override fun getString(key: String, default: String): String {
        return settings.getString(key, default)
    }

    override fun putBoolean(key: String, value: Boolean) {
        settings.putBoolean(key, value)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return settings.getBoolean(key, default)
    }

    override fun remove(key: String) {
        settings.remove(key)
    }
}