package com.thabang.icant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.thabang.icant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {

            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val age = binding.age.text.toString()
            val userName = binding.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("user")
            val User = User(firstName, lastName, age, userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.age.text.clear()
                binding.userName.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


            }


        }
        val btnSearch = findViewById(R.id.readBtn2) as Button
        btnSearch.setOnClickListener {
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)
            val btnSearch = findViewById(R.id.updateBtn1) as Button
            btnSearch.setOnClickListener {
                val intent = Intent(this, UpdateData::class.java)
                startActivity(intent)
                val btnDelete = findViewById(R.id.deleteBtn) as Button
                btnDelete.setOnClickListener {
                    val intent = Intent(this, DeleteData::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}