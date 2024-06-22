package com.sf.healthylifestyle.view.mydish

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.google.android.material.snackbar.Snackbar
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.databinding.FragmentMydishBinding
import dagger.android.support.AndroidSupportInjection
import ed.maevski.minideviantart.view.decoration.TopSpacingItemDecoration
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyDishFragment : Fragment() {

    private val leftHalfAdapter = LeftHalfAdapter()
    private val rightHalfAdapter = RightHalfAdapter()

    private var _binding: FragmentMydishBinding? = null
    private val binding get() = _binding!!

    private lateinit var myDishViewModel: MyDishViewModel

    @Inject
    lateinit var myDishViewModelFactory: MyDishViewModel.Factory

    override fun onAttach(context: Context) {

        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMydishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        myDishViewModel =
            ViewModelProvider(this, myDishViewModelFactory)[MyDishViewModel::class.java]

        with(binding) {
            binding.rvLeftHalf.addItemDecoration(TopSpacingItemDecoration(0, 16, 0, 0))
            binding.rvRightHalf.addItemDecoration(TopSpacingItemDecoration(16, 0, 0, 0))

            rvLeftHalf.adapter = leftHalfAdapter
            rvRightHalf.adapter = rightHalfAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            myDishViewModel.pairHalfesDishes.collect {

                println("Pair rv $it")

                initRV(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRV(data: Pair<List<ProductResponse>, List<ProductResponse>>) {

        leftHalfAdapter.setData(data.first)
        rightHalfAdapter.setData(data.second)

        val leftSnapHelper = GravitySnapHelper(
            Gravity.TOP,
            true
        ) { position ->
            showDescription(leftHalfAdapter.getItem(position))
        }
        val rightSnapHelper = GravitySnapHelper(
            Gravity.TOP,
            true
        ) { position ->
            showDescription(rightHalfAdapter.getItem(position))
        }

        leftSnapHelper.attachToRecyclerView(binding.rvLeftHalf)
        rightSnapHelper.attachToRecyclerView(binding.rvRightHalf)
    }

    private fun showDescription(productResponse: ProductResponse) {
//        binding.tvLeftDescription.text = productResponse.title

        Snackbar.make(
            binding.root,
            productResponse.title,
            Snackbar.LENGTH_LONG
        ).show()
    }
}