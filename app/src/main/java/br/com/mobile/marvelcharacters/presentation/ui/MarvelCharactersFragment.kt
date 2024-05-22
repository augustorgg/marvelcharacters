package br.com.mobile.marvelcharacters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.data.utils.Logger
import br.com.mobile.marvelcharacters.databinding.FragmentMarvelCharactersBinding
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.presentation.adapter.MarvelCharactersAdapter
import br.com.mobile.marvelcharacters.presentation.ui.state.MarvelCharactersViewState
import br.com.mobile.marvelcharacters.presentation.utils.hide
import br.com.mobile.marvelcharacters.presentation.utils.show
import br.com.mobile.marvelcharacters.presentation.utils.startShimmering
import br.com.mobile.marvelcharacters.presentation.utils.stopShimmering
import br.com.mobile.marvelcharacters.presentation.viewmodel.MarvelCharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelCharactersFragment : Fragment() {

    private var _binding: FragmentMarvelCharactersBinding? = null
    private val viewModel: MarvelCharactersViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarvelCharactersBinding.inflate(inflater, container, false)
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
                    binding.apply {
                        recyclerView.hide()
                        shimmerLayout.startShimmering()
                    }
                }

                is MarvelCharactersViewState.Success -> {
                    setupRecyclerView(state.marvelCharacters)
                    binding.apply {
                        recyclerView.show()
                        shimmerLayout.stopShimmering()
                    }
                }

                is MarvelCharactersViewState.Error -> {
                    binding.apply {
                        errorLayout.root.show()
                        shimmerLayout.stopShimmering()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(characters: List<CharacterResult>?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = MarvelCharactersAdapter(characters) {
                val action =
                    MarvelCharactersFragmentDirections.actionMarvelCharactersFragmentToMarvelCharacterDetailFragment(
                        it
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtons() {
        binding.apply {
            btnWhatsapp.setOnClickListener {
                viewModel.getMarvelCharactersDetails()
            }
            errorLayout.retryButton.setOnClickListener {
                binding.errorLayout.root.hide()
                viewModel.getMarvelCharactersDetails()
            }
        }
    }
}