# Fox.ONE Pay SDK for Android

Pay SDK 是加密货币支付解决方案的基础 SDK。基于Pay SDK 可以方便商户，开发者快速的集成数字化钱包。

* 转入
* 转出
* 资产总览
* 交易记录
* 汇率
* PIN码

## 快速集成

>也可以直接查看 [接入文档](API.md) 进行接入

1. 在 [商户平台](https://admin.pay.fox.one/) 中注册账户，通过验证之后，并且填写商户信息提交审核。

2. 申请审核通过之后会发送 `AppSecret` 给您， `AppSecret` 是访问支付后台的令牌。

3. 在您的应用中集成 SDK 并使用:

* 添加 Gradle 依赖

```groovy
implementation 'com.fox.one:opensdk:0.2.2'
```

* 初始化

> 在`Application#onCreate()`中初始化`FoxOneOpenSDK`
 
```kotlin
//AppSecret为接入方申请的AppSecret
FoxOneOpenSDK.init(this, "YourAppSecret", FoxOneOpenSDK.Option(debugEnable = true, workInTestEnv = true))
```

* 设置 Token

```kotlin
//在用户登录状态下设置token, token用于API接口请求使用，无token或者token失效时，API接口会返回ResponseCode 401错误码（身份验证失败）
FoxOneOpenSDK.token = '34tsr45trert4645643t'
```

* API接口使用

> openSDK初始化后就可以调用业务API接口了，详细参考 [接入文档](API.md) ，例如：

```kotlin
//获取资产信息
APILoader.load(WalletAPI::class.java).getWalletAssetInfo(assetId)
                .enqueue(object : Callback<WalletAssetResponse> {
                    override fun onFailure(call: Call<WalletAssetResponse>, t: Throwable) {
                        //process failure event
                        HttpErrorHandler.handle(t)
                    }

                    override fun onResponse(call: Call<WalletAssetResponse>, response: Response<WalletAssetResponse>) {
                    	//process suceed event
                        DataCenter.get(WalletDataSource::class.java).postAssetValue(assetId, FoxWalletAssetBean(response.body()?.data?.asset))
                    }
                })
```

##  参考文档
- [接入文档](API.md)
- [错误码](errors.md)