package com.example.analisis_quimico_2.ui.bebidas

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
import com.example.analisis_quimico_2.R
import com.example.analisis_quimico_2.databinding.FragmentAguasresidualesBinding
import com.example.analisis_quimico_2.databinding.FragmentBebidasBinding
import com.google.firebase.storage.FirebaseStorage

class BebidasFragment : Fragment() {

    private var _binding: FragmentBebidasBinding? = null

    private lateinit var txtImage1: ImageView
    private lateinit var btnSelectImage2: Button
    private lateinit var btnUploadImage2: Button
    private var imageUri: Uri? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bebidas, container, false)


        btnSelectImage2 = view.findViewById(R.id.btnSelectImage2)
        btnUploadImage2 = view.findViewById(R.id.btnUploadImage2)
        txtImage1 = view.findViewById(R.id.textImage1)

        btnSelectImage2.setOnClickListener {
            openGallery()
        }

        btnUploadImage2.setOnClickListener {
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
            txtImage1.setImageURI(imageUri)
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