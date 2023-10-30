package ru.dievil.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dievil.shoplist.data.ShopListRepositoryImpl
import ru.dievil.shoplist.domain.DeleteShopItemUseCase
import ru.dievil.shoplist.domain.EditShopItemUseCase
import ru.dievil.shoplist.domain.GetShopListUseCase
import ru.dievil.shoplist.domain.ShopItem

class MainViewModel : ViewModel() {
    //потом поменять
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopListItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}