package com.sf.healthylifestyle.view.basket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sf.healthylifestyle.databinding.FragmentBasketBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BasketFragment : Fragment() {

    private val basketAdapter = BasketAdapter()

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private lateinit var basketViewModel: BasketViewModel

    @Inject
    lateinit var basketViewModelFactory: BasketViewModel.Factory

    override fun onAttach(context: Context) {

        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        basketViewModel =
            ViewModelProvider(this, basketViewModelFactory)[BasketViewModel::class.java]

        binding.rvBasket.adapter = basketAdapter

//        viewLifecycleOwner.lifecycleScope.launch {
//            myDishViewModel.pairHalfesDishes.collect {
//
//                println("Pair rv $it")
//
//                initRV(it)
//            }
//        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

//    private fun initRV(data: Pair<List<ProductResponse>, List<ProductResponse>>) {
//
//        leftHalfAdapter.setData(data.first)
//        rightHalfAdapter.setData(data.second)
//
//        val leftSnapHelper = GravitySnapHelper(
//            Gravity.TOP,
//            true
//        ) { position ->
//            val item = leftHalfAdapter.getItem(position)
//            leftCost = item.price
//            showDescription(
//                Pair(binding.tvLeftDescription, binding.tvLeftCost),
//                item
//            )
//        }
//        val rightSnapHelper = GravitySnapHelper(
//            Gravity.TOP,
//            true
//        ) { position ->
//            val item = rightHalfAdapter.getItem(position)
//            rightCost = item.price
//            showDescription(
//                Pair(binding.tvRightDescription, binding.tvRightCost),
//                item
//            )
//        }
//
//        leftSnapHelper.attachToRecyclerView(binding.rvLeftHalf)
//        rightSnapHelper.attachToRecyclerView(binding.rvRightHalf)
//    }

//    @SuppressLint("SetTextI18n")
//    private fun showDescription(
//        pairView: Pair<TextView, TextView>,
//        productResponse: ProductResponse
//    ) {
//        pairView.first.text = productResponse.title
//        pairView.first.background =
//            ContextCompat.getDrawable(requireContext(), R.drawable.background_desc)
//        pairView.second.text = productResponse.price.toString() + " ₽"
//        pairView.second.background =
//            ContextCompat.getDrawable(requireContext(), R.drawable.background_desc)
//
//        binding.tvCost.text = ((leftCost ?: 0) + (rightCost ?: 0)).toString() + " ₽"
//    }
}