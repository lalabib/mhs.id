package com.lalabib.mhsid.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lalabib.mhsid.R
import com.lalabib.mhsid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}