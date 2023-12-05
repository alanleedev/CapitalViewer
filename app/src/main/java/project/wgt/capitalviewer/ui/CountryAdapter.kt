package project.wgt.capitalviewer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import project.wgt.capitalviewer.R
import project.wgt.capitalviewer.model.CountryItem


object CountryItemDiffCallback: DiffUtil.ItemCallback<CountryItem>() {
    override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem == newItem
    }
}
class CountryAdapter: ListAdapter<CountryItem, CountryItemViewHolder>(CountryItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.country_item,
            parent,
            false
        )
        return CountryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CountryItemViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
    private val nameRegion = itemView.findViewById<TextView>(R.id.name_region)
    private val code = itemView.findViewById<TextView>(R.id.code)
    private val capital = itemView.findViewById<TextView>(R.id.capital)
    fun bind(countryItem: CountryItem) {
        nameRegion.text = "${countryItem.name}, ${countryItem.region}"
        code.text = "${countryItem.code}"
        capital.text = "${countryItem.capital}"
    }
}
