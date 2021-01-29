package com.levi.desafio4.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class StorageFirebase {

    lateinit var storageReference: StorageReference
    var urlImage: MutableLiveData<String> = MutableLiveData()

    private fun getReferenceStorage(location: String) {
        storageReference =  FirebaseStorage.getInstance().getReference(location)
    }

    fun uploadImage(data: Intent, location: String){
        getReferenceStorage(location)
        val uploadFile = storageReference.putFile(data.data!!)
        val task = uploadFile.continueWithTask {task ->
            if (task.isSuccessful) {
            }
            storageReference.downloadUrl
        }.addOnCompleteListener {task ->
            if(task.isSuccessful) {
                val downloadUri = task.result
                urlImage.value = downloadUri!!.toString().substring(0, downloadUri.toString().indexOf("&token"))
            }
        }
    }
}