package com.upiita.practica1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.upiita.practica1.databinding.ActivityValidacionpassBinding
import com.upiita.practica1.model.UserProvider

class ValidacionPassActivity: AppCompatActivity() {
    lateinit var binding: ActivityValidacionpassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityValidacionpassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserProvider.readUsers(this)

        binding.btnBuscar.setOnClickListener {
            val correito=binding.txtemail.text.toString()
            correito.trim()

            println("correito: ${correito}")

            val question =UserProvider.olvidepass(correito)
            println(" question: ${question}")

            if(question.isEmpty()){
                Toast.makeText(this, "Error en los campos", Toast.LENGTH_LONG).show()
                println("Fallido")
                binding.txtemail.text.clear()
            }else{
                binding.btnBuscar.isVisible=false
                binding.txtemail.isActivated=false
                binding.txtPregunta.isVisible=true
                binding.txtRespuesta.isVisible=true
                binding.btnValidar.isVisible=true
                binding.txtPregunta.text=question
            }

        }

        binding.btnValidar.setOnClickListener {
            var resp=binding.txtRespuesta.text.toString()

            if(resp.isEmpty()){
                Toast.makeText(this, "Respuesta Vacía", Toast.LENGTH_LONG).show()
                println("Campos vacios")
            }else{
                resp = resp.replace("\\s".toRegex(), "")
                resp.lowercase()

                val usur= UserProvider.respuestaUser

                println("${usur} || ${resp}")

                if(resp== usur){
                    println("Exito")
                    val cambiarPass= Intent(this, CambiarPassActivity::class.java)
                    startActivity(cambiarPass)
                }else{
                    Toast.makeText(this, "Respuesta Incorrecta", Toast.LENGTH_LONG).show()
                    println("Fallido")
                }

            }
            binding.txtRespuesta.text.clear()
        }
    }
}