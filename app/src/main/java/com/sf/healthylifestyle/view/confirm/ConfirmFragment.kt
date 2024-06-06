package com.sf.healthylifestyle.view.confirm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentConfirmBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ConfirmFragment : Fragment() {

    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!

    private lateinit var confirmFragmentViewModel: ConfirmViewModel

    @Inject
    lateinit var vmFactory: ConfirmViewModel.Factory

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
        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        confirmFragmentViewModel =
            ViewModelProvider(this, vmFactory)[ConfirmViewModel::class.java]

        binding.submit.setOnClickListener{
            findNavController().navigate(R.id.action_confirmFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}