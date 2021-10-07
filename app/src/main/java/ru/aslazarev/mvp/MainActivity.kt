package ru.aslazarev.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.aslazarev.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    val viewUpdater = ViewUpdater(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            viewUpdater.updateView(it.id)
        }

        binding?.btnCounter1?.setOnClickListener(listener)
        binding?.btnCounter2?.setOnClickListener(listener)
        binding?.btnCounter3?.setOnClickListener(listener)

    }

    override fun setButtonText(index: Int, text: String) = with(binding) {
        when(index){
            0 -> btnCounter1.text = text
            1 -> btnCounter2.text = text
            2 -> btnCounter3.text = text
        }

    }
}