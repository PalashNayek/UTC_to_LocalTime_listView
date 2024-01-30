package com.palash.utc_to_localtime_listview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.palash.utc_to_localtime_listview.R
import com.palash.utc_to_localtime_listview.models.ProgrammingModel
import java.text.SimpleDateFormat
import java.util.*

class ProductAdapter : ListAdapter<ProgrammingModel, ProductAdapter.ProductViewHolder>(DiffCallback()) {

    class ProductViewHolder(view: View) : ViewHolder(view) {
        private val productName = view.findViewById<TextView>(R.id.productName)
        private val productDate = view.findViewById<TextView>(R.id.createdData)

        fun convertUtcToIst(utcTime: String): String {
            val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            utcFormat.timeZone = TimeZone.getTimeZone("UTC")

            val date = utcFormat.parse(utcTime) ?: return "Invalid Date Format"

            val istFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            istFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")

            return istFormat.format(date)
        }

        fun bind(item: ProgrammingModel) {
            productName.text = item.name
            val utcTime = item.date
            val istTime = convertUtcToIst(utcTime)
            productDate.text = istTime

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<ProgrammingModel>() {
        override fun areItemsTheSame(
            oldItem: ProgrammingModel,
            newItem: ProgrammingModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProgrammingModel,
            newItem: ProgrammingModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}