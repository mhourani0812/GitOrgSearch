package com.example.nytimes.model.interfaces

import com.example.nytimes.model.data.models.Organizations
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface OrganizationServices
{
    @GET("organizations")
    suspend fun getOrganizations() : Response<Organizations>
}