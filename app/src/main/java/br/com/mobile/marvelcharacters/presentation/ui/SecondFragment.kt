package br.com.mobile.marvelcharacters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.databinding.FragmentSecondBinding
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.ComicItem
import br.com.mobile.marvelcharacters.presentation.adapter.MarvelCharacterDetailAdapter
import com.squareup.picasso.Picasso

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var characterResult: CharacterResult? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            characterResult = bundle.getParcelable(CHARACTER_RESULT)
        }
        setupDetails()
    }

    private fun setupDetails() {
        binding.apply {
            textViewTitle.text = characterResult?.name
            textViewDescription.text = characterResult?.description
            setupRecyclerView(characterResult?.comics?.items)
        }
        setupImage()
    }

    private fun setupImage(){
        val imageUrl =
            "${characterResult?.thumbnail?.path}.${characterResult?.thumbnail?.extension}"
        val secureImageUrl = imageUrl.replace("http://", "https://")
        Picasso.get()
            .load(secureImageUrl)
            .placeholder(R.drawable.placeholder_marvel_character)
            .error(R.drawable.whatsapp)
            .into(binding.ivHero)
    }

    private fun setupRecyclerView(comicItems: List<ComicItem>?) {
        binding.recyclerViewComics.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = MarvelCharacterDetailAdapter(comicItems)
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