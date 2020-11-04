package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val WHAT_POKEMON = "WHAT_POKEMON"

        fun newIntent(context: Context, id : Long, pokemon : Int) =
            Intent(context, SelectionActivity::class.java)
                .putExtras(bundleOf(EXTRA_POKEMON to id, WHAT_POKEMON to pokemon ))

    }

    private var id : Long = 0
    private var whatPokemon : Int = 0
    private var pokemon : Pokemon? = null
    private lateinit var binding: SelectionActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupViews()
    }

    private fun setupViews() {
        checkPokemon(pokemon)
        listeners()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_POKEMON) || !intent.hasExtra(WHAT_POKEMON)) {
            throw RuntimeException()
        }
        id = intent.getLongExtra(EXTRA_POKEMON, 0)
        whatPokemon = intent.getIntExtra(WHAT_POKEMON, 0)
        pokemon = Database.getPokemonById(id)
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
            id = 1
            binding.rdbBulbasur.isChecked = true}
        binding.rdbBulbasur.setOnClickListener { uncheckButtons(R.id.rdgRight)
            id = 1}

        binding.imgCubone.setOnClickListener { uncheckButtons(R.id.rdgRight)
            id = 3
            binding.rdbCubone.isChecked = true}
        binding.rdbCubone.setOnClickListener { uncheckButtons(R.id.rdgRight)
            id = 3}

        binding.imgFeebas.setOnClickListener { uncheckButtons(R.id.rdgRight)
            id = 5
            binding.rdbFeebas.isChecked = true}
        binding.rdbFeebas.setOnClickListener { uncheckButtons(R.id.rdgRight)
            id = 5}

        binding.imgGiratina.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 2
            binding.rdbGiratina.isChecked = true}
        binding.rdbGiratina.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 2}

        binding.imgGyarados.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 4
            binding.rdbGyarados.isChecked = true}
        binding.rdbGyarados.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 4}

        binding.imgPikachu.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 6
            binding.rdbPikachu.isChecked = true}
        binding.rdbPikachu.setOnClickListener { uncheckButtons(R.id.rdgLeft)
            id = 6}
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
        setResult(RESULT_OK, Intent().putExtras(bundleOf(EXTRA_POKEMON to id, WHAT_POKEMON to whatPokemon)))
        super.onBackPressed()
    }



}