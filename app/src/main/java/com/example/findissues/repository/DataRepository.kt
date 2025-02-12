package com.example.findissues.repository

import com.example.findissues.api.github.GithubApiService
import com.example.findissues.utils.Constants
import javax.inject.Inject

class DataRepository @Inject constructor(private val service: GithubApiService) {
    suspend fun getAllIssues() = service.getIssue("kotlin", Constants.CREATED)
    suspend fun getUser() = service.getUser("pratyushsingh07")
    suspend fun getFollowers() = service.getFollowers("pratyushsingh07")
    suspend fun getFollowing() = service.getFollowings("pratyushsingh07")
    suspend fun getRepos() = service.getRepos("pratyushsingh07")
}