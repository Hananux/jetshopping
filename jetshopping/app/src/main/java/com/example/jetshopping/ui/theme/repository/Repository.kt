package com.example.jetshopping.ui.theme.repository

import com.example.jetshopping.data.room.models.Item
import com.example.jetshopping.data.room.models.ItemDao
import com.example.jetshopping.data.room.models.ListDao
import com.example.jetshopping.data.room.models.ShoppingList
import com.example.jetshopping.data.room.models.StoreDao
import com.example.jetshopping.data.room.models.store

class Repository (

    private val listDao: ListDao,
    private val storeDao: StoreDao,
    private val itemDao:ItemDao
    ) {


    val store = storeDao.getAllStores()
    val getItemsWithListAndStore = listDao.getItemWithStoreAndList()
    fun getItemWithStoreAndList(id:Int) = listDao
        .getItemsWithStoreAndListFilteredById(id)
    fun getItemWithStoreAndListFilteredById(id: Int) =
      listDao.getItemsWithStoreAndListFilteredById(id)
    suspend fun insertlist(shoppingList: ShoppingList){
        listDao.insertShoppingList(shoppingList)
    }
    suspend fun insertStore(store: store){
        storeDao.insert(store)
    }
    suspend fun insertItem(item: Item){
        itemDao.insert(item)
    }
    suspend fun deleteItem(item: Item){
        itemDao.delete(item)
    }
    suspend fun  updateItem(item: Item){
        itemDao.update(item)
    }
}