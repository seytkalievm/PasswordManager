package com.seytkalievm.passwordmanager.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seytkalievm.passwordmanager.data.credit_card.CreditCardDao
import com.seytkalievm.passwordmanager.data.credit_card.CreditCardEntity
import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoDao
import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoEntity
import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsDao
import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsEntity

@Database(
    entities = [WebsiteCredentialsEntity::class, CreditCardEntity::class, PersonalInfoEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase(){

    abstract fun websiteCredentialsDao(): WebsiteCredentialsDao
    abstract fun creditCardDao(): CreditCardDao
    abstract fun personalInfoDao(): PersonalInfoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context, AppDatabase::class.java,"app_database")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}