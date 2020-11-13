package es.iessaladillo.pedrojoya.intents.data.local

import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import java.util.concurrent.ThreadLocalRandom

// TODO: Haz que Database implemente DataSource
object Database : DataSource{
    override fun getRandomPokemon(): Pokemon {
        val pokemonList = getAllPokemons()
        val random = ThreadLocalRandom.current().nextLong(0, 6)
        return pokemonList[random.toInt()]
    }

    override fun getAllPokemons(): List<Pokemon> {
        return listOf(
            Pokemon(10, R.drawable.bulbasur, 1, R.string.bulbasur),
            Pokemon(100, R.drawable.giratina, 2, R.string.giratina),
            Pokemon(50, R.drawable.cubone, 3, R.string.cubone),
            Pokemon(90, R.drawable.gyarados, 4, R.string.gyarados),
            Pokemon(1, R.drawable.feebas, 5, R.string.feebas),
            Pokemon(30, R.drawable.pikachu, 6, R.string.pikachu),
        )
    }

    override fun getPokemonById(id: Long): Pokemon? {
        val pokemonList = getAllPokemons()
        var pokemon : Pokemon? = null
        pokemonList.forEach{
            if (it.id == id){
                pokemon = it
            }
        }
        return pokemon
    }

}