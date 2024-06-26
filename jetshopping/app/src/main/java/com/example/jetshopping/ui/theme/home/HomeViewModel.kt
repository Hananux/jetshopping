package com.example.jetshopping.ui.theme.home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetshopping.Graph
import com.example.jetshopping.data.room.models.ItemsWithStoreAndList

import com.example.jetshopping.data.room.models.Category
import com.example.jetshopping.data.room.models.Item
import com.example.jetshopping.ui.theme.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: Repository = Graph.repository
):ViewModel(){
    var state by mutableStateOf(HomeState())
        private set
    init {
        getItems()
    }
     private fun getItems(){
         viewModelScope.launch {
             repository.getItemsWithListAndStore.collectLatest {
                 state = state.copy(
                     items = it
                 )
             }
         }
     }
      fun deleteItem(item: Item){
          viewModelScope.launch {
              repository.deleteItem(item)

          }
      }

    fun onCreateChange(category: Category){
        state = state.copy(category = category)
        filterBy(category.id)
    }

    private fun filterBy(ShoppingListId:Int){
        if(ShoppingListId != 10001){
            viewModelScope.launch {
                repository.getItemWithStoreAndListFilteredById(
                    ShoppingListId
                ).collectLatest {
                    state = state.copy(items = it)
                }
            }

        }else{
            getItems()
        }
    }




}

    data class HomeState(
        val items:List<ItemsWithStoreAndList> = emptyList(),
        val category: Category = Category(),
        val itemChecked:Boolean = false
    )
