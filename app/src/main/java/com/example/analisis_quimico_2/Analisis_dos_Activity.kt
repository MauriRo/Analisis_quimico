package com.example.analisis_quimico_2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class Analisis_dos_Activity : AppCompatActivity() {

    //declaramos varaible de fireabse
    private lateinit var firebaseAuth: FirebaseAuth
    //declaramos varaibles en donde almacenaremos los datos
   /* private val quimico = FirebaseDatabase.getInstance().getReference("Servicio/AnalisisQuimico")*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analisis_dos)
        //declaramos variable para whatsaPp
        val imagewhat2: ImageView =findViewById(R.id.fab)
        val btn_quimico = findViewById<Button>(R.id.btnEnviarDatos2)
        val btn_comprar = findViewById<Button>(R.id.btnComprar)

      /*  btn_quimico.setOnClickListener { save_quimico() }

        quimico.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //dataSnapshot.getValue(Encerados::class.java)
            }
        })*/

        btn_quimico.setOnClickListener()
        {

            val i = Intent(this,EjemploActivity::class.java)
            startActivity(i)

        }

        //inicia funcion de whatsapp
        val ImageWhat2 = findViewById<ImageView>(R.id.fab)
        ImageWhat2.setOnClickListener {
            val phoneNumber = "9191711019" // Número de teléfono al que se enviará el mensaje por WhatsApp

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
            startActivity(intent)
        }
        //termina funcion de whatsapp

    }
    //mandar datos como text view
   /* //mandar correo, precio y concepto a nuestra base de datos
    private fun save_quimico() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val concepto = findViewById<TextView>(R.id.Servicio2)
        val precio = findViewById<TextView>(R.id.Costo2)
        val enviar = findViewById<TextView>(R.id.btnEnviarDatos2)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap <*, *>
                val email = userMap["correoElectronico"].toString()

                val quimicos = Quimicos(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                quimico.push().setValue(quimicos)
                Toast.makeText(baseContext, "Datos Enviado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {   }
        })*/
/*
    }




    data class Quimicos(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"
    }
*/

}

