package com.app.database

import com.app.database.repository.UserRepository


class DatabaseHelper private constructor() {

    companion object {
        val instance: DatabaseHelper by lazy { DatabaseHelper() }
    }

    val userRepository by lazy { UserRepository() }
}