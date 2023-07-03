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

class Analasis_uno_Activity : AppCompatActivity() {

    //declaramos varaible de fireabse
    private lateinit var firebaseAuth: FirebaseAuth
    //declaramos varaibles en donde almacenaremos los datos
    private val analisi = FirebaseDatabase.getInstance().getReference("Servicio/AnalisisdeAgua")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analasis_uno)

        //declaramos variable para whatsaPp
        val imagewhat: ImageView =findViewById(R.id.ImageWhat)
        val btn_agua = findViewById<Button>(R.id.btnEnviasDatos)

        btn_agua.setOnClickListener { save_agua() }

        analisi.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //dataSnapshot.getValue(Encerados::class.java)
            }
        })



        //inicia funcion de whatsapp
        val ImageWhat = findViewById<ImageView>(R.id.ImageWhat)
        ImageWhat.setOnClickListener {
            val phoneNumber = "9191711019" // Número de teléfono al que se enviará el mensaje por WhatsApp

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
            startActivity(intent)
        }
        //termina funcion de whatsapp

    }
    //mandar correo, precio y concepto a nuestra base de datos
    private fun save_agua() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val concepto = findViewById<TextView>(R.id.Servicio1)
        val precio = findViewById<TextView>(R.id.Costo1)
        val enviar = findViewById<TextView>(R.id.btnEnviasDatos)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap <*, *>
                val email = userMap["correoElectronico"].toString()

                val analisis = Analisis(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                analisi.push().setValue(analisis)
                Toast.makeText(baseContext, "Datos Enviado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {   }
        })
    }




    data class Analisis(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"
    }
}