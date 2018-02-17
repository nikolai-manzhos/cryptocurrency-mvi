package com.defaultapps.cryptocurrency.domain.model

data class Currency(val id: String,
                    val name: String,
                    val price: String,
                    val percentChange1h: Float,
                    val percentChange24: Float,
                    val percentChange7d: Float)
