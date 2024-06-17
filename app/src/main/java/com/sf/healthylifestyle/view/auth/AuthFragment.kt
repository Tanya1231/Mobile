package com.sf.healthylifestyle.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentAuthBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AuthFragment : Fragment()
/*, HasAndroidInjector */ {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerFragmentViewModel: AuthViewModel

    @Inject
    lateinit var vmFactory: AuthViewModel.Factory

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

        registerFragmentViewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        binding.tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }

        binding.btnSubmit.setOnClickListener {
//            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}