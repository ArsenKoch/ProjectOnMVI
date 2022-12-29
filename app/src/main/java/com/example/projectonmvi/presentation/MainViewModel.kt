package com.example.projectonmvi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectonmvi.domain.GetUserNameUseCase
import com.example.projectonmvi.domain.SaveUserNameParam
import com.example.projectonmvi.domain.SaveUserNameUseCase
import com.example.projectonmvi.domain.UserName

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private val stateLiveMutable = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> = stateLiveMutable

    init {
        Log.e("AAA", "VM created")
        stateLiveMutable.value = MainState(false,"","")
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }

    fun send(event: MainEvent) {
        when (event) {
            is LoadEvent -> {
                load()
            }

            is SaveEvent -> {
                save(text = event.text)
            }
        }
    }

    private fun save(text: String) {
        val param = SaveUserNameParam(text)
        val resultData: Boolean = saveUserNameUseCase.execute(param)
        stateLiveMutable.value = MainState(
            result = resultData,
            lastName = stateLiveMutable.value!!.lastName,
            firstName = stateLiveMutable.value!!.firstName
        )
    }

    private fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        stateLiveMutable.value = MainState(
            result = stateLiveMutable.value!!.result,
            lastName = userName.lastName,
            firstName = userName.firstName,
        )
    }
}