package com.kmpdemo.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun getPlatformHttpClientEngine(): HttpClientEngine = Js.create()