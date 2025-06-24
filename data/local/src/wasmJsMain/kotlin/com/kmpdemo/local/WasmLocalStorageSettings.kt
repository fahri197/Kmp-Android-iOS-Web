package com.kmpdemo.local

import com.russhwolf.settings.Settings
import kotlinx.browser.window

class WasmLocalStorageSettings : Settings {

    private val localStorage = window.localStorage

    override val keys: Set<String>
        get() = localStorage.keys().toSet()

    override val size: Int
        get() = localStorage.length

    override fun clear() {
        localStorage.clear()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getBooleanOrNull(key) ?: defaultValue
    }

    override fun getBooleanOrNull(key: String): Boolean? {
        val value = localStorage.getItem(key) ?: return null
        return when (value.lowercase()) {
            "true" -> true
            "false" -> false
            else -> null
        }
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return getDoubleOrNull(key) ?: defaultValue
    }

    override fun getDoubleOrNull(key: String): Double? {
        val value = localStorage.getItem(key) ?: return null
        return value.toDoubleOrNull()
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return getFloatOrNull(key) ?: defaultValue
    }

    override fun getFloatOrNull(key: String): Float? {
        val value = localStorage.getItem(key) ?: return null
        return value.toFloatOrNull()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return getIntOrNull(key) ?: defaultValue
    }

    override fun getIntOrNull(key: String): Int? {
        val value = localStorage.getItem(key) ?: return null
        return value.toIntOrNull()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return getLongOrNull(key) ?: defaultValue
    }

    override fun getLongOrNull(key: String): Long? {
        val value = localStorage.getItem(key) ?: return null
        return value.toLongOrNull()
    }

    override fun getString(key: String, defaultValue: String): String {
        return getStringOrNull(key) ?: defaultValue
    }

    override fun getStringOrNull(key: String): String? {
        return localStorage.getItem(key)
    }

    override fun hasKey(key: String): Boolean {
        return localStorage.getItem(key) != null
    }

    override fun putBoolean(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    override fun putDouble(key: String, value: Double) {
        localStorage.setItem(key, value.toString())
    }

    override fun putFloat(key: String, value: Float) {
        localStorage.setItem(key, value.toString())
    }

    override fun putInt(key: String, value: Int) {
        localStorage.setItem(key, value.toString())
    }

    override fun putLong(key: String, value: Long) {
        localStorage.setItem(key, value.toString())
    }

    override fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    override fun remove(key: String) {
        localStorage.removeItem(key)
    }
}

// Extension function to convert JS Storage keys to Kotlin Set<String>
private fun org.w3c.dom.Storage.keys(): Set<String> {
    val result = mutableSetOf<String>()
    for (i in 0 until this.length) {
        this.key(i)?.let { result.add(it) }
    }
    return result
}

