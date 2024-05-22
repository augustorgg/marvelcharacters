import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile.marvelcharacters.R
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.databinding.ItemMarvelCharacterBinding
import com.squareup.picasso.Picasso

class MarvelCharactersAdapter(private var characters: List<CharacterResult>?) :
    RecyclerView.Adapter<MarvelCharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMarvelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters?.get(position)
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characters?.size ?: 0
    }

    inner class ViewHolder(private val binding: ItemMarvelCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterDetail: CharacterResult?) {
            val imageUrl =
                "${characterDetail?.thumbnail?.path}.${characterDetail?.thumbnail?.extension}"
            val secureImageUrl = imageUrl.replace("http://", "https://")
            Picasso.get()
                .load(secureImageUrl)
                .placeholder(R.drawable.placeholder_marvel_character)
                .error(R.drawable.whatsapp)
                .into(binding.ivItem)
            binding.tvItem.text = characterDetail?.name
        }
    }
}
