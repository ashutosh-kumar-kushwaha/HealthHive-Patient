package ashutosh.healthhive.patient.ui.consultation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import ashutosh.healthhive.patient.R
import ashutosh.healthhive.patient.databinding.FragmentConsulationBinding


class ConsultationFragment : Fragment() {

    private var _binding : FragmentConsulationBinding? = null
    private val binding: FragmentConsulationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConsulationBinding.inflate(inflater, container, false)

        binding.selfDiagnosisBtn.setOnClickListener {
            findNavController().navigate(R.id.action_consultationFragment_to_selfDiagnosisFragment)
        }

        val backBtn = binding.toolbar.findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}