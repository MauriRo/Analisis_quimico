package com.example.analisis_quimico_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.analisis_quimico_2.databinding.ActivityMenuPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MenuPrincipalActivity : AppCompatActivity() {

    //variables principales para la autenticacion con firebase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenuPrincipal.toolbar)

        binding.appBarMenuPrincipal.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val ImageWhat = findViewById<ImageView>(R.id.fab)
            ImageWhat.setOnClickListener {
                val phoneNumber = "9191711019" // Número de teléfono al que se enviará el mensaje por WhatsApp

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
                startActivity(intent)
            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_Bebidas, R.id.nav_Alimentos
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //inicializamos firebase
        firebaseAuth = Firebase.auth


        // Obtener la referencia de la base de datos
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("Usuarios")

        // Obtener la ID del usuario actual
        val userID = firebaseAuth.currentUser?.uid

        databaseReference.child(userID!!).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                // Actualizar los elementos de la interfaz de usuario en la cabecera del menú hamburguesa
                val headerView = navView.getHeaderView(0)
                val txtNombre1 = headerView.findViewById<TextView>(R.id.txtNombreUusario)
                val txtEmail1 = headerView.findViewById<TextView>(R.id.txtEmailNuevo)
                txtNombre1.text = nombre
                txtEmail1.text = email
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de la base de datos
            }
        })



    }


   //funcion de Cerrar Sesion
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_salir ->{
                cerrarSesion()
            }
        }
        return super.onOptionsItemSelected(item)
    } //termina la funcion Cerrar Sesion

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    //cerrar sesion
    private fun cerrarSesion()
    {
        firebaseAuth.signOut()
        Toast.makeText(baseContext,"Sesión Cerrada Correctamente", Toast.LENGTH_SHORT).show()
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }//Termina la funcion cerrar sesion

}




