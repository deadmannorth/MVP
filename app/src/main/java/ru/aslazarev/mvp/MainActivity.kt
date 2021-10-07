package ru.aslazarev.mvp

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.databinding.ActivityMainBinding
import ru.aslazarev.mvp.view.BackButtonListener
import ru.terrakok.cicerone.android.pure.AppNavigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }
    private val navigator = SupportAppNavigator(this, R.id.container)



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
        if(it is BackButtonListener && it.backPressed()){
            return
        }
    }
    presenter.backPressed()
    }


}