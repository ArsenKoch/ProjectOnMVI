package com.example.projectonmvi.data

interface UserStorage {
    fun save(user: User): Boolean

    fun get(): User
}