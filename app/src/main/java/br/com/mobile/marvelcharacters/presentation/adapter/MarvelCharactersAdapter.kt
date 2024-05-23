package br.com.mobile.marvelcharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.databinding.ItemMarvelCharacterBinding
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.presentation.utils.ZERO
import br.com.mobile.marvelcharacters.presentation.utils.getSecureImageUrl
import com.squareup.picasso.Picasso

class MarvelCharactersAdapter(
    private var characters: List<CharacterResult>?,
    private val onItemClick: (CharacterResult?) -> Unit,
) :
    RecyclerView.Adapter<MarvelCharactersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            ItemMarvelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val character = characters?.get(position)
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characters?.size ?: ZERO
    }

    inner class ViewHolder(private val binding: ItemMarvelCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDetail: CharacterResult?) {
            with(binding) {
                Picasso.get()
                    .load(characterDetail.getSecureImageUrl())
                    .placeholder(R.drawable.placeholder_marvel_character)
                    .error(R.drawable.alert_error)
                    .into(ivItem)
                tvItem.text = characterDetail?.name
                root.setOnClickListener {
                    onItemClick.invoke(characterDetail)
                }
            }
        }
    }
}
