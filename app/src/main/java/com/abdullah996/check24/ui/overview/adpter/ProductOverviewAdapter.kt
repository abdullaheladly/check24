package com.abdullah996.check24.ui.overview.adpter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abdullah996.check24.R
import com.abdullah996.check24.data.model.Product
import com.abdullah996.check24.ui.overview.OnClickListeners
import com.abdullah996.check24.utill.ProductsDiffUtil

class ProductOverviewAdapter(onClickListeners: OnClickListeners) :RecyclerView.Adapter<ProductOverviewAdapter.MyViewHolder>(){
    private var productList= emptyList<Product>()
    private val onClickListeners=onClickListeners

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_cardview,parent,false))
    }

    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (productList[position].available==true){
            holder.itemView.findViewById<ImageView>(R.id.product_image).load(productList[position].imageURL){
                crossfade(600)
            }
        }
        else{
            holder.itemView.findViewById<ImageView>(R.id.product_image).visibility=View.GONE
            holder.itemView.findViewById<ImageView>(R.id.product_image_notAvalabile).visibility=View.VISIBLE
            holder.itemView.findViewById<ImageView>(R.id.product_image_notAvalabile).load(productList[position].imageURL){
                crossfade(600)
            }
        }
        holder.itemView.findViewById<TextView>(R.id.txt_product_name).text=productList[position].name.toString()
        holder.itemView.findViewById<TextView>(R.id.txt_product_date).text=productList[position].releaseDate.toString()
        holder.itemView.findViewById<TextView>(R.id.txt_product_Price_num).text=productList[position].price.value.toString()+" ${productList[position].price.currency}"
        holder.itemView.findViewById<TextView>(R.id.txt_product_desc).text=productList[position].description.toString()
        holder.itemView.findViewById<RatingBar>(R.id.product_rating).numStars=5
        holder.itemView.findViewById<RatingBar>(R.id.product_rating).rating=productList[position].rating.toFloat()
        holder.itemView.setOnClickListener {
            onClickListeners.onProductItemClick(productList[position])
        }
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    fun saveData(newProductsList:List<Product>){
        val productDiffUtil=ProductsDiffUtil(productList,newProductsList)
        val diffUtilResult=DiffUtil.calculateDiff(productDiffUtil)
        productList=newProductsList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}