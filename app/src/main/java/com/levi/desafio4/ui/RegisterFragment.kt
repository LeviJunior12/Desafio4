package com.levi.desafio4.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levi.desafio4.R
import com.levi.desafio4.service.firebase
import com.levi.desafio4.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.register_body.view.*


class RegisterFragment : Fragment() {

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String

    private val viewModel by viewModels<AuthViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AuthViewModel(firebase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        view.btn_create_account.setOnClickListener {
            createAccount(view)
            Log.i("ERROR REGISTER", "CHAMEI O BUTTON CREATE ACCOUNT")
        }

        backLogin(view)

        return view
    }

    private fun backLogin(view: View) {
        view.toolbar_icon_register_user.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun createAccount(view: View) {
        getDataFields(view)
        Log.i("ERROR REGISTER", "CHAMEI O CREATE ACCOUNT")
        viewModel.registerUser(name, email, password)
        viewModel.userLogin.observe(viewLifecycleOwner, {
            when (viewModel.userLogin.value) {
                true -> {
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }
                else -> {
                    Log.i("ERRO NO REGISTRO", "ERRO NO REGISTRO")
                }
            }
        })
    }

    private fun getDataFields(view: View) {
        name = view.ed_name.text.toString()
        email = view.ed_email.text.toString()
        password = view.tf_password.text.toString()
        val passwordConfirm = view.tfc_password.text.toString()

    }

}