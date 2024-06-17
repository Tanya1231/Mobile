package com.sf.healthylifestyle.view.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentAuthBinding
import com.sf.healthylifestyle.utils.uiextensions.hide
import com.sf.healthylifestyle.utils.uiextensions.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment : Fragment()
/*, HasAndroidInjector */ {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var authFragmentViewModel: AuthViewModel

    @Inject
    lateinit var vmFactory: AuthViewModel.Factory

//    @Inject
//    lateinit var androidInjector: DispatchingAndroidInjector<Any>
//    override fun androidInjector(): AndroidInjector<Any> {
//        return androidInjector
//    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.btnPhone.isSelected = true

        authFragmentViewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authFragmentViewModel.authState outside")
            authFragmentViewModel.authState.collect {
                println("AuthFragment: запуск authFragmentViewModel.authState = $it inside")
                renderData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authFragmentViewModel.isEntry outside")
            authFragmentViewModel.isEntry.collect {
                println("AuthFragment: запуск authFragmentViewModel.isEntry inside")
                if (it) {
                    findNavController().navigate(R.id.action_authFragment_to_confirmFragment)
                } else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка сервера.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        /*        binding.submit.setOnClickListener {
                    */
        /*** На время тестов, чтобы не писать постоянно e-mail*//*
            if (binding.phone.text.toString().isNotEmpty()) {
                */
        /*** На время тестов, чтобы не писать постоянно e-mail*//*
                if (!isEmailValid(binding.phone.text.toString())) {
                    Snackbar.make(
                        binding.root,
                        "E-mail содержит не допустимые символы. Или не верный формат записи.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }

            authFragmentViewModel.login(binding.phone.text.toString())
        }*/

        initBtnListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^([A-Za-z0-9._%+-]+)@([A-Za-z0-9-]+)\\.([A-Za-z]{2,})$")
        return emailRegex.matches(email)
    }

    private fun renderData(authState: AuthState) {
        when (authState) {
            is AuthState.Loading -> {
            }

            is AuthState.Reg<*> -> {

                binding.btnSubmit.text = getString(R.string.fragment_auth_btn_submit_get_code)

                when (AuthState.PHONE_OR_EMAIL) {
                    "phone" -> {
                        with(binding) {
                            tilEmail.hide()
                            etEmail.hide()
                            tilPhone.show()
                            etPhone.show()
                        }
                    }

                    "email" -> {
                        with(binding) {
                            tilPhone.hide()
                            etPhone.hide()
                            tilEmail.show()
                            etEmail.show()
                        }
                    }

                    else -> {
                        TODO("Invalid ...")
                    }
                }
            }

            is AuthState.Confirm<*> -> {

                binding.btnSubmit.text = getString(R.string.fragment_auth_btn_submit_confirm)

                when (AuthState.PHONE_OR_EMAIL) {
                    "phone" -> {
                        with(binding) {
                            tilName.hide()
                            etName.hide()
                            tilEmail.hide()
                            etEmail.hide()
                            tilPhone.hide()
                            etPhone.hide()
                            btnPhone.hide()
                            btnEmail.hide()
                            tvConfirmEmail.hide()
                            etConfirmEmail.hide()
                            tvConfirmPhone.show()
                            tilConfirmPhone.show()
                            etConfirmPhone.show()
                        }
                    }

                    "email" -> {
                        with(binding) {
                            tilName.hide()
                            etName.hide()
                            tilEmail.hide()
                            etEmail.hide()
                            tilPhone.hide()
                            etPhone.hide()
                            btnPhone.hide()
                            btnEmail.hide()
                            tvConfirmPhone.hide()
                            etConfirmPhone.hide()
                            tvConfirmEmail.show()
                            tilConfirmEmail.show()
                            etConfirmEmail.show()
                        }
                    }

                    else -> {
                        TODO("Invalid ...")
                    }
                }
            }

            is AuthState.Done<*> -> {
                binding.btnSubmit.text = getString(R.string.fragment_auth_btn_submit_to_main)
                with(binding) {
                    tvReg.hide()
                    tilName.hide()
                    etName.hide()
                    tilEmail.hide()
                    etEmail.hide()
                    tilPhone.hide()
                    etPhone.hide()
                    btnPhone.hide()
                    btnEmail.hide()
                    tvConfirmEmail.hide()
                    etConfirmEmail.hide()
                    tvConfirmPhone.hide()
                    tilConfirmPhone.hide()
                    etConfirmPhone.hide()
                    tvConfirmEmail.hide()
                    tilConfirmEmail.hide()
                    etConfirmEmail.hide()
                    tvRegDone.show()
                    imgRegDone.show()
                }
            }

            is AuthState.Auth<*> -> TODO()
            is AuthState.Error -> TODO()
        }
    }

    private fun initBtnListeners() = with(binding) {
        btnPhone.setOnClickListener {
            btnPhone.isSelected = true
            btnEmail.isSelected = false
            AuthState.PHONE_OR_EMAIL = "phone"
            authFragmentViewModel.switch()
        }

        btnEmail.setOnClickListener {
            btnEmail.isSelected = true
            btnPhone.isSelected = false
            AuthState.PHONE_OR_EMAIL = "email"
            authFragmentViewModel.switch()
        }

        btnSubmit.setOnClickListener {
//            authFragmentViewModel.login("")
            when (authFragmentViewModel.authState.value) {
                is AuthState.Reg<*> -> {
                    when (AuthState.PHONE_OR_EMAIL) {
                        "phone" -> {
                            /*** На время тестов, чтобы не писать постоянно e-mail*/
                            if (binding.etPhone.text.toString().isNotEmpty()) {

                                /*** На время тестов, чтобы не писать постоянно e-mail*/
                                if (!isEmailValid(binding.etPhone.text.toString())) {
                                    Snackbar.make(
                                        binding.root,
                                        "E-mail содержит не допустимые символы. Или не верный формат записи.",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                        }

                        "email" -> {
                            /*** На время тестов, чтобы не писать постоянно e-mail*/
                            if (binding.etPhone.text.toString().isNotEmpty()) {

                                /*** На время тестов, чтобы не писать постоянно e-mail*/
                                if (!isEmailValid(binding.etPhone.text.toString())) {
                                    Snackbar.make(
                                        binding.root,
                                        "E-mail содержит не допустимые символы. Или не верный формат записи.",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                        }

                        else -> {
                            TODO("Invalid ...")
                        }
                    }



                    authFragmentViewModel.reg()
                }

                is AuthState.Confirm<*> -> {
                    authFragmentViewModel.confirm()
                }

                is AuthState.Done<*> -> {
                    findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                }

                is AuthState.Auth<*> -> TODO()
                is AuthState.Error -> TODO()
                is AuthState.Loading -> TODO()
            }

        }
    }
}