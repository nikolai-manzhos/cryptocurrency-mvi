package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.domain.model.Currency
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.utils.Constants
import com.defaultapps.cryptocurrency.utils.ResUtils
import com.defaultapps.cryptocurrency.utils.extensions.loadSimple
import kotlinx.android.synthetic.main.item_currency.view.*
import javax.inject.Inject

@PerScreen
class OverviewAdapter @Inject constructor(private val resUtils: ResUtils)
    : RecyclerView.Adapter<CurrencyViewHolder>() {

    companion object {
        private const val MIN_CHANGE_THRESHOLD = 0F
    }

    private val items = mutableListOf<Currency>()
    private var listener: CurrencyListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_currency, parent, false)
        val vh = CurrencyViewHolder(view)
        vh.itemView.setOnClickListener {
            val currency = items[vh.adapterPosition]
            listener?.onCurrencyClick(currency.id)
        }
        return vh
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder?, position: Int) {
        val aPosition = holder!!.adapterPosition
        val currency = items[aPosition]
        if (currency.percentChange24 < MIN_CHANGE_THRESHOLD) {
            val params = CurrencyParams(resUtils.getColor(R.color.red), R.drawable.ic_trending_down_red_24dp)
            bindPriceChangeView(holder.itemView, params, currency)
        } else {
            val params = CurrencyParams(resUtils.getColor(R.color.green), R.drawable.ic_trending_up_green_24dp)
            bindPriceChangeView(holder.itemView, params, currency)
        }
    }

    private fun bindPriceChangeView(itemView: View, params: CurrencyParams, currency: Currency) {
        itemView.image
                .loadSimple(Constants.IMAGE_BASE_URL + currency.id + Constants.IMAGE_FORMAT)
        itemView.name.text = currency.name
        itemView.price.text = currency.price
        itemView.priceChange.setTextColor(params.color)
        itemView.priceChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, params.drawableId, 0)
        itemView.priceChange.text = currency.percentChange24.toString()
    }

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Currency>) {
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }

    fun setCurrencyListener(listener: CurrencyListener) {
        this.listener = listener
    }

    class CurrencyParams(val color: Int, val drawableId: Int)

    interface CurrencyListener {
        fun onCurrencyClick(id: String)
    }
}
