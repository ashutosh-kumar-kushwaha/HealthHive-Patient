package ashutosh.healthhive.patient.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.healthhive.patient.CheckboxClickListener
import ashutosh.healthhive.patient.R
import ashutosh.healthhive.patient.SymptomRecyclerAdapter
import ashutosh.healthhive.patient.databinding.FragmentSelfDiagnosisBinding
import ashutosh.healthhive.patient.models.Symptom
import java.lang.reflect.Field

class SelfDiagnosisFragment : Fragment() , CheckboxClickListener{

    private var _binding: FragmentSelfDiagnosisBinding? = null
    private val binding: FragmentSelfDiagnosisBinding get() = _binding!!

    val symptomList = mutableListOf(

        Symptom(1, "Itching", "itching", false),
        Symptom(2, "Skin Rash", "skin_rash", false),
        Symptom(3, "Nodal Skin Eruptions", "nodal_skin_eruptions", false),
        Symptom(4, "Continuous Sneezing", "continuous_sneezing", false),
        Symptom(5, "Shivering", "shivering", false),
        Symptom(6, "Chills", "chills", false),
        Symptom(7, "Joint Pain", "joint_pain", false),
        Symptom(8, "Stomach Pain", "stomach_pain", false),
        Symptom(9, "Acidity", "acidity", false),
        Symptom(10, "Ulcers on Tongue", "ulcers_on_tongue", false),
        Symptom(11, "Muscle Wasting", "muscle_wasting", false),
        Symptom(12, "Vomiting", "vomiting", false),
        Symptom(13, "Burning Micturition", "burning_micturition", false),
        Symptom(14, "Spotting Urination", "spotting_ urination", false),
        Symptom(15, "Fatigue", "fatigue", false),
        Symptom(16, "Weight Gain", "weight_gain", false),
        Symptom(17, "Anxiety", "anxiety", false),
        Symptom(18, "Cold Hands and Feets", "cold_hands_and_feets", false),
        Symptom(19, "Mood Swings", "mood_swings", false),
        Symptom(20, "Weight Loss", "weight_loss", false),
        Symptom(21, "Restlessness", "restlessness", false),
        Symptom(22, "Lethargy", "lethargy", false),
        Symptom(23, "Patches in Throat", "patches_in_throat", false),
        Symptom(24, "Irregular Sugar Level", "irregular_sugar_level", false),
        Symptom(25, "Cough", "cough", false),
        Symptom(26, "High Fever", "high_fever", false),
        Symptom(27, "Sunken Eyes", "sunken_eyes", false),
        Symptom(28, "Breathlessness", "breathlessness", false),
        Symptom(29, "Sweating", "sweating", false),
        Symptom(30, "Dehydration", "dehydration", false),
        Symptom(31, "Indigestion", "indigestion", false),
        Symptom(32, "Headache", "headache", false),
        Symptom(33, "Yellowish Skin", "yellowish_skin", false),
        Symptom(34, "Dark Urine", "dark_urine", false),
        Symptom(35, "Nausea", "nausea", false),
        Symptom(36, "Loss of Appetite", "loss_of_appetite", false),
        Symptom(37, "Pain behind the eyes", "pain_behind_the_eyes", false),
        Symptom(38, "Back Pain", "back_pain", false),
        Symptom(39, "Constipation", "constipation", false),
        Symptom(40, "Abdominal Pain", "abdominal_pain", false),
        Symptom(41, "Diarrhoea", "diarrhoea", false),
        Symptom(42, "Mild Fever", "mild_fever", false),
        Symptom(43, "Yellow Urine", "yellow_urine", false)

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelfDiagnosisBinding.inflate(inflater, container, false)

//        val symptomList = listOf( Symptom(1, "Itching", "itching", false), Symptom(2, "Skin Rash", "skin_rash", false), Symptom(3, "Nodal Skin Eruptions", "nodal_skin_eruptions", false), Symptom(4, "Continuous Sneezing", "continuous_sneezing"), Symptom(5, "Shivering", "shivering"), Symptom(6, "Chills", "chills"), Symptom(7, "Joint Pain", "joint_pain"), Symptom(8, "Stomach Pain", "stomach_pain"), Symptom(9, "Acidity", "acidity"), Symptom(10, "Ulcers on Tongue", "ulcers_on_tongue"), Symptom(11, "Muscle Wasting", "muscle_wasting"), Symptom(12, "Vomiting", "vomiting"), Symptom(13, "Burning Micturition", "burning_micturition"), Symptom(14, "Spotting Urination", "spotting_ urination"), Symptom(15, "Fatigue", "fatigue"), Symptom(16, "Weight Gain", "weight_gain"), Symptom(17, "Anxiety", "anxiety"), Symptom(18, "Cold Hands and Feets", "cold_hands_and_feets"), Symptom(19, "Mood Swings", "mood_swings"), Symptom(20, "Weight Loss", "weight_loss"), Symptom(21, "Restlessness", "restlessness"), Symptom(22, "Lethargy", "lethargy"), Symptom(23, "Patches in Throat", "patches_in_throat"), Symptom(24, "Irregular Sugar Level", "irregular_sugar_level"), Symptom(25, "Cough", "cough"), Symptom(26, "High Fever", "high_fever"), Symptom(27, "Sunken Eyes", "sunken_eyes"), Symptom(28, "Breathlessness", "breathlessness"), Symptom(29, "Sweating", "sweating"), Symptom(30, "Dehydration", "dehydration"), Symptom(31, "Indigestion", "indigestion"), Symptom(32, "Headache", "headache"), Symptom(33, "Yellowish Skin", "yellowish_skin"), Symptom(34, "Dark Urine", "dark_urine"), Symptom(35, "Nausea", "nausea"), Symptom(36, "Loss of Appetite", "loss_of_appetite"), Symptom(37, "Pain behind the eyes", "pain_behind_the_eyes"), Symptom(38, "Back Pain", "back_pain"), Symptom(39, "Constipation", "constipation"), Symptom(40, "Abdominal Pain", "abdominal_pain"), Symptom(41, "Diarrhoea", "diarrhoea"), Symptom(42, "Mild Fever", "mild_fever"), Symptom(43, "Yellow Urine", "yellow_urine"), Symptom(44, "Yellowing of Eyes", "yellowing_of_eyes"), Symptom(45, "Acute Liver Failure", "acute_liver_failure"), Symptom(46, "Fluid Overload", "fluid_overload"), Symptom(47, "Swelling of Stomach", "swelling_of_stomach"), Symptom(48, "Swelled Lymph Nodes", "swelled_lymph_nodes"))



//        val checkboxClickListener = object : CheckboxClickListener{
//            override fun onClick(isPicked: Boolean, index: Int) {
//                symptomList[index].isPicked = isPicked
//            }
//        }

        val symptomRecyclerAdapter = SymptomRecyclerAdapter(symptomList, this)
        binding.symptomsRecyclerView.adapter = symptomRecyclerAdapter
        binding.symptomsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val searchText = binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        val font = ResourcesCompat.getFont(requireContext(), R.font.montserrat)
        searchText.typeface = font
        searchText.setTextColor(ContextCompat.getColor(requireContext(), R.color.color2))
        searchText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.color2))

        searchText.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        if(Build.VERSION.SDK_INT >= 29){
            searchText.textCursorDrawable = AppCompatResources.getDrawable(requireContext(), R.drawable.cursor_2)
        }
        else{
            try{
                val f: Field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                f.isAccessible = true
                f.set(searchText, R.drawable.cursor_2)
            }
            catch (_: Exception){}
        }

        val icon = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        icon.setImageResource(R.drawable.ic_search_icon)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                symptomRecyclerAdapter.filter.filter(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                symptomRecyclerAdapter.filter.filter(newText.toString())
                return false
            }

        })

        binding.nextBtn.setOnClickListener {
//            Log.d("Ashu", symptomRecyclerAdapter.selectedItems.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(isPicked: Boolean, index: Int) {
        symptomList[index].isPicked = isPicked
    }
}