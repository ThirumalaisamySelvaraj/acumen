package com.example.acumenapp.room.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.acumenapp.getOrAwaitValue
import com.example.acumenapp.room.dao.UserDao
import com.example.acumenapp.room.entity.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDataBaseTest{

    private lateinit var dataBase: UserDataBase
    private lateinit var dao: UserDao

    @get : Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = dataBase.userDao
    }

    @After
    fun tearDown(){
        dataBase.close()
    }

    @Test
    fun insertUser() = runBlocking{

        dao.insertUser(User(1,"Thiru","Thirumalais.selvaraj@gmail.com"))
        val insertedRec = dao.getAllUser()

        val insertedLiveData = insertedRec.getOrAwaitValue().find {
            it.userId!=null  && it.userId==1
                    && it.userName!=null && it.userName=="Thiru"
                    && it.userEmail != null && it.userEmail == "Thirumalais.selvaraj@gmail.com"
        }
        assertThat(insertedLiveData!=null).isTrue()
    }

    @Test
    fun updateUser() = runBlocking{

        dao.updateUser(User(1,"Thiru","Thirumalais.selvaraj@gmail.com"))
        val updatedRec = dao.getAllUser()

        val updatedLiveData = updatedRec.getOrAwaitValue().find {
            it.userId!=null  && it.userId==1
                    && it.userName!=null && it.userName=="Thiru"
                    && it.userEmail != null && it.userEmail == "Thirumalais.selvaraj@gmail.com"
        }
        assertThat(updatedLiveData!=null).isTrue()
    }

    @Test
    fun deleteUser() = runBlocking{

        dao.deleteUser(User(1,"Thiru","Thirumalais.selvaraj@gmail.com"))
        val deletedRec = dao.getAllUser()

        val deletedLiveData = deletedRec.getOrAwaitValue().find {
            it.userId!=null  && it.userId==1
                    && it.userName!=null && it.userName=="Thiru"
                    && it.userEmail != null && it.userEmail == "Thirumalais.selvaraj@gmail.com"
        }
        assertThat(deletedLiveData==null).isTrue()
    }
}