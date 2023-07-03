package com.example.analisis_quimico_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class AguaFormularioActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    //declaramos varaibles en donde almacenaremos los datos
    private val empoy = FirebaseDatabase.getInstance().getReference("Aguas Residuales")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agua_formulario)

        val btn_subir2 = findViewById<Button>(R.id.btnAddDataA)



        btn_subir2.setOnClickListener { save_subir2() }

        empoy.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //dataSnapshot.getValue(Encerados::class.java)
            }
        })
    }

    private fun save_subir2() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val nombre = findViewById<TextView>(R.id.etNameA)
        val ciudad = findViewById<TextView>(R.id.etCityA)
        val calle = findViewById<TextView>(R.id.etCalleA)
        val enviar = findViewById<TextView>(R.id.btnAddDataA)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap <*, *>
                val email = userMap["correoElectronico"].toString()

                val empoye2 = Empoye2(
                    nombre.text.toString(),
                    ciudad.text.toString(),
                    calle.text.toString(),
                    email
                )
                empoy.push().setValue(empoye2)
                Toast.makeText(baseContext, "Datos Enviado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {   }
        })
    }




    data class Empoye2(val nombre: String = "", val ciudad: String = "", val calle: String = "" ,val Email: String = "") {
        override fun toString() = nombre + "\t" + ciudad + "\t" + calle + "\t" + Email + "\t"
    }
}