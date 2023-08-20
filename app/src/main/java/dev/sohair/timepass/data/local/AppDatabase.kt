package dev.sohair.timepass.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CachedImageDao
}
