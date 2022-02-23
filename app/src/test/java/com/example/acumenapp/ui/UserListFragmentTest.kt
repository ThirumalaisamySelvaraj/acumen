package com.example.acumenapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acumenapp.Status
import com.example.acumenapp.apputil.GlobalConstants
import com.example.acumenapp.getOrAwaitValueTest
import com.example.acumenapp.repositories.FakeUserRepositoryTest
import com.example.acumenapp.ui.viewmodels.UserViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserListFragmentTest{
    private lateinit var viewmodel : UserViewModel

    @get : Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewmodel = UserViewModel(FakeUserRepositoryTest())
    }

    @Test
    fun `insert user item with empty field returns error`(){
        viewmodel.insertUsersItem(1,"Name","")
        val value = viewmodel.insertuserstableStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert user item with too long name returns error`(){
        var string = buildString {
            for(i in 1..GlobalConstants.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }
        viewmodel.insertUsersItem(1, string,"Name@gmail.com")
        val value = viewmodel.insertuserstableStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert user item with too long email returns error`(){
        var string = buildString {
            for(i in 1..GlobalConstants.MAX_EMAIL_LENGTH + 1){
                append(1)
            }
        }
        viewmodel.insertUsersItem(1, string,"Name@gmail.com")
        val value = viewmodel.insertuserstableStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert user item with valid input returns true`(){
        viewmodel.insertUsersItem(1, "Thir","Thiru@gmail.com")
        val value = viewmodel.insertuserstableStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}