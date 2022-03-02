package com.example.nytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.nytimes.model.data.models.Organizations
import com.example.nytimes.model.interfaces.OrganizationServices
import com.example.nytimes.model.retrofit.RetrofitInstance
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG: String = "APPDEBUG"

    @Inject
    lateinit var randomString : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ${randomString}")

        val retService: OrganizationServices = RetrofitInstance
            .getRetrofitInstance()
            .create(OrganizationServices::class.java)

        val responseLiveData : LiveData<Response<Organizations>> = liveData {
            val response = retService.getOrganizations()
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val organizationsList = it.body()?.listIterator()
            if(organizationsList!=null){
                while(organizationsList.hasNext()) {
                    val orgItem = organizationsList.next()
                    Log.i("Hourani", orgItem.login)
                }
            }
        })
    }

}