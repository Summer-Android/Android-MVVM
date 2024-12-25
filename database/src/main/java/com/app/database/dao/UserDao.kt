package com.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityList: List<UserEntity>): List<Long>

    @Query("SELECT * FROM t_user")
    suspend fun queryAll(): List<UserEntity>?

    @Query("SELECT * FROM t_user where userId=:userId")
    suspend fun queryByUserId(userId: String): UserEntity?

    @Query("update t_user set name=:name where userId=:userId")
    suspend fun updateByUserId(userId: String, name: String): Int

    @Query("delete from t_user where userId=:userId")
    suspend fun deleteByUserId(userId: String): Int
}