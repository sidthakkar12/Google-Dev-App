package com.googledev.siddharththakkar.repositeries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.googledev.siddharththakkar.models.ProductModel
import java.util.*

class ProductRepository {

    companion object {
        private var productRepository: ProductRepository? = null
        fun getinstance(): ProductRepository? {
            if (productRepository == null) {
                productRepository = ProductRepository()
            }
            return productRepository
        }
    }

    private var productModelLiveData: MutableLiveData<ArrayList<ProductModel>>? = null
    val productList: MutableLiveData<ArrayList<ProductModel>>?
        get() {
            val productModels = ArrayList<ProductModel>()
            productModelLiveData = MutableLiveData()
            productModels.add(ProductModel(1, "Product One", "This is product one", 10, 0, 4.9F))
            productModels.add(ProductModel(2, "Product Two", "This is product two", 22, 0, 4F))
            productModels.add(ProductModel(3, "Product Three", "This is product three", 15, 0, 4.5F))
            productModels.add(ProductModel(4, "Product Four", "This is product four", 30, 0, 4F))
            productModels.add(ProductModel(5, "Product Five", "This is product five", 15, 0, 3.5F))
            productModels.add(ProductModel(6, "Product Six", "This is product six", 5, 0, 4.5F))
            productModels.add(ProductModel(7, "Product Seven", "This is product seven", 15, 0, 3F))
            productModels.add(ProductModel(8, "Product Eight", "This is product eight", 10, 0, 4F))
            productModels.add(ProductModel(9, "Product Nine", "This is product nine", 25, 0, 4.5F))
            productModels.add(ProductModel(10, "Product Ten", "This is product ten", 33, 0,4F))
            productModelLiveData!!.value = productModels
            return productModelLiveData
        }
}