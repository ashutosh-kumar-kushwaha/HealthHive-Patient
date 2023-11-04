package ashutosh.healthhive.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ashutosh.healthhive.patient.databinding.ImageItemBinding
import coil.load

class ImageRecyclerAdapter(private val images: List<String>) : RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String){
            binding.imageView.load(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}