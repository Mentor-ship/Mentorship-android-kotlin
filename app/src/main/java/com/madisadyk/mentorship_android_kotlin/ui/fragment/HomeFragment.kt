package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.adapter.NearbyVacancyAdapter
import com.madisadyk.mentorship_android_kotlin.adapter.PopularVacancyAdapter
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import com.madisadyk.mentorship_android_kotlin.utils.SessionManager
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: MentorshipViewModel
    lateinit var popularVacancyAdapter: PopularVacancyAdapter
    lateinit var nearbyVacancyAdapter: NearbyVacancyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        if (SessionManager.fetchToken(activity as MainActivity) == null) {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            (activity as MainActivity).nav_bar.visibility = View.GONE
        } else {
            (activity as MainActivity).nav_bar.visibility = View.VISIBLE
            setupRecyclerView()
        }

        popularVacancyAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vacancyDetailsFragment)
        }

        nearbyVacancyAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vacancyDetailsFragment)
        }

        viewModel.vacancyResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { vacancyResponse ->
                        //заглушка
                        popularVacancyAdapter.differ.submitList(vacancyResponse)
                        nearbyVacancyAdapter.differ.submitList(vacancyResponse)
                        swipe_refresh.isRefreshing = false
                    }
                }
            }
        })

        swipe_refresh.setOnRefreshListener {
            viewModel.getAllVacancies()
        }
    }

    private fun setupRecyclerView() {
        popularVacancyAdapter = PopularVacancyAdapter()
        rv_popular_jobs.apply {
            adapter = popularVacancyAdapter
            layoutManager = LinearLayoutManager((activity as MainActivity), LinearLayoutManager.HORIZONTAL, false)
        }

        nearbyVacancyAdapter = NearbyVacancyAdapter()
        rv_nearby_jobs.apply {
            adapter = nearbyVacancyAdapter
            layoutManager = LinearLayoutManager(activity as MainActivity)
        }
    }
}