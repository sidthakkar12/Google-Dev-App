package com.googledev.siddharththakkar.viewmodels

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.googledev.siddharththakkar.models.ProductModel
import com.googledev.siddharththakkar.repositeries.ProductRepository
import java.util.*

class ProductListViewModel : ViewModel() {
    var productRepository: ProductRepository? = null

    companion object {
        private var mProductListViewModel: ProductListViewModel? = null
        var count = 1
        fun getInstance(activity: FragmentActivity): ProductListViewModel? {
            if (mProductListViewModel == null) {
                mProductListViewModel = ViewModelProviders.of(activity).get(ProductListViewModel::class.java)
            }
            return mProductListViewModel
        }
    }

    fun initRepository() {
        productRepository = ProductRepository.getinstance()
        getProductMutableLiveData = productRepository?.productList
    }

    var getProductMutableLiveData: MutableLiveData<ArrayList<ProductModel>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
}