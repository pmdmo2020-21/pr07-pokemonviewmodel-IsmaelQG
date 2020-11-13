package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

private const val STATE_POKEMON_1 = "STATE_POKEMON_1"
private const val STATE_POKEMON_2 = "STATE_POKEMON_2"

class WinnerActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON1 = "EXTRA_POKEMON1"
        const val EXTRA_POKEMON2 = "EXTRA_POKEMON2"

        fun newIntent(context: Context, pokemon1 : Pokemon, pokemon2 : Pokemon) =
            Intent(context, WinnerActivity::class.java)
                .putExtras(bundleOf(EXTRA_POKEMON1 to pokemon1, EXTRA_POKEMON2 to pokemon2))

    }

    private lateinit var binding: WinnerActivityBinding
    private val viewModel: WinnerActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupViews()
        if (savedInstanceState != null){
            viewModel.pokemon1 = (savedInstanceState.getParcelable(STATE_POKEMON_1) as Pokemon?)!!
            viewModel.pokemon2 = (savedInstanceState.getParcelable(STATE_POKEMON_2) as Pokemon?)!!
        }
    }

    private fun setupViews() {
        winner()
    }

    private fun winner() {
        if (viewModel.pokemon1.power > viewModel.pokemon2.power){
            binding.imgPokemonWinner.setImageResource(viewModel.pokemon1.image)
            binding.lblWinnerName.setText(viewModel.pokemon1.name)
        }
        else if (viewModel.pokemon1.power < viewModel.pokemon2.power){
            binding.imgPokemonWinner.setImageResource(viewModel.pokemon2.image)
            binding.lblWinnerName.setText(viewModel.pokemon2.name)
        }
        else{
            binding.imgPokemonWinner.setImageResource(viewModel.pokemon1.image)
            binding.lblWinnerName.setText(viewModel.pokemon1.name)
        }
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(WinnerActivity.EXTRA_POKEMON1) || !intent.hasExtra(WinnerActivity.EXTRA_POKEMON2)) {
            throw RuntimeException()
        }
        viewModel.pokemon1 = intent.getParcelableExtra(WinnerActivity.EXTRA_POKEMON1)!!
        viewModel.pokemon2 = intent.getParcelableExtra(WinnerActivity.EXTRA_POKEMON2)!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_POKEMON_1, viewModel.pokemon1)
        outState.putParcelable(STATE_POKEMON_2, viewModel.pokemon2)
    }

}