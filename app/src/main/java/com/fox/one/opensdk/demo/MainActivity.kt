package com.fox.one.opensdk.demo

import android.accounts.Account
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fox.one.base.JsonUtils
import com.fox.one.base.LogUtils
import com.fox.one.opensdk.APILoader
import com.fox.one.opensdk.NoDataResponseBody
import com.fox.one.opensdk.account.AccountAPI
import com.fox.one.opensdk.account.model.AccountResponse
import com.fox.one.opensdk.config.ConfigManager
import com.fox.one.opensdk.pin.PinAPI
import com.fox.one.opensdk.pin.model.PinRequest
import com.fox.one.opensdk.wallet.WalletAPI
import com.fox.one.opensdk.wallet.model.WalletAssetListResponse
import com.fox.one.opensdk.wallet.model.WalletSnapshotsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_all_wallet_info).setOnClickListener {
            APILoader.load(WalletAPI::class.java).allWalletInfo
                    .enqueue(object : Callback<WalletAssetListResponse> {
                        override fun onFailure(call: Call<WalletAssetListResponse>, t: Throwable) {
                        }

                        override fun onResponse(call: Call<WalletAssetListResponse>, response: Response<WalletAssetListResponse>) {
                            response.body()?.data?.assets?.forEach {
                            }
                        }

                    })

        }
        findViewById<Button>(R.id.btn_deposit_supported_coins).setOnClickListener {
//            APILoader.load(WalletAPI::class.java).depositSupportedCoins
//                    .enqueue(object : Callback<WalletAssetListResponse> {
//                        override fun onFailure(call: Call<WalletAssetListResponse>, t: Throwable) {
//                        }
//
//                        override fun onResponse(call: Call<WalletAssetListResponse>, response: Response<WalletAssetListResponse>) {
//                            response.body()?.data?.assets?.forEach {
//                            }
//                        }
//                    })
//            ConfigManager.loadConfig(this@MainActivity)

            val intent = Intent()
//            intent.data = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/foxoneapp")
            intent.data = Uri.parse("fb://profile/foxoneapp/addfriend")
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.action = Intent.ACTION_VIEW

            startActivity(intent)
        }
    }
}
