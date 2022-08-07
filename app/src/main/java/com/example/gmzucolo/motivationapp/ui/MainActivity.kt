package com.example.gmzucolo.motivationapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.gmzucolo.motivationapp.R
import com.example.gmzucolo.motivationapp.data.Mock
import com.example.gmzucolo.motivationapp.databinding.ActivityMainBinding
import com.example.gmzucolo.motivationapp.utils.MotivationConstants
import com.example.gmzucolo.motivationapp.security.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var category: Int = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding()
        handleUserName()
        handleFilter(id = R.id.im_all)
        handleNextPhrase()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.im_all, R.id.im_happy, R.id.im_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.tvPhrase.text = Mock().getPhrase(category)
    }

    private fun binding() {
        binding.btNewPhrase.setOnClickListener(this)
        binding.imAll.setOnClickListener(this)
        binding.imHappy.setOnClickListener(this)
        binding.imSunny.setOnClickListener(this)
    }

    private fun handleFilter(id: Int) {
        resetFilter()
        setItemFiltered(id)
    }

    private fun setItemFiltered(id: Int) {
        when (id) {
            R.id.im_all -> {
                binding.imAll.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.white
                    )
                )
                category = MotivationConstants.FILTER.ALL
            }
            R.id.im_happy -> {
                binding.imHappy.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.white
                    )
                )
                category = MotivationConstants.FILTER.HAPPY
            }
            R.id.im_sunny -> {
                binding.imSunny.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.white
                    )
                )
                category = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun resetFilter() {
        binding.imAll.setColorFilter(
            ContextCompat.getColor(
                applicationContext,
                R.color.dark_purple
            )
        )
        binding.imHappy.setColorFilter(
            ContextCompat.getColor(
                applicationContext,
                R.color.dark_purple
            )
        )
        binding.imSunny.setColorFilter(
            ContextCompat.getColor(
                applicationContext,
                R.color.dark_purple
            )
        )
    }

    private fun handleUserName() {
        val name =
            SecurityPreferences(applicationContext).getValue(MotivationConstants.KEY.USER_NAME)
        binding.tvUserName.text = "Olá, $name!"
    }
}