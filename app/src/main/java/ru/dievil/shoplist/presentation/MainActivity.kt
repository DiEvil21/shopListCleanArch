package ru.dievil.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.dievil.shoplist.R
import ru.dievil.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListadapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecylerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListadapter.submitList(it)
            }

        }

    private fun setupRecylerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListadapter = ShopListAdapter()
            adapter = shopListadapter
            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setOnShopItemLongClickListener()
        setOnShopItemClickListener()
        setSwipeListener(rvShopList)
    }

    private fun setSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListadapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopListItem(item)
            }
        }
        val itemTochHelper = ItemTouchHelper(callback)
        itemTochHelper.attachToRecyclerView(rvShopList)
    }

    private fun setOnShopItemClickListener() {
        shopListadapter.onShopItemClickListener = {
            Log.d("MainActivity", it.toString())
        }
    }

    private fun setOnShopItemLongClickListener() {
        shopListadapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}










    //медленный способ, аналог recyclerView
//    private fun showList(list: List<ShopItem>) {
//        llshopList.removeAllViews()
//        for (shopItem in list) {
//            val layoutId = if (shopItem.enabled) {
//                R.layout.item_shop_enabled
//            }else {
//                R.layout.item_shop_disabled
//            }
//            val view = LayoutInflater.from(this).inflate(layoutId,llshopList,false)
//            val tvName = view.findViewById<TextView>(R.id.tv_name)
//            val tvCount = view.findViewById<TextView>(R.id.tv_count)
//            tvName.text = shopItem.name
//            tvCount.text = shopItem.count.toString()
//            view.setOnLongClickListener {
//                viewModel.changeEnableState(shopItem)
//                true
//            }
//            llshopList.addView(view)
//        }
//    }
