package com.sf.healthylifestyle.view.register

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
import com.sf.healthylifestyle.databinding.FragmentRegisterBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerFragmentViewModel: RegisterViewModel

    @Inject
    lateinit var vmFactory: RegisterViewModel.Factory

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

        registerFragmentViewModel =
            ViewModelProvider(this, vmFactory)[RegisterViewModel::class.java]

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_authFragment)
        }

        binding.enter.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}