package com.example.findissues.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.databinding.FragmentFollowingBinding
import com.example.findissues.ui.adapters.FollowingAdapter
import com.example.findissues.viewmodels.FollowingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingViewModel: FollowingViewModel

    @Inject
    lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followingAdapter
        }
        followingViewModel = ViewModelProvider(this)[FollowingViewModel::class.java]

        followingViewModel.getFollowing()

        followingViewModel.observeFollowingList().observe(viewLifecycleOwner) {
            followingAdapter.setUpFollowingList(it)
        }

        return binding.root
    }
}