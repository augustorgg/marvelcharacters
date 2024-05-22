package br.com.mobile.marvelcharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.databinding.ItemMarvelCharacterDetailBinding
import br.com.mobile.marvelcharacters.domain.model.ComicItem

class MarvelCharacterDetailAdapter(private val comics: List<ComicItem>?) :
    RecyclerView.Adapter<MarvelCharacterDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMarvelCharacterDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comics?.get(position))
    }

    override fun getItemCount(): Int {
        return comics?.size ?: 0
    }

    class ViewHolder(binding: ItemMarvelCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameTextView = binding.textViewComicName

        fun bind(comic: ComicItem?) {
            nameTextView.text = comic?.name
        }
    }
}
