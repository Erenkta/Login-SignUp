package com.example.kotlin201

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin201.databinding.ActivityKullaniciEkranBinding

class KullaniciEkran : AppCompatActivity() {
    lateinit var binding:ActivityKullaniciEkranBinding
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKullaniciEkranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        preferences = getSharedPreferences("ProfilBilgileri", MODE_PRIVATE)
        var kullanici = preferences.getString("kullaniciAdi","").toString()
        var message:String = "Hosgeldiniz sayin ${kullanici}. Oturumunuz Acildi"
        binding.Message.setText(message)
    }
}