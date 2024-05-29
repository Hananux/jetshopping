package com.example.jetshopping

import android.content.Context
import com.example.jetshopping.data.room.models.ShoppingListDatabase
import com.example.jetshopping.ui.theme.repository.Repository

object Graph {
    lateinit var db: ShoppingListDatabase
        private set
    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            storeDao = db.storeDao(),
            itemDao = db.itemDao()
        )
    }

    fun provide(context: Context) {
        db = ShoppingListDatabase.getDatabase(context)

    }

}