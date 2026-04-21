package com.xluis.adviceapp.Presentation.Screens.FavourtiesScreen

import com.xluis.adviceapp.Data.Mappers.toDomain
import com.xluis.adviceapp.Presentation.BaseScreenViewModel
import com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites.GetAllJokeFavourites
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesScreenViewModel @Inject constructor(
    private val getAllJokeFavourites: GetAllJokeFavourites
) : BaseScreenViewModel<FavouritesScreenUiState, FavouritesScreenUiEvent, FavouritesScrenUiEffect>
    (initialState = FavouritesScreenUiState()) {

        init {
            showToast = ::showToast
           collectFlow(
               flow = getAllJokeFavourites(),
               onCollect = {
                   updateState {
                       copy(favouritesJokes = it.map { it.toDomain() })
                   }
               }
           )
        }
    override fun onEvent(event: FavouritesScreenUiEvent) {
        when(event){
            else -> {}
        }
    }

    private fun showToast (message : String) {
        sendEffect(FavouritesScrenUiEffect.ShowToast(message))
    }


}