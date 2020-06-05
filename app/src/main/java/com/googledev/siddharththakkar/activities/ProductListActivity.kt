package com.googledev.siddharththakkar.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.googledev.siddharththakkar.R
import com.googledev.siddharththakkar.adapter.ProductListAdapter
import com.googledev.siddharththakkar.utils.AppConstants
import com.googledev.siddharththakkar.utils.BaseActivity
import com.googledev.siddharththakkar.utils.darktheme.DarkTheme
import com.googledev.siddharththakkar.utils.quitApp
import com.googledev.siddharththakkar.utils.showToast
import com.googledev.siddharththakkar.viewmodels.ProductListViewModel
import kotlinx.android.synthetic.main.activity_product_list.*


class ProductListActivity : BaseActivity(), View.OnClickListener {
    private val TAG = ProductListActivity::class.java.simpleName
    private lateinit var mProductListAdapter: ProductListAdapter
    private lateinit var mProductListViewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        initComponents()
        setListeners()
        initViewModelData()
    }

    private fun initComponents() {
        val nightModeFlags: Int =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            sharedPreferenceUtil?.setBooleanPreference(AppConstants.PREF_THEME, true)
        } else {
            sharedPreferenceUtil?.setBooleanPreference(AppConstants.PREF_THEME, false)
        }
        switchDarkMode.isChecked =
            sharedPreferenceUtil?.getBooleanPreference(AppConstants.PREF_THEME)!!
    }

    private fun setListeners() {
        fabCart.setOnClickListener(this)
        switchDarkMode.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                DarkTheme.apply(enabled = true)
            } else {
                DarkTheme.apply(enabled = false)
            }
            sharedPreferenceUtil?.setBooleanPreference(AppConstants.PREF_THEME, isChecked)
        }
    }

    private fun initViewModelData() {
        mProductListViewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        mProductListViewModel.initRepository()
        mProductListViewModel.getProductMutableLiveData
            ?.observe(
                this,
                Observer { mProductListAdapter.notifyDataSetChanged() })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mProductListAdapter = ProductListAdapter(
            this,
            mProductListViewModel.getProductMutableLiveData?.value!!,
            mProductListViewModel
        )
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        recyclerViewProducts.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerViewProducts.adapter = mProductListAdapter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabCart -> showToast(this, resources.getString(R.string.str_redirect_cart_screen))
        }
    }

    override fun onBackPressed() {
        quitApp(this)
    }
}