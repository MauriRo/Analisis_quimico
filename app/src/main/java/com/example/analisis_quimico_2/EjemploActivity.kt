package com.example.analisis_quimico_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class EjemploActivity : AppCompatActivity() {

    /*private lateinit var etName: EditText
    private lateinit var etCity: EditText
    private lateinit var btnAddData: Button

    private lateinit var database: FirebaseDatabase*/

    //declaramos varaible de fireabse
    private lateinit var firebaseAuth: FirebaseAuth
    //declaramos varaibles en donde almacenaremos los datos
    private val empoy = FirebaseDatabase.getInstance().getReference("Concepto")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejemplo)

        val btn_subir = findViewById<Button>(R.id.btnAddData)



        btn_subir.setOnClickListener { save_subir1() }

        empoy.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //dataSnapshot.getValue(Encerados::class.java)
            }
        })



       /* etName = findViewById(R.id.etName)
        etCity = findViewById(R.id.etCity)
        btnAddData = findViewById(R.id.btnAddData)

        // Inicializa la instancia de Firebase Realtime Database
        database = FirebaseDatabase.getInstance()

        val dataRef = database.reference.child("Nombres")

        btnAddData.setOnClickListener {
            val name = etName.text.toString()
            val city = etCity.text.toString()

            // Crea un nuevo nodo en la base de datos con los datos ingresados
            val newData = dataRef.push()
            newData.child("name").setValue(name)
            newData.child("city").setValue(city)

            etName.text.clear()
            etCity.text.clear()
        }*/

    }
    //mandar correo, precio y concepto a nuestra base de datos
    private fun save_subir1() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val nombre = findViewById<TextView>(R.id.etName)
        val ciudad = findViewById<TextView>(R.id.etCity)
        val calle = findViewById<TextView>(R.id.etCalle)
        val enviar = findViewById<TextView>(R.id.btnAddData)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap <*, *>
                val email = userMap["correoElectronico"].toString()

                val empoye = Empoye(
                    nombre.text.toString(),
                    ciudad.text.toString(),
                    calle.text.toString(),
                    email
                )
                empoy.push().setValue(empoye)
                Toast.makeText(baseContext, "Datos Enviado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {   }
        })
    }




    data class Empoye(val nombre: String = "", val ciudad: String = "", val calle: String = "" ,val Email: String = "") {
        override fun toString() = nombre + "\t" + ciudad + "\t" + calle + "\t" + Email + "\t"
    }
}