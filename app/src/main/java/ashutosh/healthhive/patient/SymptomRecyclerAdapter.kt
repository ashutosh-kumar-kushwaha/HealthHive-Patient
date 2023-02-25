package ashutosh.healthhive.patient

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import ashutosh.healthhive.patient.databinding.SymptomItemBinding
import ashutosh.healthhive.patient.models.Symptom

class SymptomRecyclerAdapter(val symptomList: List<Symptom>) : RecyclerView.Adapter<SymptomRecyclerAdapter.ViewHolder>(),
    Filterable {

    private var filteredList = symptomList
    private val selectedItems = ArrayList<Symptom>()

    inner class ViewHolder(private val binding: SymptomItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(symptom: Symptom){
            binding.checkbox.text = symptom.name
            binding.checkbox.isChecked = selectedItems.contains(symptom)
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(symptom)
                } else {
                    selectedItems.remove(symptom)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SymptomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase()
                val results = FilterResults()
                val filtered = ArrayList<Symptom>()

                for (item in symptomList) {
                    if (item.name.lowercase().contains(query)) {
                        filtered.add(item)
                    }
                }

                results.count = filtered.size
                results.values = filtered

                Log.d("Ashu" , results.toString())

//                publishResults(constraint, results)

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Symptom>
                notifyDataSetChanged()
            }
        }
    }
}