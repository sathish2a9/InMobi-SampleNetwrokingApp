package com.sathishkumar.InMobiDemoProject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingassignretrofit.Users
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var initialTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            initialTime = System.currentTimeMillis()
            loader.visibility = View.VISIBLE
            btn_start.visibility = View.GONE

            launch {
                val listOfUsers = async { getUsersData() }
                val listOfPosts = async { getPostsData() }

                val finalListOfPosts = listOfPosts.await()
                val finalListOfUsers = listOfUsers.await()

                if (finalListOfPosts.isNotEmpty() && finalListOfUsers.isNotEmpty()) {
                    showData(finalListOfPosts, finalListOfUsers)
                }
            }
        }

    }

    private fun showData(postList: List<Posts>, usersList: List<Users>) {
        loader.visibility = View.GONE
        val count = usersList.toString().length
        val time = (System.currentTimeMillis() - initialTime)
        tv_time.text = getString(R.string.time_taken, time.toString())

        Toast.makeText(
            this@MainActivity,
            "Total Char Count in Users List: $count",
            Toast.LENGTH_LONG
        ).show()
        rv_list.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_list.adapter = PostAdapter(postList)
    }

    suspend fun getUsersData(): List<Users> {
        val userApi = RetrofitClient.userApi
        val response = userApi.getUsers()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            emptyList()
        }
    }

    suspend fun getPostsData(): List<Posts> {
        val postApi = RetrofitClient.postApi

        val response = postApi.getPosts()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            emptyList()
        }
    }
}