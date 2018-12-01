## WAlletAPI

#### - getAllWalletInfo
 * 参数:
 
 * response:
 
   `WalletAssetListResponse`
   以数组的形式返回多个钱包资产信息，单个钱包信息如下：
   
   
   ```
 
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * 币的总数量
     */
    private double balance;
    /**
     * 该币归属的主链的ID
     */
    private String chainId;
    /**
     * 以BTC为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeBtc;
    /**
     * 以USD为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeUsd;

     /**
     * EOS资产专用：name, 对应于转账参数的{@link WithdrawRequest#label}
     */
    private String accountName;
    /**
     * EOS资产专用：tag, 对应于转账参数的{@link WithdrawRequest#memo}
     */
    private String accountTag;
    /**
     * 主链信息
     */
    private ChainCoin chain;
    /**
     * 当前资产币的信息
     */
    private ChainCoin coin;

    /**
     * 额外信息:
     * hide: 用来标记在钱包里显示还是隐藏
     */
    private WalletAssetBean.Option option;

    /**
     * 币ID
     * ETH coin id: 2
     * EOS coin id: 5
     */
    private int coinId;
    /**
     *
     */
    private double confirmations;
    /**
     * 币图标
     */
    private String icon;
    /**
     * 币全称
     */
    private String name;
    /**
     * btc单价
     */
    private double priceBtc;
    /**
     * usd单价
     */
    private double priceUsd;
    /**
     * 资产钱包地址
     */
    private String publicKey;
    /**
     * 币简称
     */
    private String symbol;
   ```
   
   
   
 
#### - getWalletInfo

* 参数：
    -  assetId: 钱包资产ID
* response:
  
  ```
   /**
     * 资产ID
     */
    private String assetId;
    /**
     * 币的总数量
     */
    private double balance;
    /**
     * 该币归属的主链的ID
     */
    private String chainId;
    /**
     * 以BTC为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeBtc;
    /**
     * 以USD为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeUsd;

      /**
     * EOS资产专用：name, 对应于转账参数的{@link WithdrawRequest#label}
     */
    private String accountName;
    /**
     * EOS资产专用：tag, 对应于转账参数的{@link WithdrawRequest#memo}
     */
    private String accountTag;
    /**
     * 主链信息
     */
    private ChainCoin chain;
    /**
     * 当前资产币的信息
     */
    private ChainCoin coin;

    /**
     * 额外信息:
     * hide: 用来标记在钱包里显示还是隐藏
     */
    private WalletAssetBean.Option option;

    /**
     * 币ID
     * ETH coin id: 2
     * EOS coin id: 5
     */
    private int coinId;
    /**
     *
     */
    private double confirmations;
    /**
     * 币图标
     */
    private String icon;
    /**
     * 币全称
     */
    private String name;
    /**
     * btc单价
     */
    private double priceBtc;
    /**
     * usd单价
     */
    private double priceUsd;
    /**
     * 资产钱包地址
     */
    private String publicKey;
    /**
     * 币简称
     */
    private String symbol;
  ```

#### - getAllSnapshots
* 参数：
* response：
 `WalletSnapshotsResponse`: 以数组形式返回多个交易账单信息，单个账单信息如下：
 
 ```
 /**
     * 资产总额
     */
    private double amount;
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * coin ID
     */
    private int coinId;
    /**
     *
     */
    private String counterUserId;
    /**
     * 转账时间
     */
    private String createdAt;
    /**
     * 备注
     */
    private String memo;
    /**
     * 账单ID
     */
    private String snapshotId;

    private String traceId;
    /**
     * 交易hash
     */
    private String transactionHash;
    /**
     * 是否在mixin网络内部发生的交易
     */
    private boolean insideMixin;
 ```

#### - getSnapshots
* 参数：
    - assetId: 指定钱包资产的ID
* response:
 `WalletSnapshotsResponse`: 以数组形式返回多个交易账单信息，单个账单信息如下：
 
 ```
 /**
     * 资产总额
     */
    private double amount;
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * coin ID
     */
    private int coinId;
    /**
     *
     */
    private String counterUserId;
    /**
     * 转账时间
     */
    private String createdAt;
    /**
     * 备注
     */
    private String memo;
    /**
     * 账单ID
     */
    private String snapshotId;

    private String traceId;
    /**
     * 交易hash
     */
    private String transactionHash;
    /**
     * 是否在mixin网络内部发生的交易
     */
    private boolean insideMixin;
 ```

#### - withDraw
* 参数:
    - pin: 用户的pin码
    - WithDrawRequest:
 
 ```
  /**
     * 钱包地址
     */
    private String publicKey;
    /**
     * 转账数量
     */
    private String amount;
    /**
     * 备注, 当为EOS转账时，memo必填，且填入的数据必须是{@link WalletAssetBean#accountTag}
     */
    private String memo;
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * 资产标识，用于EOS转账用，填入的数据为{@link WalletAssetBean#accountName}, mixin网络上一般为eoswithmixin
     */
    private String label;
 ```
 
* response:

```
/**
     * 业务状态码，0：成功； 非0：失败，不同状态码对应不同业务错误
     */
    private int code = 0;
    /**
     * 接口返回的状态信息
     */
    private String msg = null;
```

#### - getDepositSupportedCoins
* 参数：
* response：
    - `WalletAssetListResponse`: 以数组形式返回多个数字资产货币列表，单个数据结构如下：
 
 ```
 /**
     * 资产ID
     */
    private String assetId;
    /**
     * 币的总数量
     */
    private double balance;
    /**
     * 该币归属的主链的ID
     */
    private String chainId;
    /**
     * 以BTC为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeBtc;
    /**
     * 以USD为计价单位的资产涨跌幅的百分比，例：-0.0123，则说明资产跌了1.23%
     */
    private double changeUsd;

    /**
     * EOS资产专用：name
     */
    private String accountName;
    /**
     * EOS资产专用：tag
     */
    private String accountTag;
    /**
     * 主链信息
     */
    private ChainCoin chain;
    /**
     * 当前资产币的信息
     */
    private ChainCoin coin;

    /**
     * 额外信息:
     * hide: 用来标记在钱包里显示还是隐藏
     */
    private WalletAssetBean.Option option;

    /**
     * 币ID
     * ETH coin id: 2
     * EOS coin id: 5
     */
    private int coinId;
    /**
     *
     */
    private double confirmations;
    /**
     * 币图标
     */
    private String icon;
    /**
     * 币全称
     */
    private String name;
    /**
     * btc单价
     */
    private double priceBtc;
    /**
     * usd单价
     */
    private double priceUsd;
    /**
     * 资产钱包地址
     */
    private String publicKey;
    /**
     * 币简称
     */
    private String symbol;
 ```

#### - getFee
* 参数：
    - pin: 用户pin码
    - assetId: 资产ID
    - publicKey: 钱包地址
    - label: 当为EOS时必传，其他币不用传，该值对应于{@link com.fox.one.opensdk.wallet.model.WalletAssetBean#accountName}，在mixin网络上默认为`eoswithmixin`
 
* response:
    - WithdrawFeeResponse:
 
 ```
   /**
     * 手续费
     */
    var amount: String? = ""
    /**
     * 资产ID
     */
    var assetId: String? = ""
    /**
     * coin ID
     */
    var coinId = 0
 ```

#### - hideWalletAsset
#### - showWalletAsset

## LuckyCoinAPI

#### - create
#### - publish

## PinAPI
#### - setPin
#### - modifyPin
#### - verifyPin