package com.sf.healthylifestyle.view.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentOnboardingThreeBinding
import dagger.android.support.AndroidSupportInjection

class OnboardingThreeFragment : Fragment()
{

    private var _binding: FragmentOnboardingThreeBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentOnboardingThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener { findNavController().navigate(R.id.action_onboardingThreeFragment_to_onboardingFourFragment) }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}