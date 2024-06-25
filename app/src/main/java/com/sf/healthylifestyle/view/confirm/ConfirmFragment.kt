package com.sf.healthylifestyle.view.confirm

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
import com.sf.healthylifestyle.databinding.FragmentConfirmBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
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

        viewLifecycleOwner.lifecycleScope.launch {
            confirmFragmentViewModel.isEntry.collect {
                if (it) {
//                    findNavController().navigate(R.id.action_confirmFragment_to_homeFragment)
                }
                else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.submit.setOnClickListener{
            confirmFragmentViewModel.confirm(binding.code.text.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}