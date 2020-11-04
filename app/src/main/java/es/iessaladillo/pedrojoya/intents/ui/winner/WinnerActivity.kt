package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity

class WinnerActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON1 = "EXTRA_POKEMON1"
        const val EXTRA_POKEMON2 = "EXTRA_POKEMON2"

        fun newIntent(context: Context, id1 : Long, id2 : Long) =
            Intent(context, WinnerActivity::class.java)
                .putExtras(bundleOf(EXTRA_POKEMON1 to id1, EXTRA_POKEMON2 to id2))

    }

    private var id2 : Long = 0
    private var id1 : Long = 0
    private lateinit var pokemon1 : Pokemon
    private lateinit var pokemon2 : Pokemon
    private lateinit var binding: WinnerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupViews()
    }

    private fun setupViews() {
        winner()
    }

    private fun winner() {
        if (pokemon1.power > pokemon2.power){
            binding.imgPokemonWinner.setImageResource(pokemon1.image)
            binding.lblWinnerName.text = pokemon1.name
        }
        else if (pokemon1.power < pokemon2.power){
            binding.imgPokemonWinner.setImageResource(pokemon2.image)
            binding.lblWinnerName.text = pokemon2.name
        }
        else{
            binding.imgPokemonWinner.setImageResource(pokemon1.image)
            binding.lblWinnerName.text = pokemon1.name
        }
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(WinnerActivity.EXTRA_POKEMON1) || !intent.hasExtra(WinnerActivity.EXTRA_POKEMON2)) {
            throw RuntimeException()
        }
        id1 = intent.getLongExtra(WinnerActivity.EXTRA_POKEMON1, 0)
        id2 = intent.getLongExtra(WinnerActivity.EXTRA_POKEMON2, 0)
        pokemon1 = Database.getPokemonById(id1)!!
        pokemon2 = Database.getPokemonById(id2)!!
    }

}