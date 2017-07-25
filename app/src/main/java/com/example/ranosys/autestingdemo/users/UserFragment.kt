package com.example.ranosys.autestingdemo.users


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
import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.SchedulerProvider

/**
 *@author Hitesh Khatri
 */
class UserFragment : Fragment(),UserContract.View,UserItemClickListener {


    private var presenter: UserContract.Presenter? = null

    private var listAdapter: UserAdapter? = null

    private var listView:ListView?=null

    private var noItemAvailable:TextView?=null

    private var TAG="UserFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = UserAdapter(mutableListOf(),this)
        presenter?.start()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        presenter= UserPresenter(activity,this,
                SchedulerProvider.getInstance() as BaseSchedulerProvider)

        val root = inflater!!.inflate(R.layout.fragment_user, container, false)

        listView=root.findViewById(R.id.lv_user) as ListView

        listView!!.adapter=listAdapter

        noItemAvailable = root.findViewById(R.id.tv_no_item_available) as TextView

        return root
    }

    override fun onItemClick(user: User) {
        presenter!!.openUserDetail(user)
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
        Log.d(TAG,"Show add user screen.")
    }


    override fun showAddCar() {
        Log.d(TAG,"Show add car screen.")
    }

    override fun showUserDetail(userId: String) {
        Log.d(TAG,"Show user detail screen.")
    }

    override fun showNoTasks() {
       listView?.visibility=View.GONE
        noItemAvailable?.visibility = View.VISIBLE
    }

    override fun showLoadingTaskError() {
        Log.d(TAG,"some error occured while loading tasks.")
    }

    override fun showAllUserDeleted() {
        Log.d(TAG,"some error occured while loading tasks.")
    }

    override fun setPresenter(presenter: UserContract.Presenter) {
        this.presenter=presenter
    }

}

