package com.example.ranosys.autestingdemo.users


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.ranosys.autestingdemo.R
import com.example.ranosys.autestingdemo.addedituser.AddEditUserActivity
import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.SchedulerProvider

/**
 *@author Hitesh Khatri
 */
class UserFragment : Fragment(),UserContract.View {


    private var presenter: UserContract.Presenter? = null

    private var listAdapter: UserAdapter? = null

    private var listView:ListView?=null

    private var noItemAvailable:TextView?=null

    private var TAG="UserFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {


        val root = inflater!!.inflate(R.layout.fragment_user, container, false)

        presenter= UserPresenter(activity,this,
                SchedulerProvider.getInstance() as BaseSchedulerProvider)


        listAdapter = UserAdapter(mutableListOf(), presenter as UserPresenter)


        listView=root.findViewById(R.id.lv_user) as ListView

        listView!!.adapter=listAdapter

        noItemAvailable = root.findViewById(R.id.tv_no_item_available) as TextView
        presenter?.loadUser(true)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter?.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter?.subscribe()
    }

    override fun setLoadingIndicator(active: Boolean) {
        if (view==null){
            return
        }

        val refreshLayout = view!!.findViewById(R.id.refresh_layout) as SwipeRefreshLayout
        refreshLayout.post { refreshLayout.setRefreshing(active) }

    }

    override fun showUsers(users: MutableList<User>) {
        listAdapter?.swapData(users)

        listView?.visibility = View.VISIBLE
        noItemAvailable?.visibility =View.GONE

    }

    override fun showAddUser() {
        val intent = Intent(activity,AddEditUserActivity::class.java)
        intent.putExtra("action","ADD_USER")
        activity.startActivityFromFragment(this,intent, 101)
    }


    override fun showAddCar() {
        val intent = Intent(activity,AddEditUserActivity::class.java)
        intent.putExtra("action","ADD_USER")
        activity.startActivityFromFragment(this,intent, 101)
    }

    override fun showUserDetail(user: User) {
        val intent = Intent(activity,AddEditUserActivity::class.java)
        intent.putExtra("action","UPDATE_USER")
        intent.putExtra("useData",user )
        activity.startActivity(intent)
    }

    override fun showNoTasks() {
        listView?.visibility=View.GONE
        noItemAvailable?.visibility = View.VISIBLE
    }

    override fun showLoadingTaskError() {
        Log.d(TAG,"some error occurred while loading tasks.")
    }

    override fun showAllUserDeleted() {
        Log.d(TAG,"Some error occurred while loading tasks.")
    }

    override fun setPresenter(presenter: UserContract.Presenter) {
        this.presenter=presenter
    }

    fun onAddUserClicked(view: View){
        presenter?.addNewUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.result(requestCode,resultCode)
    }
}

