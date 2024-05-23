package br.com.mobile.marvelcharacters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.ComicItem
import br.com.mobile.marvelcharacters.presentation.adapter.MarvelCharacterDetailAdapter
import br.com.mobile.marvelcharacters.presentation.utils.WhatsappIntentBuilder
import br.com.mobile.marvelcharacters.presentation.utils.getSecureImageUrl
import br.com.mobile.marvelcharacters.presentation.utils.orIfNullOrEmpty
import com.squareup.picasso.Picasso

class MarvelCharacterDetailFragment : Fragment() {
    private var _binding: FragmentMarvelCharacterDetailBinding? = null
    private var characterResult: CharacterResult? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMarvelCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            characterResult = bundle.getParcelable(CHARACTER_RESULT)
        }
        setupDetails()
        setupButtons()
    }

    private fun setupDetails() {
        binding.apply {
            textViewTitle.text = characterResult?.name
            textViewDescription.text =
                characterResult?.description.orIfNullOrEmpty(getString(R.string.hero_without_description))
            setupRecyclerView(characterResult?.comics?.items)
        }
        setupImage()
    }

    private fun setupImage() {
        Picasso.get()
            .load(characterResult.getSecureImageUrl())
            .placeholder(R.drawable.placeholder_marvel_character)
            .error(R.drawable.alert_error)
            .into(binding.ivHero)
    }

    private fun setupRecyclerView(comicItems: List<ComicItem>?) {
        binding.recyclerViewComics.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = MarvelCharacterDetailAdapter(comicItems)
        }
    }

    private fun setupButtons() {
        binding.apply {
            btnWhatsapp.setOnClickListener {
                val intent = WhatsappIntentBuilder().build()
                startActivity(intent)
            }
            btnBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CHARACTER_RESULT = "characterResult"
    }
}
