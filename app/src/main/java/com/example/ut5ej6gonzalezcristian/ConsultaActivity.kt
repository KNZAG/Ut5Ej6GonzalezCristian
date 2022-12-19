package com.example.ut5ej6gonzalezcristian

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ut5ej6gonzalezcristian.databinding.ActivityConsultaBinding
import com.google.android.material.snackbar.Snackbar


class ConsultaActivity : AppCompatActivity(), Eventos {

    private lateinit var binding: ActivityConsultaBinding
    private lateinit var linearLayout: LinearLayoutManager

    private var vehiculo1 = Vehiculo(
        "78945612V",
        "Pepe",
        "cosascrisco@gmail.com",
        "321",
        "Ford Bronco",
        "22/12/2022",
        "Nada",
        true
    )
    private var vehiculo2 = Vehiculo(
        "71708975Z",
        "Luis",
        "luis@gmail.com",
        "123",
        "Seat Panda",
        "18/12/2022",
        "Piñazo",
        false
    )
    private var listaVehiculo = mutableListOf(vehiculo1, vehiculo2)
   // val usuarioRegistrado = intent.getParcelableExtra<Usuario>("usario")
    private var tipoUsuario: Int = 0

    private val devolverNuevoCoche =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                //val code = activityResult.data?.getStringExtra("codigo").orEmpty()
               // binding.codEspe.text = code
                //Toast.makeText(this, code, Toast.LENGTH_SHORT).show()
                //Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tipoUsuario = usuarioRegistrado!!.tipoUsuario

        tipoUsuario = intent.getIntExtra("tipoUsuario", 0)

        binding.btAnadirCoche.setOnClickListener {
            val intent = Intent(this, AnadirVehiculo::class.java)
            devolverNuevoCoche.launch(intent)
        }

        cargarDatosRecyclerView()
        crearNuevosCoches()
    }

    private fun cargarDatosRecyclerView() {
        binding.recyclerview.adapter = CochesAdapter(listaVehiculo, this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }

    private fun crearNuevosCoches() {
        if (tipoUsuario == 1) {
            binding.btAnadirCoche.visibility = View.VISIBLE
        } else {
            binding.btAnadirCoche.visibility = View.GONE
        }
    }

    override fun pulsacionLarga(vehiculo: Vehiculo): Boolean {
        if (tipoUsuario == 0) {
            if (vehiculo.estado) {
                Toast.makeText(this, "VEHÍCULO YA ENTREGADO", Toast.LENGTH_SHORT).show()
            } else {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("VEHICULO LISTO")
                builder.setMessage("¿REALIZAR ENTREGA?")

                builder.setPositiveButton("ACEPTAR") { _/*NADA*/, _/*NADA*/ ->
                    vehiculo.estado = true
                    Toast.makeText(this, "ENTREGA REALIZADA", Toast.LENGTH_SHORT).show()
                    binding.recyclerview.adapter?.notifyDataSetChanged()

                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "*/*"
                        putExtra(Intent.EXTRA_EMAIL, vehiculo.email)
                        putExtra(Intent.EXTRA_SUBJECT, "Vehiculo listo")
                        putExtra(Intent.EXTRA_STREAM, "El vehiculo esta listo para su recogida")
                    }

                }
                builder.setNeutralButton("CANCELAR", null)
            }
        } else {
            Toast.makeText(this, "FUNCIÓN EXCLUSIVA MECANICO (LARGA)", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun pulsacionCorta(vehiculo: Vehiculo) {
        if (tipoUsuario == 1) {

            listaVehiculo.remove(vehiculo)
            Toast.makeText(this, "VEHÍCULO ELIMINADO", Toast.LENGTH_SHORT).show()
            binding.recyclerview.adapter?.notifyDataSetChanged()

        } else {
            Toast.makeText(this, "FUNCIÓN EXCLUSIVA RECEPCIONISTA (CORTA)", Toast.LENGTH_SHORT).show()
        }

    }

    /*private fun enviarCorreo() {
        when {
            ContextCompat.checkSelfPermission(
                this@ConsultaActivity, Manifest.permission.) == PackageManager.PERMISSION_GRANTED/*PERMISO CONCEDIDO*/
            -> {
               // val intent = Intent(Intent.ACTION_SEND).apply {
                   //
                    /*putExtra(Intent.EXTRA_EMAIL, vehiculo.email)
                    putExtra(Intent.EXTRA_SUBJECT, "Vehiculo listo")
                    putExtra(Intent.EXTRA_STREAM, "El vehiculo esta listo para su recogida")
                }*/
            }
            /*MIRAS SI YA ESTABA CONCEDIDO EL PERMISO*/
            shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {

                val builder = AlertDialog.Builder(this@ConsultaActivity)
                builder.setTitle("PERMISO PARA MANDAR CORREO ELECTRÓNICO")
                builder.setMessage("Necesario para enviar el aviso")
                builder.setPositiveButton(android.R.string.ok) { _/*NADA*/, _/*NADA*/ ->
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
                builder.setNeutralButton(android.R.string.cancel, null)
                builder.show()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    */

   /* private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean/*¿ESE PERMISO ME LO HAS CONCEDIDO?*/ ->
            if (isGranted) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }*/
}