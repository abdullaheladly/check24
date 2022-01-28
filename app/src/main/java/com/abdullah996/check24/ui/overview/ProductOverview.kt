package com.abdullah996.check24.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdullah996.check24.R
import com.abdullah996.check24.databinding.FragmentProductOverviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductOverview : Fragment() {

    private var _binding:FragmentProductOverviewBinding?=null
    private val binding get() = _binding!!
    private lateinit var productOverviewViewModel: ProductOverviewViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productOverviewViewModel=ViewModelProvider(requireActivity()).get(ProductOverviewViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProductOverviewBinding.inflate(layoutInflater,container,false)
        loadProducts()
        return binding.root
    }

    private fun loadProducts() {
        productOverviewViewModel.getAllProducts().observe(viewLifecycleOwner,{
            makeToast(it.toString())
        })
    }

    private fun makeToast(s:String){
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}