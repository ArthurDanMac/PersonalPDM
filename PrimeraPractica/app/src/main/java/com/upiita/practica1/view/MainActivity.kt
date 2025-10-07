package com.upiita.practica1.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import com.upiita.practica1.databinding.ActivityMainBinding
import com.upiita.practica1.model.UserProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            UserProvider.readUsers(this)

            val correo=binding.email.text.toString()
            var pass=binding.pass.text.toString()

            if( correo.isNotEmpty() && pass.isNotEmpty() ){
                pass = UserProvider.sha256(pass)
                correo.trim()

                if (UserProvider.validacion(correo, pass)) {
                    val intent = Intent(this, SesionIniciadaActivity::class.java)
                    startActivity(intent)
                }else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                    println("Campos vacios")
                }

            }else {
                Toast.makeText(this, "Campo(s) Vacíos", Toast.LENGTH_LONG).show()
                println("Campos vacios")
            }
            binding.email.text.clear()
            binding.pass.text.clear()
        }

        binding.btnOlvidar.setOnClickListener {
            val intent = Intent(this, ValidacionPassActivity::class.java)
            startActivity(intent)
        }
    }
}