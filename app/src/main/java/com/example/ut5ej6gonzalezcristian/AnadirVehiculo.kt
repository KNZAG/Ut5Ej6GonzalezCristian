package com.example.ut5ej6gonzalezcristian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut5ej6gonzalezcristian.databinding.ActivityAnadirVehiculoBinding



class AnadirVehiculo : AppCompatActivity() {

    private lateinit var binding: ActivityAnadirVehiculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAnadirVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}