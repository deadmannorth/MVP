package ru.aslazarev.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatActivity
import ru.aslazarev.mvp.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}