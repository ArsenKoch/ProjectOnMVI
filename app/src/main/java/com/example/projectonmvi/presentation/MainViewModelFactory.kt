package com.example.projectonmvi.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectonmvi.data.SharedPrefUserStorage
import com.example.projectonmvi.data.UserRepositoryImpl
import com.example.projectonmvi.domain.GetUserNameUseCase
import com.example.projectonmvi.domain.SaveUserNameUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(
            SharedPrefUserStorage(context = context)
        )
    }

    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(
            userRepository
        )
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(
            userRepository
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getUserNameUseCase, saveUserNameUseCase) as T
    }
}