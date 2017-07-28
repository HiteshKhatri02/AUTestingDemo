package com.example.ranosys.autestingdemo.addedituser

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.SparseIntArray
import android.view.View
import android.widget.EditText
import com.example.ranosys.autestingdemo.R

import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.SchedulerProvider
import kotlinx.android.synthetic.main.activity_add_edit_user.*
import java.util.*


/**
 * @author Hitesh Khatri
 */
class AddEditUserActivity : AppCompatActivity(),AddEditUserContractor.View{


    private var presenter : AddEditUserContractor.Presenter? = null

    var userName:EditText?=null
    var userAdress:EditText?=null
    var fab:FloatingActionButton?=null
    var isAddTask:Boolean=false
    var user:User?=User()
    var viewRoot:View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_user)

        presenter = AddEditPresenter(this, this,
                SchedulerProvider.getInstance() as BaseSchedulerProvider)

        userName = findViewById(R.id.tv_user_car) as EditText
        userAdress = findViewById(R.id.tv_address) as EditText
        fab = findViewById(R.id.fab_add_user) as FloatingActionButton

        presenter?.verifyAddEditUser(intent)

        viewRoot = window.decorView.rootView

    }

    fun  onFABClicked(view: View){

        user?.name = userName?.getText().toString().trim()
        user?.address = userAdress?.getText().toString().trim()

        if (isAddTask){
            user?.id = UUID.randomUUID().toString()
            presenter?.addUserDetails(user!!, viewRoot!!)

        }else{

            presenter?.updateUserDetails(user!!, viewRoot!!)
        }
    }


    override fun setPresenter(presenter: AddEditUserContractor.Presenter) {

    }


    override fun setLoadingIndicator(boolean: Boolean) {

    }



    override fun showAsAddUser() {
        userName?.setText(null)
        userName?.setHint(getString(R.string.text_user_name))
        userAdress?.setText(null)
        userAdress?.setHint(getString(R.string.text_user_address))
        fab?.setImageResource(R.drawable.ic_add)
        isAddTask = true

    }

    override fun showAsEditTask(user: User) {
        this.user = user
        userName?.setText(user.name)
        userAdress?.setText(user.address)
        fab?.setImageResource(R.drawable.ic_update)
        isAddTask = false
    }

    override fun showLoadingTaskError() {

    }

    override fun finishActivity() {
        finish()
    }

}