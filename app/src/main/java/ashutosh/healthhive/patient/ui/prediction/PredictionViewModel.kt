package ashutosh.healthhive.patient.ui.prediction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ashutosh.healthhive.patient.models.PredictRequest
import ashutosh.healthhive.patient.repository.PredictionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredictionViewModel @Inject constructor(private val predictionRepository: PredictionRepository) : ViewModel() {

    var symptomsList = listOf<String>()

    val predictionResponse get() = predictionRepository.predictResponse

    fun predict(){
        viewModelScope.launch {
            predictionRepository.predict(PredictRequest(symptomsList))
        }
    }
}