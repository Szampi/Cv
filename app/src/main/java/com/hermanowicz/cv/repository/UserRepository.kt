package com.hermanowicz.cv.repository


interface UserRepository {
    fun saveUserId(userId: String)
    fun userId(): String
}
