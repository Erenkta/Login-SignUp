package com.example.kotlin201

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.kotlin201.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var preferences:SharedPreferences //İçeride kullanmak için bir preferences oluşturduk
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Tıklanabilir bir Text yapma
        var text:String = "Henüz Kayıt Olmadınız mı ? Buraya Tıklayarak Kayıt Olun !"
        var ss:SpannableString = SpannableString(text)
        var fcsBlue:ForegroundColorSpan = ForegroundColorSpan(Color.BLUE)
        val clickableSpan:ClickableSpan = object : ClickableSpan(){
            override fun onClick(textView: View) {
                Toast.makeText(this@MainActivity,"Kayıt Sayfasına Yönlendiriliyor",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, KayitSayfa::class.java))
            }

            override fun updateDrawState(ds: TextPaint) { //Tıklandıktan sonraki state değişimi
                super.updateDrawState(ds)
                ds.setColor(Color.GRAY)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan,27,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) //Tıklanabilir yaptı
        ss.setSpan(fcsBlue,27,55,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) //Renk Ayarladı
        binding.textView.setText(ss)
        binding.textView.movementMethod = LinkMovementMethod.getInstance() //Bu olmadan çalışmadı dikkat
        binding.textView.highlightColor = Color.TRANSPARENT
        //Tıklanabilir txt Bitiş

        preferences = getSharedPreferences("ProfilBilgileri", MODE_PRIVATE) //Hangi xml dosyasındaki verileri çekicez bunu belirttik
        binding.btnGiris.setOnClickListener {
            var kayitliKullanici = preferences.getString("kullaniciAdi","")
            var kayitliSifre = preferences.getString("kullaniciSifre","")
            //Şimdi kullanicinin gireceği bilgileri alıyoruz Daha sonra karşılaştırma yapıcaz
            var girilenKullanici = binding.userName.text.toString()
            var girilenSifre = binding.userPassword.text.toString()
            //Karşılaştırmayı Yapıyoruz
            if((kayitliKullanici == girilenKullanici)&&(kayitliSifre == girilenSifre)){
                startActivity(Intent(this@MainActivity,KullaniciEkran::class.java))
            }
            else{
                Toast.makeText(this,"Kayitli Kullanici Bulunamadi",Toast.LENGTH_SHORT).show()
            }



        }
    }
}