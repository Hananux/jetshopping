package com.example.jetshopping.data.room.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item:Item)
    @Delete
    suspend fun delete(item:Item)

    @Query("SELECT * FROM items")

    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE item_id =:itemId")
    fun getItem(itemId:Int):Flow<Item>




}

@Dao
interface StoreDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(store: store)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(store: store )
    @Delete
    suspend fun delete(item:Item)

    @Query("SELECT * FROM stores")

    fun getAllStores(): Flow<List<store>>

    @Query("SELECT * FROM stores WHERE store_id =:storeId")
    fun getStore(storeId:Int):Flow<store>

}

@Dao
interface ListDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Query("""
        SELECT * FROM items AS I INNER JOIN shopping_list AS S
            ON I.listIdFk = S.list_id INNER JOIN stores AS ST
            ON I.storeIdFk = ST.store_id
        """)
            fun getItemWithStoreAndList(): Flow<List<ItemsWithStoreAndList>>
    @Query("""
        SELECT * FROM items AS I INNER JOIN shopping_list AS S
            ON I.listIdFk = S.list_id INNER JOIN stores AS ST
            ON I.storeIdFk = ST.store_id WHERE S.list_id =:listId
        """)
    fun getItemsWithStoreAndListFilteredById(listId:Int)
            :Flow<List<ItemsWithStoreAndList>>

    @Query("""
        SELECT * FROM items AS I INNER JOIN shopping_list AS S
            ON I.listIdFk = S.list_id INNER JOIN stores AS ST
            ON I.storeIdFk = ST.store_id WHERE I.item_id=:itemId
        """)
    fun getItemWithStoreAndListFilteredById(itemId: Int)
            :Flow<ItemsWithStoreAndList>
}

data class ItemsWithStoreAndList(
    @Embedded val item: Item,
    @Embedded val shoppingList: ShoppingList,
    @Embedded val store: store,
)