package es.iessaladillo.pedrojoya.intents.ui.battle

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class BattleActivityViewModel : ViewModel() {

    lateinit var pokemon1 : Pokemon
    lateinit var pokemon2 : Pokemon
    var whatPokemon : Int = 0

}