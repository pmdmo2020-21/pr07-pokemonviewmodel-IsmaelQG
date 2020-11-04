package es.iessaladillo.pedrojoya.intents.data.local

import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import java.util.concurrent.ThreadLocalRandom

// TODO: Haz que Database implemente DataSource
object Database : DataSource{
    override fun getRandomPokemon(): Pokemon {
        var pokemonList = getAllPokemons()
        var random = ThreadLocalRandom.current().nextLong(0, 6)
        return pokemonList[random.toInt()]
    }

    override fun getAllPokemons(): List<Pokemon> {
        return listOf(
            Pokemon(10, R.drawable.bulbasur, 1, "Bulbasur"),
            Pokemon(100, R.drawable.giratina, 2, "Giratina"),
            Pokemon(50, R.drawable.cubone, 3, "Cubone"),
            Pokemon(90, R.drawable.gyarados, 4, "Gyarados"),
            Pokemon(1, R.drawable.feebas, 5, "Feebas"),
            Pokemon(30, R.drawable.pikachu, 6, "Pikachu"),
        )
    }

    override fun getPokemonById(id: Long): Pokemon? {
        var pokemonList = getAllPokemons()
        var pokemon : Pokemon? = null
        pokemonList.forEach{
            if (it.id == id){
                pokemon = it
            }
        }
        return pokemon
    }

}