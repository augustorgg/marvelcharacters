package br.com.mobile.marvelcharacters.presentation.ui

import MarvelCharactersAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.databinding.FragmentFirstBinding
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.presentation.ui.state.MarvelCharactersViewState
import br.com.mobile.marvelcharacters.presentation.viewmodel.MarvelCharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: MarvelCharactersViewModel by viewModel()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        setupListeners()
        viewModel.getMarvelCharactersDetails()
    }

    private fun setupListeners() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MarvelCharactersViewState.Loading -> {
                    Toast.makeText(this.context, "Loading...", Toast.LENGTH_SHORT).show()
                }

                is MarvelCharactersViewState.Success -> {
                    setupRecyclerView(state.marvelCharacters)
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                }

                is MarvelCharactersViewState.Error -> {
                    Toast.makeText(this.context, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView(characters: List<CharacterResult>?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = MarvelCharactersAdapter(characters)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtons(){
        binding.btnWhatsapp.setOnClickListener {
            viewModel.getMarvelCharactersDetails()
        }
    }
}