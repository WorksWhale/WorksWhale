package com.example.workswhale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workswhale.databinding.ActivityMainBinding
import com.example.workswhale.databinding.FragmentContactListBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout_main, ContactListFragment()).commit()

    }
}