package com.levi.desafio4.service

import android.content.Intent
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class StorageFirebase {

    lateinit var storageReference: StorageReference
    lateinit var urlImage: String

    private fun getReferenceStorage() {
        storageReference =  FirebaseStorage.getInstance().getReference("img")
    }

    fun uploadImage(data: Intent) {
        getReferenceStorage()
        val uploadFile = storageReference.putFile(data.data!!)
        val task = uploadFile.continueWithTask {task ->
            if (task.isSuccessful) {
                Log.i("IMAGEM", "IMAGEM CARREGADA COM SUCESSO")
            }
            storageReference.downloadUrl
        }.addOnCompleteListener {task ->
            if(task.isSuccessful) {
                val downloadUri = task.result
                urlImage = downloadUri!!.toString().substring(0, downloadUri.toString().indexOf("&token"))
            }
        }
    }
}