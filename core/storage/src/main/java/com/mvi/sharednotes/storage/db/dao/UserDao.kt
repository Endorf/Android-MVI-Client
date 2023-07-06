package com.mvi.sharednotes.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvi.sharednotes.storage.db.entity.DbUserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT :limit")
    fun read(email: String, limit: Int = 1): DbUserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: DbUserEntity)

    @Query("UPDATE user SET name = :name, username = :username WHERE email = :email")
    fun update(email: String, name: String?, username: String?)
}