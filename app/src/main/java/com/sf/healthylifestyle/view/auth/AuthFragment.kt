package com.sf.healthylifestyle.view.auth

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentAuthBinding
import com.sf.healthylifestyle.databinding.FragmentHomeBinding
import com.sf.healthylifestyle.view.home.HomeViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AuthFragment : Fragment()
    /*, HasAndroidInjector */
{

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
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}