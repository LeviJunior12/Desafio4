package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.levi.desafio4.R
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*

class SplashScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        showSplashScreen(view)
        return view
    }

    private fun showSplashScreen(view: View) {
        view.ivSplash.alpha = 0f
        view.ivSplash.animate().setDuration(2000).alpha(1f).withEndAction {
            findNavController().navigate(R.id.action_splashScreen_to_loginFragment)
        }
    }

}