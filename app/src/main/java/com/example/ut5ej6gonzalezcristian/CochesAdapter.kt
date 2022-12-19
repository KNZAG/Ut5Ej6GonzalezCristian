package com.example.ut5ej6gonzalezcristian

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ut5ej6gonzalezcristian.databinding.ItemCochesAltBinding

class CochesAdapter(
    private val vehiculos: List<Vehiculo>, private val listener: Eventos/*TE PASO LA INTERFACE*/
) : RecyclerView.Adapter<CochesAdapter.ClaseInterna>() {

    private lateinit var context: Context

    inner class ClaseInterna(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCochesAltBinding.bind(view)

        fun setListener(vehiculo: Vehiculo) {
            binding.root.setOnClickListener {
                 //binding.fondo.pulsacionCorta(Vehiculo)
                listener.pulsacionCorta(vehiculo)
             }

            binding.root.setOnLongClickListener {
                listener.pulsacionLarga(vehiculo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseInterna {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_coches_alt, parent, false)
        return ClaseInterna(view)
    }

    override fun onBindViewHolder(holder: ClaseInterna, position: Int) {
        val vehiculo = vehiculos.get(position)
        with(holder) {
            binding.dniCliente.text = vehiculo.dni
            binding.matricula.text = vehiculo.matricula
            binding.modeloCoche.text = vehiculo.modeloCoche
            if (vehiculo.estado) {
                binding.fondo.setBackgroundColor(Color.GREEN)
            } else {
                binding.fondo.setBackgroundColor(Color.RED)
            }
            setListener(vehiculo)
        }

    }

    override fun getItemCount() = vehiculos.size
}
