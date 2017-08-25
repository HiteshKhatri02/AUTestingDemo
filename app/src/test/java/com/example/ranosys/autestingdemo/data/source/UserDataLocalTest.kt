package com.example.ranosys.autestingdemo.data.source

import com.example.ranosys.autestingdemo.data.user.UserDataSource
import com.example.ranosys.autestingdemo.data.user.UserLocalDataSource
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author Hitesh Khatri
 */
class UserDataLocalTest {

    /*  private val USER_NAME = "Hitesh"

      private val USER_NAME2 = "Vikas"

      private val USER_NAME3 = "Vineet"

      private val TASKS = mutableListOf<User>(User("Hitesh", "362/b street no5, Bikaner"),
              User("Vikas", "MP colony Bikaner"))
*/
      private var userLocalDataStorage:UserLocalDataSource?=null

      @Mock
      private var dataRespository:UserDataSource?=null

    @Before
    fun setUp(){

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)
    }

    @Throws(Exception::class)
    fun testGetUsers() {

    }

    @Throws(Exception::class)
    fun testGetUser(){

    }

    @Throws(Exception::class)
    fun testAddUser(){

    }

    @Throws(Exception::class)
    fun testEditUser(){

    }

    @Throws(Exception::class)
    fun testDeleteUser(){

    }

    @Throws(Exception::class)
    fun testDeleteUser(userId: String){

    }

    @Throws(Exception::class)
    fun testDeleteAllUsers(){

    }

    @Throws(Exception::class)
    fun testRefreshUser(){

    }
}