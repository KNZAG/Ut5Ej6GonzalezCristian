package com.example.ut5ej6gonzalezcristian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut5ej6gonzalezcristian.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var usuario1 = Usuario("0", "0", 0)
    private var usuario2 = Usuario("1", "1", 1)
    private var usuario3 = Usuario("user", "123", 0)
    private var usuario4 = Usuario("user2", "321", 1)

    private val listaUsuarios = listOf(usuario1, usuario2, usuario3, usuario4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAceptar.setOnClickListener {
            val login = binding.txLogin.text.toString().trim()
            val contra = binding.txContra.text.toString().trim()
            val pos = existeUsuario(login)

            if (pos != -1 && listaUsuarios[pos].contrasena == contra) {
                Snackbar.make(binding.root, "Sesión iniciada", Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.btAceptar).show()

                val intent = Intent(this, ConsultaActivity::class.java)
                //intent.putExtra("usuario", listaUsuarios.get(pos))
                intent.putExtra("tipoUsuario", listaUsuarios.get(pos).tipoUsuario)
                startActivity(intent)
                finish()

            } else if (pos == -1) {
                Snackbar.make(binding.root, "Usuario/Contraseña incorrecta", Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.txContra).show()
            }
        }

        binding.btCancelar.setOnClickListener {
            limpiar()
        }
    }

    private fun limpiar() {
        binding.txLogin.setText("")
        binding.txContra.setText("")
        binding.txLogin.requestFocus()
    }

    private fun existeUsuario(usuario: String) = listaUsuarios.indexOf(Usuario(usuario))

}