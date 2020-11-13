package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

private const val STATE_POKEMON = "STATE_POKEMON"

class SelectionActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val WHAT_POKEMON = "WHAT_POKEMON"

        fun newIntent(context: Context, pokemon: Pokemon, whatPokemon : Int) =
            Intent(context, SelectionActivity::class.java)
                .putExtras(bundleOf(EXTRA_POKEMON to pokemon, WHAT_POKEMON to whatPokemon ))

    }

    private lateinit var binding: SelectionActivityBinding
    private val viewModel: SelectionActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupViews()
        if (savedInstanceState != null){
            viewModel.pokemon = (savedInstanceState.getParcelable(STATE_POKEMON) as Pokemon?)!!
        }
    }

    private fun setupViews() {
        checkPokemon(viewModel.pokemon)
        listeners()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_POKEMON) || !intent.hasExtra(WHAT_POKEMON)) {
            throw RuntimeException()
        }
        viewModel.whatPokemon = intent.getIntExtra(WHAT_POKEMON, 0)
        viewModel.pokemon = intent.getParcelableExtra(EXTRA_POKEMON)
    }

    private fun checkPokemon(pokemon: Pokemon?) {
        if (pokemon != null) {
            when(pokemon.id){
                1L -> binding.rdbBulbasur.isChecked = true
                2L -> binding.rdbGiratina.isChecked = true
                3L -> binding.rdbCubone.isChecked = true
                4L -> binding.rdbGyarados.isChecked = true
                5L -> binding.rdbFeebas.isChecked = true
                6L -> binding.rdbPikachu.isChecked = true
            }
        }
    }

    private fun listeners() {
        binding.imgBulbasur.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(1)
            binding.rdbBulbasur.isChecked = true}
        binding.rdbBulbasur.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(1)}

        binding.imgCubone.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(3)
            binding.rdbCubone.isChecked = true}
        binding.rdbCubone.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(3)}

        binding.imgFeebas.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(5)
            binding.rdbFeebas.isChecked = true}
        binding.rdbFeebas.setOnClickListener { uncheckButtons(R.id.rdgRight)
            viewModel.pokemon = Database.getPokemonById(5)}

        binding.imgGiratina.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(2)
            binding.rdbGiratina.isChecked = true}
        binding.rdbGiratina.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(2)}

        binding.imgGyarados.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(4)
            binding.rdbGyarados.isChecked = true}
        binding.rdbGyarados.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(4)}

        binding.imgPikachu.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(6)
            binding.rdbPikachu.isChecked = true}
        binding.rdbPikachu.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            viewModel.pokemon = Database.getPokemonById(6)}
    }

    private fun uncheckButtons(rdg: Int) {

        if(rdg == R.id.rdgLeft){
            binding.rdgLeft.clearCheck()
        }
        else if(rdg == R.id.rdgRight){
            binding.rdgRight.clearCheck()
        }

    }

    override fun onBackPressed() {
        // TODO: AQUÍ ES DONDE DEBES ESTABLECER EL RESULTADO CON setResult()
        setResult(RESULT_OK, Intent().putExtras(bundleOf(EXTRA_POKEMON to viewModel.pokemon, WHAT_POKEMON to viewModel.whatPokemon)))
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_POKEMON, viewModel.pokemon)
    }

}