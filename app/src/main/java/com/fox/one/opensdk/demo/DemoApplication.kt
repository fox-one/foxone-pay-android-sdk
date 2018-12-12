package com.fox.one.opensdk.demo

import android.app.Application
import com.fox.one.opensdk.FoxOneOpenSDK

/**
 * class description here
 * @author xiaoming1109@gmail.com
 * @version 1.0.0
 * @since 2018-09-04
 */
class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FoxOneOpenSDK.init(this, "123456", FoxOneOpenSDK.Option(debugEnable = true, workInTestEnv = true))

        if (isLogin()) {
            FoxOneOpenSDK.token = token()
        }
    }

    fun isLogin(): Boolean {
        return true
    }

    fun token(): String {
        return "dSw1NG8sZCxBayxlLDFnUWs2UA==.rm+O9ZJ9TxSTTIFCxepfAJlN+VqM1cEnbYCN0iGeFzY="
    }

    fun onLogin(token: String) {
        //set token when login
        FoxOneOpenSDK.token = token
    }

    fun onLogout() {
        FoxOneOpenSDK.token = ""
    }
}