package com.defaultapps.cryptocurrency.domain.model

data class Currency(val id: String,
                    val name: String,
                    val price: String,
                    val percentChange24: Float)
