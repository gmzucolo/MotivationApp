package com.example.gmzucolo.motivationapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gmzucolo.motivationapp.R
import com.example.gmzucolo.motivationapp.databinding.ActivityUserBinding
import com.example.gmzucolo.motivationapp.utils.MotivationConstants
import com.example.gmzucolo.motivationapp.security.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.btSave.setOnClickListener(this)
        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(applicationContext).getValue(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name: String = binding.edName.text.toString()

        if (name != "") {
            SecurityPreferences(applicationContext).saveValue(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                R.string.validation_mandatory_name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}