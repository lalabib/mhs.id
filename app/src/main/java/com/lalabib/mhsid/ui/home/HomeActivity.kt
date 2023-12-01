package com.lalabib.mhsid.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.mhsid.R
import com.lalabib.mhsid.adapter.MahasiswaAdapter
import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.databinding.ActivityHomeBinding
import com.lalabib.mhsid.databinding.ContentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeContentBinding: ContentHomeBinding

    private lateinit var mahasiswaAdapter: MahasiswaAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeContentBinding = binding.contentHome

        setupAction()
        search()
    }

    private fun setupAction() {
        mahasiswaAdapter = MahasiswaAdapter()

        homeContentBinding.rvMahasiswa.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = mahasiswaAdapter
        }
    }

    private fun search() {
        val search = binding.searchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                handleSearchQuery(query)
                return true
            }
        })
    }

    private fun handleSearchQuery(name: String) {
        homeViewModel.getMahasiswa(name).observe(this@HomeActivity) { mahasiwa ->
            if (mahasiwa != null) {
                when (mahasiwa) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        mahasiswaAdapter.submitList(mahasiwa.data)
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this@HomeActivity,
                            getString(R.string.search_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}