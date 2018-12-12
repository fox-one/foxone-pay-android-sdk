## 接入指南

* 申请 AppSecret

在[商户平台](https://admin.pay.fox.one/)中注册账户，通过验证之后，并且填写商户信息提交审核。

申请审核通过之后会发送 `AppSecret` 给您， `AppSecret` 是访问支付后台的令牌。

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

> openSDK初始化后就可以调用业务API接口了，详细参考[接入文档](API.md)，例如：

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

**`注意：API的具体使用情况可参考Demo`**

## API 说明

API 接口详情，请阅读[API 详情](api_detail.md)

* AccountAPI

|接口|参数|Response|说明|
|---|---|---|---|
|login|LoginRequest||登录|
|loginWithSMS|ValidatedRequest.ForLogin||短信验证码登录|
|register|RegisterRequest||注册|
|getAccountDetail|||获取登录用户详情|
|modify|ModifyAccountRequest||修改用户信息|
|resetPassword|ResetPasswordRequest||重置密码|
|bind|ValidatedRequest.ForChangeTelOrEmail||绑定用户手机号或者邮箱|
|logout|||登出|

* WalletAPI

|接口|参数|Response|说明|
|---|---|---|---|
|getAllWalletInfo|||获取所有钱包信息|
|getWalletInfo|assetID||获取指定钱包信息|
|getAllSnapshots|||获取交易记录|
|withDraw|pin<br>WithDrawRequest||转账|
|getDepositSupportedCoins|||获取支持转账的数字货币|
|getFee|pin<br>assetId<br>publicKey<br>label||获取转账费用|
|hideWalletAsset|IdsRequest||隐藏指定钱包资产ID的钱包资产|
|showWalletAsset|IdsRequest||移除指定钱包资产ID的隐藏状态|

* PinAPI

|接口|参数|Response|说明|
|---|---|---|---|
|setPin|PinRequest||设置用户的pin|
|modifyPin|pin:<br>PinRequest:||修改用户pin码|
|verifyPin|pin:<br>PinRequest:||验证用户的pin码|

* 短信验证码API：`SMSCodeHelper`
> 通过该接口请求相应业务的短信验证码，包括如下业务

```java
//激活验证码
SMSCodeType.ACTIVATION,
//重置密码验证码
SMSCodeType.RESET_PASSWORD,
//绑定账号验证码
SMSCodeType.BIND,
//短信登录验证码
SMSCodeType.LOGIN

```

接口：

|接口|参数|Response|说明|
|---|---|---|---|
|requestSMSCode|tel:<br>email:<br>captcha:图片验证码<br>smsCodeType:业务类型||请求短信验证码|

* 汇率接口 `ExchangeRateManager`

```kotlin
//获取美元兑人民币汇率，e.g: 6.85
getExchangeRateUSD2CNY()

//获取USDT兑人民币汇率，注意：USDT非USD（美元）
getExchangeRateUSDT2CNY()

//将美元价格换算成人民币价格
getUSD2CNY(usdValue: Double)

//将人民币价格换算成美元价格
getCNY2USD(cnyValue: Double)

//获取其他币兑人民币的价格，from: USD, JPY等
getExchangeRate2CNY(from: String)

```

## 混淆配置

```bash
# --------for retrofit2 start------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembernames interface * {
        @retrofit.http.* <methods>;
}


#----------for retrofit2 end---------------

##--- Begin:GSON ----
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.

# For using GSON @Expose annotation

-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# keep enum so gson can deserialize it
-keepclassmembers enum * { *; }

##--- End:GSON ----

-keep class * implements java.io.Serializable{*;}
-keepnames class * implements java.io.Serializable
-keepclasseswithmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
```
### [错误码](errors.md)

### [CHANGELOG](CHANGELOG.md)

### [LICENSE](LICENSE)