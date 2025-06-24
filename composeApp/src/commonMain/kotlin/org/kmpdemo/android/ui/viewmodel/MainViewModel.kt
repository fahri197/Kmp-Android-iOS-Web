package org.kmpdemo.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kmpdemo.local.pref.KeyValueStorage
import com.kmpdemo.presentation.utils.PrefKeys

class MainViewModel(
    private val preferences: KeyValueStorage
) : ViewModel() {

    var isLoggedIn: Boolean = preferences.getBoolean(PrefKeys.IS_LOGGED_IN, false)

}