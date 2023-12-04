package com.lalabib.mhsid.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.mhsid.adapter.MahasiswaAdapter
import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.databinding.ActivityHomeBinding
import com.lalabib.mhsid.databinding.ContentHomeBinding
import com.lalabib.mhsid.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeContentBinding: ContentHomeBinding
    private lateinit var search: SearchView
    private lateinit var recyclerView: RecyclerView
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
        recyclerView = binding.contentHome.rvMahasiswa

        mahasiswaAdapter = MahasiswaAdapter { mahasiswa ->
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, mahasiswa.uniqueId)
                startActivity(this)
                search.clearFocus()
            }
        }

        homeContentBinding.rvMahasiswa.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = mahasiswaAdapter
        }
    }

    private fun search() {
        search = binding.searchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isNotEmpty()) {
                    handleSearchQuery(query)
                }
                return true
            }
        })
    }

    private fun handleSearchQuery(name: String) {
        homeViewModel.getMahasiswa(name).observe(this@HomeActivity) { mahasiswa ->
            if (mahasiswa != null) {
                when (mahasiswa) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        mahasiswaAdapter.submitList(mahasiswa.data.takeIf { it.isNotEmpty() }) {
                            recyclerView.scrollToPosition(0)
                        }
                    }

                    is Result.Error -> {}
                }
            }
        }
    }
}