package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.Issues
import com.example.findissues.models.IssuesList
import com.example.findissues.repository.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssuesViewModel constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var issueLiveData = MutableLiveData<List<IssuesList>>()

    suspend fun getIssueLink() {
       repository.getAllIssues().enqueue(object : Callback<Issues> {
            override fun onResponse(call: Call<Issues>, response: Response<Issues>) {
                if (response.body() != null) {
                     issueLiveData.value = response.body()?.items
                    Log.d("response",response.body().toString())
                } else {
                    Log.d("error" ,"in here")
                }
            }

            override fun onFailure(call: Call<Issues>, t: Throwable) {
                Log.d("failure", "call has failed")
                return
            }

        })
    }

    fun observeIssueLiveData(): LiveData<List<IssuesList>> {
        return issueLiveData
    }
}