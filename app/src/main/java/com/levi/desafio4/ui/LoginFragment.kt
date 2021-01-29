package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levi.desafio4.R
import com.levi.desafio4.service.firebase
import com.levi.desafio4.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.login_body.view.*

class LoginFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        registerUser(view)
        loginUser(view)

        return view
    }

    private fun loginUser(view: View) {
        view.btn_login.setOnClickListener {
            getDataFields(view)
            viewModel.loginUser(email, password)
            viewModel.userLogin.observe(viewLifecycleOwner, {
                when (viewModel.userLogin.value) {
                    true -> {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    else -> {
                        Toast.makeText(context, "Erro ao realizar login! Verifique seu E-mail ou Senha!", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }
    }

    private fun registerUser(view: View) {
        view.tv_create_account.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun getDataFields(view: View) {
        email = view.tf_email.text.toString()
        password = view.ed_password.text.toString()
    }
}