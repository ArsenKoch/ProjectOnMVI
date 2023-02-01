package com.example.projectonmvi.domain

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {
        val oldUserName = userRepository.getName()
        if (oldUserName.firstName == param.name) {
            return true
        }

        return userRepository.saveName(saveParam = param)
    }
}