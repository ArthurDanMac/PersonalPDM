package com.upiita.practica1.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upiita.practica1.R
import com.upiita.practica1.databinding.ActivitySesioniniciadaBinding
import com.upiita.practica1.model.UserProvider

class SesionIniciadaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySesioniniciadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySesioniniciadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usar Toolbar con ViewBinding
        setSupportActionBar(binding.menuBar)

        println("Datos ${UserProvider.emailUser} ${UserProvider.contraUser}")
        binding.txtUser.text=UserProvider.nameUser
        binding.txtCorreo.text= UserProvider.emailUser


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opc_change_passwd -> {
                Toast.makeText(this, "Cambiar contraseña", Toast.LENGTH_LONG).show()
                val validar= Intent(this, CambiarPassActivity::class.java)
                startActivity(validar)
                true

            }
            R.id.opc_logout -> {
                Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show()
                val login= Intent(this, MainActivity::class.java)
                startActivity(login)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}