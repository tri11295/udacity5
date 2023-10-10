package com.example.android.moviedb.ui.actor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.moviedb.data.model.ActorDetail
import com.example.android.moviedb.data.source.remote.ApiRepository
import kotlinx.coroutines.launch

class ActorDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun saveProgress(progress: Float) {
        savedStateHandle["key_progress"] = progress
    }

    fun getProgress(): Float? {
        return savedStateHandle.get<Float>("key_progress")
    }

    private var apiRepository = ApiRepository

    private val _isHideNameActor = MutableLiveData(false)
    val isHideNameActor: MutableLiveData<Boolean>
        get() = _isHideNameActor

    private val _isHideBirthday = MutableLiveData(false)
    val isHideBirthday: MutableLiveData<Boolean>
        get() = _isHideBirthday

    private val _isHideAddress = MutableLiveData(false)
    val isHideAddress: MutableLiveData<Boolean>
        get() = _isHideAddress

    private val _isHideBiography = MutableLiveData(false)
    val isHideBiography: MutableLiveData<Boolean>
        get() = _isHideBiography

    private val _detailActor = MutableLiveData<ActorDetail?>()
    val detailActor: MutableLiveData<ActorDetail?>
        get() = _detailActor

    fun getActorDetail(idActor: Int) {
        try {
            viewModelScope.launch {
                try {
                    launch {
                        apiRepository.getActorDetail(idActor).apply {
                            body()?.let {
                                _detailActor.value = it
                                if (it.name.isNullOrEmpty()) {
                                    _isHideNameActor.value = true
                                }
                                if (it.birthday.isNullOrEmpty()) {
                                    _isHideBirthday.value = true
                                }
                                if (it.address.isNullOrEmpty()) {
                                    _isHideAddress.value = true
                                }
                                if (it.biography.isNullOrEmpty()) {
                                    _isHideBiography.value = true
                                }
                            }
                        }
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }
}