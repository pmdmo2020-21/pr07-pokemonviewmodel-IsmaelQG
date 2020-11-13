package es.iessaladillo.pedrojoya.intents.ui.selection

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class SelectionActivityViewModel: ViewModel() {

    var whatPokemon : Int = 0
    var pokemon : Pokemon? = null

}