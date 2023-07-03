package com.example.analisis_quimico_2.ui.alimentos

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.analisis_quimico_2.EjemploActivity
import com.example.analisis_quimico_2.R
import com.example.analisis_quimico_2.databinding.FragmentAlimentosBinding
import com.example.analisis_quimico_2.databinding.FragmentBebidasBinding
import com.google.firebase.storage.FirebaseStorage

class AlimentosFragment : Fragment() {

    private var _binding: FragmentAlimentosBinding? = null

    // Declaramos los botones y text view
    private lateinit var txtImage3: ImageView
    private lateinit var btnSelectImage3: Button
    private lateinit var btnUploadImage3: Button

    private var imageUri: Uri? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_alimentos, container, false)


        btnSelectImage3 = view.findViewById(R.id.btnSelectImage3)
        btnUploadImage3 = view.findViewById(R.id.btnUploadImage3)
        txtImage3 = view.findViewById(R.id.textImage3)



        btnSelectImage3.setOnClickListener {
            openGallery()
        }

        btnUploadImage3.setOnClickListener {
            uploadImageToFirebaseStorage()
        }




        return view
    }



    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            txtImage3.setImageURI(imageUri)
        }
    }

    private fun uploadImageToFirebaseStorage() {
        if (imageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("images/${System.currentTimeMillis()}.jpg")

            val uploadTask = imageRef.putFile(imageUri!!)

            uploadTask.addOnSuccessListener { taskSnapshot ->
                // La imagen se ha subido exitosamente
                Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
                // Ocurrió un error al subir la imagen
                Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }
}
