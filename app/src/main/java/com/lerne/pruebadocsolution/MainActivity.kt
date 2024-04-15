package com.lerne.pruebadocsolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lerne.pruebadocsolution.databinding.ActivityMainBinding
import com.lerne.pruebadocsolution.model.BodyLogin
import com.lerne.pruebadocsolution.model.Login
import com.lerne.pruebadocsolution.model.loginResponse
import com.lerne.pruebadocsolution.services.Api
import com.lerne.pruebadocsolution.services.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var refrofit: Retrofit

    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refrofit = Api.getRetrofit()

        elemntsUI()
    }

    fun elemntsUI() {
        binding.loginButton.setOnClickListener {
            getUserAndPassword()
        }
    }

    fun getUserAndPassword() {
        val bodyLogin:BodyLogin = BodyLogin(
            Username = binding.editTextUsername.text.toString(),
            Password = binding.editTextPassword.text.toString())
        val login:Login = Login(bodyLogin)

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse:Response<loginResponse> =
                refrofit.create(ApiServices::class.java).login(login)
            if(myResponse.isSuccessful) {
                val response: loginResponse? = myResponse.body()
//                Log.i("Lerne", "getUserAndPassword: ${response}")
                if(response != null) {
                    token = response.Body.Token
                    runOnUiThread {
                        navigateNewUser()
                    }
                }

            } else {
                Log.i("Lerne", "No error al enviar la informacion")
            }
        }
    }

    fun navigateNewUser() {
        val intent = Intent(this, RegistrarUsuarioActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

}