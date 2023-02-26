package ashutosh.healthhive.patient.ui.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ashutosh.healthhive.patient.repository.RecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(private val recordsRepository: RecordsRepository): ViewModel() {
    val records get() = recordsRepository.records

    fun getRecords(){
        viewModelScope.launch {
            recordsRepository.getRecords()
        }
    }
}