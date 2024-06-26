package com.sf.healthylifestyle.view.cart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.sf.healthylifestyle.databinding.FragmentDescriptionDishBinding
import com.sf.healthylifestyle.utils.transformation.RotateTransformation
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class DescriptionDishFragment : Fragment() {

    private var _binding: FragmentDescriptionDishBinding? = null
    private val binding get() = _binding!!

    private lateinit var descriptionDishViewModel: DescriptionDishViewModel

    @Inject
    lateinit var descriptionDishViewModelFactory: DescriptionDishViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDescriptionDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val dishId = arguments?.getInt("dishId")

        descriptionDishViewModel =
            ViewModelProvider(this, descriptionDishViewModelFactory)[DescriptionDishViewModel::class.java]

        if (dishId != null) descriptionDishViewModel.getDish(dishId)

//        binding.btnProfile.setOnClickListener { findNavController().navigate(R.id.action_catalogueFragment_to_profileFragment) }

        viewLifecycleOwner.lifecycleScope.launch {
            descriptionDishViewModel.dish.collect {

                println("dish collect $it")

                binding.tvCalories.text = it.calories.toString() + " ккал"
                binding.tvWeight.text = it.weight.toString() + " г"
                binding.tvCost.text = it.price.toString() + " ₽"
                binding.tvCost.isVisible  = true
                binding.tvDescription.text = it.description

                Glide.with(binding.cartRoot)
                    .load(it.image_extra)
                    .centerInside()
                    .transform(RotateTransformation(90f))
                    .into(binding.imgDish)

            }
        }

        binding.btnBack.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
    }
}