package ashutosh.healthhive.patient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.healthhive.patient.R
import ashutosh.healthhive.patient.SymptomRecyclerAdapter
import ashutosh.healthhive.patient.databinding.FragmentSelfDiagnosisBinding
import ashutosh.healthhive.patient.models.Symptom

class SelfDiagnosisFragment : Fragment() {

    private var _binding: FragmentSelfDiagnosisBinding? = null
    private val binding: FragmentSelfDiagnosisBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelfDiagnosisBinding.inflate(inflater, container, false)

        val symptomList = listOf( Symptom(1, "Itching", "itching"), Symptom(2, "Skin Rash", "skin_rash"), Symptom(3, "Nodal Skin Eruptions", "nodal_skin_eruptions"), Symptom(4, "Continuous Sneezing", "continuous_sneezing"), Symptom(5, "Shivering", "shivering"), Symptom(6, "Chills", "chills"), Symptom(7, "Joint Pain", "joint_pain"), Symptom(8, "Stomach Pain", "stomach_pain"), Symptom(9, "Acidity", "acidity"), Symptom(10, "Ulcers on Tongue", "ulcers_on_tongue"), Symptom(11, "Muscle Wasting", "muscle_wasting"), Symptom(12, "Vomiting", "vomiting"), Symptom(13, "Burning Micturition", "burning_micturition"), Symptom(14, "Spotting Urination", "spotting_ urination"), Symptom(15, "Fatigue", "fatigue"), Symptom(16, "Weight Gain", "weight_gain"), Symptom(17, "Anxiety", "anxiety"), Symptom(18, "Cold Hands and Feets", "cold_hands_and_feets"), Symptom(19, "Mood Swings", "mood_swings"), Symptom(20, "Weight Loss", "weight_loss"), Symptom(21, "Restlessness", "restlessness"), Symptom(22, "Lethargy", "lethargy"), Symptom(23, "Patches in Throat", "patches_in_throat"), Symptom(24, "Irregular Sugar Level", "irregular_sugar_level"), Symptom(25, "Cough", "cough"), Symptom(26, "High Fever", "high_fever"), Symptom(27, "Sunken Eyes", "sunken_eyes"), Symptom(28, "Breathlessness", "breathlessness"), Symptom(29, "Sweating", "sweating"), Symptom(30, "Dehydration", "dehydration"), Symptom(31, "Indigestion", "indigestion"), Symptom(32, "Headache", "headache"), Symptom(33, "Yellowish Skin", "yellowish_skin"), Symptom(34, "Dark Urine", "dark_urine"), Symptom(35, "Nausea", "nausea"), Symptom(36, "Loss of Appetite", "loss_of_appetite"), Symptom(37, "Pain behind the eyes", "pain_behind_the_eyes"), Symptom(38, "Back Pain", "back_pain"), Symptom(39, "Constipation", "constipation"), Symptom(40, "Abdominal Pain", "abdominal_pain"), Symptom(41, "Diarrhoea", "diarrhoea"), Symptom(42, "Mild Fever", "mild_fever"), Symptom(43, "Yellow Urine", "yellow_urine"), Symptom(44, "Yellowing of Eyes", "yellowing_of_eyes"), Symptom(45, "Acute Liver Failure", "acute_liver_failure"), Symptom(46, "Fluid Overload", "fluid_overload"), Symptom(47, "Swelling of Stomach", "swelling_of_stomach"), Symptom(48, "Swelled Lymph Nodes", "swelled_lymph_nodes"))

        val symptomRecyclerAdapter = SymptomRecyclerAdapter(symptomList)
        binding.symptomsRecyclerView.adapter = symptomRecyclerAdapter
        binding.symptomsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                symptomRecyclerAdapter.filter.filter(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                symptomRecyclerAdapter.filter.filter(newText.toString())
                return true
            }

        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}