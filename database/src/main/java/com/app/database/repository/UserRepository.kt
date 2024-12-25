package com.app.database.repository

import com.app.database.AppDatabase
import com.app.database.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    private val dao = AppDatabase.instance.userDao

    suspend fun insert(entity: UserEntity) = withContext(Dispatchers.IO) {
        dao.insert(entity)
    }

    suspend fun insert(entityList: List<UserEntity>) = withContext(Dispatchers.IO) {
        dao.insert(entityList)
    }

    suspend fun queryAll() = withContext(Dispatchers.IO) {
        dao.queryAll()
    }

    suspend fun queryByUserId(userId: String) = withContext(Dispatchers.IO) {
        dao.queryByUserId(userId)
    }

    suspend fun updateByUserId(userId: String, name: String) = withContext(Dispatchers.IO) {
        dao.updateByUserId(userId, name)
    }

    suspend fun deleteByUserId(userId: String) = withContext(Dispatchers.IO) {
        dao.deleteByUserId(userId)
    }
}