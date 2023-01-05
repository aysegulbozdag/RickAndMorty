package com.example.rickandmorty.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.db.dao.FavCharacterDAO
import com.example.rickandmorty.data.db.entity.FavCharacter

@Database(entities = [FavCharacter::class], version = 1)
abstract class FavCharacterInstance : RoomDatabase() {
    abstract fun favCharacterDao(): FavCharacterDAO

    companion object {
        private var favCharacterInstance: FavCharacterInstance? = null

        fun getDatabase(context: Context): FavCharacterInstance? {
            if (favCharacterInstance == null) {
                synchronized(FavCharacterInstance::class.java) {
                    if (favCharacterInstance == null) {
                        favCharacterInstance = Room.databaseBuilder(
                            context,
                            FavCharacterInstance::class.java,
                            "favCharacterDB"
                        ).build()
                    }
                }
            }
            return favCharacterInstance
        }
    }
}