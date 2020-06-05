package com.googledev.siddharththakkar.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.googledev.siddharththakkar.R
import com.googledev.siddharththakkar.adapter.ProductListAdapter.MyViewHolder
import com.googledev.siddharththakkar.models.ProductModel
import com.googledev.siddharththakkar.utils.showToast
import com.googledev.siddharththakkar.viewmodels.ProductListViewModel
import kotlinx.android.synthetic.main.row_product_list.view.*
import java.util.*

class ProductListAdapter(
    private val mActivity: FragmentActivity,
    private val mProductModelArrayList: ArrayList<ProductModel>,
    private val mProductListViewModel: ProductListViewModel
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_product_list, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        holder.bind(mProductModelArrayList[pos])
    }

    override fun getItemCount(): Int {
        return mProductModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.relativeParent.setOnClickListener(this)
            itemView.imgAddToCart.setOnClickListener(this)
            itemView.imgAdd.setOnClickListener(this)
            itemView.imgMinus.setOnClickListener(this)
        }

        fun bind(productModel: ProductModel) {
            itemView.txtProductName.text = productModel.name
            itemView.txtProductPrice.text = "$${productModel.price}"
            itemView.txtProductDescription.text = productModel.description
            itemView.txtQuantity.text = "${productModel.quantity}"
        }

        override fun onClick(view: View?) {
            when (view?.id) {

                R.id.relativeParent -> showToast(
                    mActivity,
                    mActivity.getString(R.string.str_redirect)
                )

                R.id.imgAddToCart -> showToast(
                    mActivity,
                    mActivity.getString(R.string.str_product_added_to_cart)
                )

                R.id.imgAdd -> {
                    mProductModelArrayList[adapterPosition].quantity =
                        itemView.txtQuantity.text.toString().toInt() + 1
                    mProductModelArrayList[adapterPosition] =
                        mProductModelArrayList[adapterPosition]
                    mProductListViewModel.getProductMutableLiveData?.value = mProductModelArrayList
                    ProductListViewModel.count++
                }

                R.id.imgMinus -> {
                    if (itemView.txtQuantity.text.toString().toInt() > 0) {
                        mProductModelArrayList[adapterPosition].quantity =
                            itemView.txtQuantity.text.toString().toInt() - 1
                        mProductModelArrayList[adapterPosition] =
                            mProductModelArrayList[adapterPosition]
                        mProductListViewModel.getProductMutableLiveData?.value =
                            mProductModelArrayList
                    }
                }
            }
        }
    }
}