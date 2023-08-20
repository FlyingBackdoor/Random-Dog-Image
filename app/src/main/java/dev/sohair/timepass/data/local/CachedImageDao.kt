package dev.sohair.timepass.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CachedImageDao {
    @Query("SELECT * from CachedImage")
    suspend fun getAll(): List<CachedImage>?

    @Insert
    suspend fun insertImage(image: CachedImage)

    @Query("DELETE FROM CachedImage")
    suspend fun deleteAll()
}