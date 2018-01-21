package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.data.entity.CurrencyResponse
import com.defaultapps.cryptocurrency.domain.model.Currency
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.utils.Constants
import com.defaultapps.cryptocurrency.utils.ResUtils
import kotlinx.android.synthetic.main.item_currency.view.*
import javax.inject.Inject

@PerScreen
class OverviewAdapter @Inject constructor(private val resUtils: ResUtils)
    : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val items = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyViewHolder {
        val view =  LayoutInflater.from(parent!!.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder?, position: Int) {
        val aPosition = holder!!.adapterPosition
        val currency = items[aPosition]
        Glide
                .with(holder.itemView)
                .load(Constants.IMAGE_BASE_URL + currency.id + Constants.IMAGE_FORMAT)
                .into(holder.itemView.image)
        holder.itemView.name.text = currency.name
        holder.itemView.price.text = currency.price


        if (currency.percentChange24 < 0F) {
            configurePriceChangeView(holder, resUtils.getColor(R.color.red),
                    R.drawable.ic_trending_down_red_24dp)
        } else {
            configurePriceChangeView(holder, resUtils.getColor(R.color.green),
                    R.drawable.ic_trending_up_green_24dp)
        }
        holder.itemView.priceChange.text = currency.percentChange24.toString()
    }

    private fun configurePriceChangeView(holder: CurrencyViewHolder, color: Int, drawableId: Int) {
        holder.itemView.priceChange.setTextColor(color)
        holder.itemView.priceChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableId, 0)
    }

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Currency>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}