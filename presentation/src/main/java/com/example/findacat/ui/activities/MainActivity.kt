package com.example.findacat.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findacat.R
import com.example.findacat.app.App
import com.example.findacat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}