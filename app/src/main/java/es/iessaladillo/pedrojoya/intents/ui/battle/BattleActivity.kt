package es.iessaladillo.pedrojoya.intents.ui.battle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

private const val STATE_POKEMON_1 = "STATE_POKEMON_1"
private const val STATE_POKEMON_2 = "STATE_POKEMON_2"

class BattleActivity : AppCompatActivity() {

    private lateinit var binding: BattleActivityBinding
    private val viewModel: BattleActivityViewModel by viewModels()

    private val selectionActivityCall=
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            val resultIntent = result.data
            if (result.resultCode == Activity.RESULT_OK && resultIntent != null) {
                extractResult(resultIntent)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        if (savedInstanceState != null){
            viewModel.pokemon1 = (savedInstanceState.getParcelable(STATE_POKEMON_1) as Pokemon?)!!
            viewModel.pokemon2 = (savedInstanceState.getParcelable(STATE_POKEMON_2) as Pokemon?)!!
        }
    }

    override fun onStart() {
        super.onStart()
        binding.llPokemon1.setOnClickListener {navigateToSelectionActivityPokemon(viewModel.pokemon1, 1)}
        binding.llPokemon2.setOnClickListener {navigateToSelectionActivityPokemon(viewModel.pokemon2, 2)}
    }

    private fun setupViews() {
        binding.btnStart.setOnClickListener{}
        setPokemon1(Database.getRandomPokemon())
        setPokemon2(Database.getRandomPokemon())
        binding.btnStart.setOnClickListener { navigateToWinnerActivityPokemon(viewModel.pokemon1, viewModel.pokemon2) }
    }

    private fun setPokemon1(pokemon: Pokemon) {
        viewModel.pokemon1 = pokemon
        binding.imgPokemon1.setImageResource(pokemon.image)
        binding.lblPokemon1.setText(pokemon.name)
    }

    private fun setPokemon2(pokemon: Pokemon) {
        viewModel.pokemon2 = pokemon
        binding.imgPokemon2.setImageResource(pokemon.image)
        binding.lblPokemon2.setText(pokemon.name)
    }

    private fun navigateToSelectionActivityPokemon(pokemon: Pokemon, whatPokemon: Int) {
        val intent = SelectionActivity.newIntent(this, pokemon, whatPokemon)
        selectionActivityCall.launch(intent)
    }

    private fun navigateToWinnerActivityPokemon(pokemon1: Pokemon, pokemon2 : Pokemon) {
        val intent = WinnerActivity.newIntent(this, pokemon1, pokemon2)
        selectionActivityCall.launch(intent)
    }

    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(SelectionActivity.EXTRA_POKEMON) ||
            !intent.hasExtra(SelectionActivity.WHAT_POKEMON)) {
            throw RuntimeException()
        }
        viewModel.whatPokemon = intent.getIntExtra(SelectionActivity.WHAT_POKEMON, 0)
        if(viewModel.whatPokemon == 1){
            setPokemon1(intent.getParcelableExtra(SelectionActivity.EXTRA_POKEMON)!!)
        }
        if(viewModel.whatPokemon == 2){
            setPokemon2(intent.getParcelableExtra(SelectionActivity.EXTRA_POKEMON)!!)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_POKEMON_1, viewModel.pokemon1)
        outState.putParcelable(STATE_POKEMON_2, viewModel.pokemon2)
    }

}