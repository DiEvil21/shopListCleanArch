package ru.dievil.shoplist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.dievil.shoplist.domain.ShopItem

class ShopListDiffCallback(
    private val oldlist: List<ShopItem>,
    private val newList: List<ShopItem>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldlist[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldlist[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }


}