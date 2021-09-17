package com.thabang.icant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thabang.icant.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class DeleteData : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn1.setOnClickListener {

            val userName = binding.userNameD.text.toString()
            if (userName.isNotEmpty())
                deleteData(userName)
            else
                Toast.makeText(this,"Please Enter username",Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).removeValue().addOnSuccessListener {
            binding.userNameD.text.clear()
            Toast.makeText(this,"Successfuly Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Delete",Toast.LENGTH_SHORT).show()

        }
    }
}