package com.sf.healthylifestyle.view.profile

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentBookBinding
import com.sf.healthylifestyle.databinding.FragmentProfileTwoBinding
import com.sf.healthylifestyle.view.book.BookViewModel
import dagger.android.support.AndroidSupportInjection

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileTwoBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.toolbarClose.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_homeFragment) }

        binding.tollbarBack.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_homeFragment) }

    }
}