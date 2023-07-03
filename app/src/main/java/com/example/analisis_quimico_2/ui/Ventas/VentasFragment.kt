package com.example.analisis_quimico_2.ui.Ventas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.analisis_quimico_2.*
import com.example.analisis_quimico_2.databinding.FragmentVentasBinding

class VentasFragment : Fragment() {

    private var _binding: FragmentVentasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVentasBinding.inflate(inflater, container, false)

        //OBTENER LA REFERENCIA DEL BOTON
            val ButtonAnalisis1 = binding.btnAnalisis1
            val ButtonAnalisis2 = binding.btnAnalisis2
            val ButtonAnalisis3 = binding.btnAnalisis3
            val ButtonAnalisis4 = binding.btnAnalisis4
            val ButtonAnalisis5 = binding.btnAnalisis5
            val ButtonAnalisis6 = binding.btnAnalisis6

        // Agregar un OnClickListener
        ButtonAnalisis1.setOnClickListener {
            // Iniciar la actividad analisis 1
            val intent = Intent(activity, Analasis_uno_Activity::class.java)
            startActivity(intent)
        }

        //Boton de ventas
        ButtonAnalisis2.setOnClickListener {
            // Iniciar la actividad analisis 2
            val intent = Intent(activity, Analisis_dos_Activity::class.java)
            startActivity(intent)
        }

        ButtonAnalisis3.setOnClickListener {
            // Iniciar la actividad analisis 3
            val intent = Intent(activity, Analisis_tres_Activity3::class.java)
            startActivity(intent)
        }

        //Boton de ventas
        ButtonAnalisis4.setOnClickListener {
            // Iniciar la actividad analisis 4
            val intent = Intent(activity, Analisis_cuatro_Activity4::class.java)
            startActivity(intent)
        }
        ButtonAnalisis5.setOnClickListener {
            // Iniciar la actividad analisis 5
            val intent = Intent(activity, Analisis_cinco_Activity5::class.java)
            startActivity(intent)
        }

        //Boton de ventas
        ButtonAnalisis6.setOnClickListener {
            // Iniciar la actividad analisis 6
            val intent = Intent(activity, Analisis_seis_Activity6::class.java)
            startActivity(intent)
        }


        return binding.root
    }


}