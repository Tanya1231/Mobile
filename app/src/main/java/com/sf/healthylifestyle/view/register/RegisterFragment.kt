package com.sf.healthylifestyle.view.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentRegisterBinding
import com.sf.healthylifestyle.utils.uiextensions.hide
import com.sf.healthylifestyle.utils.uiextensions.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerFragmentViewModel: RegisterViewModel

    @Inject
    lateinit var vmFactory: RegisterViewModel.Factory

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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.btnPhone.isSelected = true

        registerFragmentViewModel =
            ViewModelProvider(this, vmFactory)[RegisterViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authFragmentViewModel.authState outside")
            registerFragmentViewModel.regState.collect {
                println("AuthFragment: запуск authFragmentViewModel.authState = $it inside")
                renderData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authFragmentViewModel.isEntry outside")
            registerFragmentViewModel.isEntry.collect {
                println("AuthFragment: запуск authFragmentViewModel.isEntry inside")
                if (it) {
//                    findNavController().navigate(R.id.action_authFragment_to_confirmFragment)
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

    private fun renderData(regState: RegState) {
        when (regState) {
            is RegState.Loading -> {
            }

            is RegState.Reg<*> -> {

                binding.btnSubmit.text = getString(R.string.btn_submit_get_code)

                when (RegState.PHONE_OR_EMAIL) {
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
                Snackbar.make(
                    binding.root,
                    "${registerFragmentViewModel.regState.value}",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            is RegState.Confirm<*> -> {

                binding.btnSubmit.text = getString(R.string.btn_submit_confirm)

                when (RegState.PHONE_OR_EMAIL) {
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
                Snackbar.make(
                    binding.root,
                    "${registerFragmentViewModel.regState.value}",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            is RegState.Done<*> -> {
                when(regState.data) {
                    is Boolean -> {
                        if (regState.data) {
                            binding.btnSubmit.text = getString(R.string.btn_submit_to_main)
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
                                chbAgree.hide()
                                tvAgree.hide()
                                chbAd.hide()
                                tvAd.hide()
                                tvRegDone.show()
                                imgRegDone.show()
                            }
                            Snackbar.make(
                                binding.root,
                                "${registerFragmentViewModel.regState.value}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Вы ввели неверный код",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                    else -> {

                    }
                }

            }

            is RegState.Auth<*> -> TODO()
            is RegState.Error -> TODO()
        }
    }

    private fun initBtnListeners() = with(binding) {
        actions.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_authFragment)
        }

        btnPhone.setOnClickListener {
            btnPhone.isSelected = true
            btnEmail.isSelected = false
            RegState.PHONE_OR_EMAIL = "phone"
            registerFragmentViewModel.switch()
        }

        btnEmail.setOnClickListener {
            btnEmail.isSelected = true
            btnPhone.isSelected = false
            RegState.PHONE_OR_EMAIL = "email"
            registerFragmentViewModel.switch()
        }

        btnSubmit.setOnClickListener {
            when (registerFragmentViewModel.regState.value) {
                is RegState.Reg<*> -> {
                    when (RegState.PHONE_OR_EMAIL) {
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



                    registerFragmentViewModel.reg()
                }

                is RegState.Confirm<*> -> {
                    registerFragmentViewModel.confirm()
                }

                is RegState.Done<*> -> {
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }

                is RegState.Auth<*> -> TODO()
                is RegState.Error -> TODO()
                is RegState.Loading -> TODO()
            }

        }
    }


}