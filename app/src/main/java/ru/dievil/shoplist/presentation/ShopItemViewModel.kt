package ru.dievil.shoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dievil.shoplist.data.ShopListRepositoryImpl
import ru.dievil.shoplist.domain.AddShopItemUseCase
import ru.dievil.shoplist.domain.EditShopItemUseCase
import ru.dievil.shoplist.domain.GetShopItemUseCase
import ru.dievil.shoplist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopitem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopitem

    private val _shouldCLoseScreen = MutableLiveData<Unit>()
    val shouldCLoseScreen: LiveData<Unit>
        get() = _shouldCLoseScreen


    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopitem.value = item
    }
    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }

    }
    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
            val shopItem = _shopitem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }

        }

    }


    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        }catch (e: Exception) {
            0
        }
    }
    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value  = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName () {
        _errorInputName.value = false
    }

    public fun resetErrorInputCOunt () {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCLoseScreen.value = Unit
    }
}