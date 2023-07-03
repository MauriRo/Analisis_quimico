package com.example.analisis_quimico_2.ui.AguasResiduales

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.analisis_quimico_2.AguaFormularioActivity
import com.example.analisis_quimico_2.AguasCarrActivity
import com.example.analisis_quimico_2.R
import com.example.analisis_quimico_2.databinding.FragmentAguasresidualesBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import android.content.Intent as Intent1


class AguasResidualesFragment : Fragment() {

    private var _binding: FragmentAguasresidualesBinding? = null

    private lateinit var txtImage: ImageView
    private lateinit var btnSelectImage: Button
    private lateinit var btnUploadImage: Button
    private var imageUri: Uri? = null
    private lateinit var textParametros1: TextView
    private lateinit var textFormulario1: TextView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //variables para el pdf





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_aguasresiduales, container, false)

        btnSelectImage = view.findViewById(R.id.btnSelectImage)
        btnUploadImage = view.findViewById(R.id.btnUploadImage)
        txtImage = view.findViewById(R.id.txtImage)
        textParametros1 = view.findViewById(R.id.textParametros1)
        textFormulario1 = view.findViewById(R.id.textFormulario1)

        btnSelectImage.setOnClickListener {
            openGallery()
        }

        btnUploadImage.setOnClickListener {
            uploadImageToFirebaseStorage()
        }

        textParametros1.setOnClickListener {
            val intent = Intent1(activity, AguasCarrActivity::class.java)
            startActivity(intent)
        }
        textFormulario1.setOnClickListener {
            val intent = Intent1(activity, AguaFormularioActivity::class.java)
            startActivity(intent)
        }


        return view
    }




    private fun openGallery() {
        val intent = Intent1(Intent1.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent1?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            imageUri = data.data
            txtImage.setImageURI(imageUri)
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





