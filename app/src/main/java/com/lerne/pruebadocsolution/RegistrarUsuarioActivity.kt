package com.lerne.pruebadocsolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lerne.pruebadocsolution.databinding.ActivityRegistrarUsuarioBinding
import com.lerne.pruebadocsolution.model.BodyNewUser
import com.lerne.pruebadocsolution.model.NewUser
import com.lerne.pruebadocsolution.model.NewUserResponse
import com.lerne.pruebadocsolution.model.roles
import com.lerne.pruebadocsolution.services.Api
import com.lerne.pruebadocsolution.services.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit

class RegistrarUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarUsuarioBinding

    private lateinit var retrofit: Retrofit

    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = intent.getStringExtra("token").orEmpty()
        Log.i("Lerne", "onCreate: $token")

        retrofit = Api.getRetrofit()

        elementsUI()
    }

    private fun elementsUI() {
        binding.saveButton.setOnClickListener { saveNewUser() }
    }

    private fun saveNewUser() {
        val roles: roles = roles(
            Id = 2,
            Name = "Usuario Tradicional"
        )
        val listRoles: List<roles> = listOf(roles)

        val body: BodyNewUser = BodyNewUser(
            Tenant = null,
            Username = binding.editTextUsername.text.toString(),
            Password = binding.editTextPassword.text.toString(),
            Name = binding.editTextUsername.text.toString(),
            FatherLastName = binding.editTextFatherLastName.text.toString(),
            MotherLastName = binding.editTextMathoerLastName.text.toString(),
            Email = binding.editTextEmail.text.toString(),
            PhoneNumber = binding.editTextPhoneNumber.text.toString(),
            Metadata = null,
            Roles = listRoles
        )

        val newUser: NewUser = NewUser(body)

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<NewUserResponse> =
                retrofit.create(ApiServices::class.java).newUser(newUser, token)
            val response: NewUserResponse?
            if(myResponse.isSuccessful) {
                response = myResponse.body()
                Log.i("Lerne", "saveNewUser: Usuario registrado ${response}")
                if(response != null) {
                    if(response.IsOK) {
                        runOnUiThread {
                            binding.result.text = "Nuevo usuario creado"
                        }
                    }
                }
            } else {
                Log.i("Lerne", "saveNewUser: Usuario no registrado")
            }
        }
    }
}