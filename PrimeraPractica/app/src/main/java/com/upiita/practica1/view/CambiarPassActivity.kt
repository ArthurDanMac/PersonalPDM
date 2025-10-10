package com.upiita.practica1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upiita.practica1.databinding.ActivityCambiarpassBinding
import com.upiita.practica1.model.UserProvider

class CambiarPassActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCambiarpassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarpassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            val contra1 = binding.pass1.text.toString()
            val contra2 = binding.pass2.text.toString()
            if (contra1.isNotEmpty() && contra2.isNotEmpty()) {
                if (contra1.equals(contra2)) {
                    val pass = contra1
                    if (pass.length >= 8) {
                        if (pass.any { char: Char -> UserProvider.chars.contains(char) }) {
                            val nombre = UserProvider.nameUser
                            val contra = UserProvider.sha256(contra1)
                            UserProvider.updateUserData(applicationContext, nombre, contra)

                            val login = Intent(this, MainActivity::class.java)
                            startActivity(login)
                        } else {
                            Toast.makeText(this,"No contiene caracteres especiales",Toast.LENGTH_LONG).show()
                            println("chars esp")
                        }
                    } else {
                        Toast.makeText(this, "Contraseña menor a 8 caracteres", Toast.LENGTH_LONG)
                            .show()
                        println("longitd menor")
                    }
                } else {
                    Toast.makeText(this, "Contraseñas no iguales", Toast.LENGTH_LONG).show()
                    println("no iguales")
                }
            } else {
                Toast.makeText(this, "Campo(s) vacios", Toast.LENGTH_LONG).show()
                println("Campos vacios")
            }
            binding.pass1.text.clear()
            binding.pass2.text.clear()

        }

    }
}
