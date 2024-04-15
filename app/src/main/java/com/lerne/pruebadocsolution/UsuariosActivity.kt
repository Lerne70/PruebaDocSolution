package com.lerne.pruebadocsolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lerne.pruebadocsolution.databinding.ActivityUsuariosBinding

class UsuariosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsuariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elemetsUI()
    }

    private fun elemetsUI() {
        binding.addUser.setOnClickListener { navigateNewUser() }
    }

    private fun navigateNewUser() {
        val intent = Intent(this, RegistrarUsuarioActivity::class.java)
        startActivity(intent)
    }
}