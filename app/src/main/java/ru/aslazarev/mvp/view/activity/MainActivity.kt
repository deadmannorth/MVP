package ru.aslazarev.mvp.view.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.App
import ru.aslazarev.mvp.R
import ru.aslazarev.mvp.databinding.ActivityMainBinding
import ru.aslazarev.mvp.navigation.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter {
        App.instance.appComponent.presenter()
    }
    private val navigator = SupportAppNavigator(this, R.id.container)
    @Inject
    lateinit var navigatorHolder: NavigatorHolder



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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