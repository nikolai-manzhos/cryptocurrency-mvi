package com.defaultapps.cryptocurrency.data.entity

import com.google.gson.annotations.SerializedName

class Currency {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("symbol")
    var symbol: String? = null
    @SerializedName("rank")
    var rank: String? = null
    @SerializedName("price_usd")
    var priceUsd: String? = null
    @SerializedName("price_btc")
    var priceBtc: String? = null
    @SerializedName("24h_volume_usd")
    var volume24h:String? = null
    @SerializedName("market_cap_usd")
    var marketCapUsd: String? = null
    @SerializedName("available_supply")
    var availableSupply: String? = null
    @SerializedName("total_supply")
    var totalSupply: String? = null
    @SerializedName("percent_change_1h")
    var percentChange1h: String? = null
    @SerializedName("percent_change_24h")
    var percentChange24h: String? = null
    @SerializedName("percent_change_7d")
    var percentChange7d: String? = null
    @SerializedName("last_updated")
    var lastUpdated: String? = null
}
