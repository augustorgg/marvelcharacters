package br.com.mobile.marvelcharacters.presentation.ui

import MarvelCharactersAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.marvelcharacters.databinding.ActivityMainBinding
import br.com.mobile.marvelcharacters.presentation.ui.state.MarvelCharactersViewState
import br.com.mobile.marvelcharacters.presentation.viewmodel.MarvelCharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MarvelCharactersAdapter
    private val viewModel: MarvelCharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupWhatsappButton()
        viewModel.getMarvelCharactersDetails()
    }

    private fun setupListeners() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is MarvelCharactersViewState.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }

                is MarvelCharactersViewState.Success -> {
                    adapter = MarvelCharactersAdapter(state.marvelCharacters)
                    setupRecyclerView()
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }

                is MarvelCharactersViewState.Error -> {
                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupWhatsappButton() {
        binding.btnWhatsapp.setOnClickListener {
//            val intent = WhatsappIntentBuilder().build()
//            startActivity(intent)
            viewModel.getMarvelCharactersDetails()
        }
    }
}
