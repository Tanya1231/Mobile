package com.sf.healthylifestyle.view.basket

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.FragmentBasketBinding
import com.sf.healthylifestyle.domain.models.Dish
import dagger.android.support.AndroidSupportInjection
import ed.maevski.minideviantart.view.decoration.ItemDecoration
import kotlinx.coroutines.launch
import javax.inject.Inject

class BasketFragment : Fragment() {

    private val basketAdapter = BasketAdapter(
        {id ->
        basketViewModel.delDish(id)
    },
    {id ->
        val bundle = Bundle()
        bundle.putInt("dishId", id)
        findNavController().navigate(R.id.action_basketFragment_to_descriptionDishFragment, bundle)
    })

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

        println("onViewCreated BasketFragment")

        basketViewModel =
            ViewModelProvider(this, basketViewModelFactory)[BasketViewModel::class.java]

        binding.rvBasket.addItemDecoration(ItemDecoration(32, 0, 0, 0))
        binding.rvBasket.adapter = basketAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            basketViewModel.dishes.collect {

                println("dishes collect $it")
                initRV(it)
                sum(it)
            }
        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            basketViewModel.dish.collect {
//
//                println("dish collect $it")
//                findNavController().navigate(R.id.action_basketFragment_to_descriptionDishFragment)
//            }
//        }

        binding.btnArrange.setOnClickListener {
            basketAdapter.setData(listOf())
            basketViewModel.delBasket()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRV(data: List<Dish>) {

        basketAdapter.setData(data)

    }

    @SuppressLint("SetTextI18n")
    private fun sum(list: List<Dish>){
        val totalPrice = list.sumOf { it.quantity * it.price }
        binding.tvSum.text = totalPrice.toString()  + " ₽"
    }
}