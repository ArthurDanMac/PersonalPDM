package com.upiita.practica1.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.upiita.practica1.viewmodel.UserViewModel
import java.io.File
import java.io.FileWriter
import java.security.MessageDigest

class UserProvider {
    companion object {
        private lateinit var listU: List<UserModel>
        lateinit var emailUser: String
        lateinit var contraUser: String
        lateinit var nameUser: String
        lateinit var preguntaUser: String
        lateinit var respuestaUser: String
        val chars: CharArray = charArrayOf('!', '¡', '#', '$', '%', '&', '?', '¿', '-', '_');


        fun readUsers(context: Context) {
            try {
                val file = File(context.filesDir, "usuarios.json")
                if (!file.exists())
                    copiarJson(context)
                val json = file.bufferedReader().use { it.readText() }
                val gson = Gson()
                val userType = object : TypeToken<List<UserModel>>() {}.type
                listU = gson.fromJson(json, userType)
                println("${listU }}")
            } catch (e: Exception) {
                listU = emptyList()
                println("Error: ${e}")
            }
        }

        // Función para generar hash SHA-256
        fun sha256(input: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(input.toByteArray(Charsets.UTF_8))
            return hashBytes.joinToString("") { "%02x".format(it) }
        }

        fun validacion(em: String, pw: String): Boolean {
            listU.forEach {
                println("app ${em} ${pw} | lista ${it.email} ${it.passwd}")
                if (em.isNotEmpty() && em == it.email
                    && pw.isNotEmpty() && pw == it.passwd
                ) {//&& pwSha256 == sha256(it.passwd)
                    println("app ${em} ${pw} | lista ${it.email} ${it.passwd}")
                    Log.d("MiTag", "Valido");
                    emailUser = it.email
                    contraUser = it.passwd
                    nameUser = it.name
                    preguntaUser = it.pregunta
                    respuestaUser = it.respuesta
                    println("${emailUser} ${contraUser} ${nameUser} ${contraUser} ${preguntaUser} ${respuestaUser}")
                    return true
                }
            }
            Log.d("MiTag", "Invalido");
            if (listU.isEmpty())
                Log.d("MiTag", "Lista vacia");
            return false
        }

        fun olvidepass(em: String): String {
            listU.forEach {
                println("correobuscar: ${em} comparacion: ${it.email} ${it.pregunta}")
                if (em == it.email) {
                    println("${it.pregunta}")
                    respuestaUser = it.respuesta
                    return it.pregunta
                }
            }
            return ""
        }


        fun updateUserData(context: Context,nombre : String,contra:String) {
            val file = File(context.filesDir, "usuarios.json")
            val gson = Gson()
            if(file.exists() && file.length()>0) {
               file.readText()
               val listaFinal = listU.toMutableList()
               listaFinal.forEach {
                   if(it.name == nombre){
                       it.passwd=contra
                   }
               }
                val updatedJson = gson.toJson(listaFinal)
                try {
                    file.writeText(updatedJson)
                    println("Se hizo modificacion")
                } catch (e: Exception) {
                    println("Error: ${e}")
                }
            }
        }

        fun copiarJson(context: Context) {
            val file = File(context.filesDir, "usuarios.json")
            if (!file.exists()) {
                context.assets.open("users.json").use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

            }
        }

    }
}