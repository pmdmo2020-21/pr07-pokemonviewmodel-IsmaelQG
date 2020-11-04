package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity


private const val RC_SELECTION_ACTIVITY: Int = 1
private const val RC_WINNER_ACTIVITY: Int = 2

class BattleActivity : AppCompatActivity() {

    private lateinit var binding: BattleActivityBinding
    private lateinit var pokemon1 : Pokemon
    private lateinit var pokemon2 : Pokemon
    private var id : Long = 0
    private var pokemon : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        binding.llPokemon1.setOnClickListener {navigateToSelectionActivityPokemon(pokemon1.id, 1)}
        binding.llPokemon2.setOnClickListener {navigateToSelectionActivityPokemon(pokemon2.id, 2)}
    }

    private fun setupViews() {
        binding.btnStart.setOnClickListener{}
        setPokemon1(Database.getRandomPokemon())
        setPokemon2(Database.getRandomPokemon())
        binding.btnStart.setOnClickListener { navigateToWinnerActivityPokemon(pokemon1.id, pokemon2.id) }
    }

    private fun setPokemon1(pokemon: Pokemon) {
        pokemon1 = pokemon
        binding.imgPokemon1.setImageResource(pokemon.image)
        binding.lblPokemon1.text = pokemon.name
    }

    private fun setPokemon2(pokemon: Pokemon) {
        pokemon2 = pokemon
        binding.imgPokemon2.setImageResource(pokemon.image)
        binding.lblPokemon2.text = pokemon.name
    }

    private fun navigateToSelectionActivityPokemon(id: Long, pokemon: Int) {
        val intent = SelectionActivity.newIntent(this, id, pokemon)
        startActivityForResult(intent, RC_SELECTION_ACTIVITY)
    }

    private fun navigateToWinnerActivityPokemon(id1: Long, id2 : Long) {
        val intent = WinnerActivity.newIntent(this, id1, id2)
        startActivityForResult(intent, RC_WINNER_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_SELECTION_ACTIVITY && intent != null) {
            extractResult(intent)
            if(pokemon == 1){
                setPokemon1(Database.getPokemonById(id)!!)
            }
            if(pokemon == 2){
                setPokemon2(Database.getPokemonById(id)!!)
            }
        }
    }

    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(SelectionActivity.EXTRA_POKEMON) ||
            !intent.hasExtra(SelectionActivity.WHAT_POKEMON)) {
            throw RuntimeException()
        }
        id = intent.getLongExtra(SelectionActivity.EXTRA_POKEMON, 0)
        pokemon = intent.getIntExtra(SelectionActivity.WHAT_POKEMON, 0)
    }

}