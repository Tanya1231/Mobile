package com.sf.healthylifestyle.view.auth

import android.content.Context
import androidx.fragment.app.viewModels
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
import com.sf.healthylifestyle.databinding.FragmentHomeBinding
import com.sf.healthylifestyle.view.home.HomeViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
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

        authFragmentViewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

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

        binding.submit.setOnClickListener {
            /*** На время тестов, чтобы не писать постоянно e-mail*/
            if (binding.phone.text.toString().isNotEmpty()) {
                /*** На время тестов, чтобы не писать постоянно e-mail*/
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
        }


        binding.tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^([A-Za-z0-9._%+-]+)@([A-Za-z0-9-]+)\\.([A-Za-z]{2,})$")
        return emailRegex.matches(email)
    }
}