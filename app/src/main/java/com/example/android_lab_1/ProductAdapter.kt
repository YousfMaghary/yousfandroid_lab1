package com.example.android_lab_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ProductAdapter(
    private val allProducts: List<Product>,
    private val onCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var shown: List<Product> = allProducts

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgProduct)
        val name: TextView = view.findViewById(R.id.tvName)
        val price: TextView = view.findViewById(R.id.tvPrice)
        val cart: ImageView = view.findViewById(R.id.imgCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = shown[position]
        holder.image.setImageResource(product.imageRes)
        holder.name.text = product.name
        holder.price.text = String.format(Locale.US, "US$%.2f", product.price)
        holder.cart.setOnClickListener { onCartClick(product) }
    }

    override fun getItemCount(): Int = shown.size

    fun filter(query: String) {
        val q = query.trim()
        shown = if (q.isEmpty()) allProducts
        else allProducts.filter { it.name.contains(q, ignoreCase = true) }
        notifyDataSetChanged()
    }
}
