package com.example.kotlin201

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin201.databinding.ActivityKayitSayfaBinding

class KayitSayfa : AppCompatActivity() {
    lateinit var binding: ActivityKayitSayfaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKayitSayfaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //SharedPreferences Oluşturma
        var sharedPreferences:SharedPreferences = this.getSharedPreferences("ProfilBilgileri", MODE_PRIVATE)
        //
        binding.btnKayitOnay.setOnClickListener { //Kayıt işlemi sorunsuz
            var kullaniciadi = binding.kayitName.text.toString()
            var kullanicisifre = binding.kayitPassword.text.toString()
            Toast.makeText(this,kullanicisifre,Toast.LENGTH_SHORT).show()
            var editor = sharedPreferences.edit()
            if(kullaniciadi.isNotEmpty() && kullanicisifre.isNotEmpty()){
                //veri ekleme
                editor.putString("kullaniciAdi",kullaniciadi).apply()
                editor.putString("kullaniciSifre",kullanicisifre).apply()
                //
                binding.kayitName.text.clear() //Kayıt olunduktan sonra içeride kalan yazıyı sildi
                binding.kayitPassword.text.clear() //Yukarıdakiyle aynı işlem
                Toast.makeText(this,"Kayıt onaylandı. Ana Sayfaya Yönlendiriliyorsunuz",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@KayitSayfa,MainActivity::class.java)) //Yönlenmesi yapıldı
            }
            else {
                Toast.makeText(this,"İlgili Alanları Boş Bırakmayınız",Toast.LENGTH_SHORT).show()
            }
        }
    }
}