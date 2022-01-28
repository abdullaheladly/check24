package com.abdullah996.check24.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.abdullah996.check24.R
import com.abdullah996.check24.data.database.Products
import com.abdullah996.check24.data.model.Product
import com.abdullah996.check24.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetails : Fragment() {

    private var _binding:FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ProductDetailsArgs>()
    private lateinit var product:Product
    private lateinit var productsDetailsViewModel:ProductsDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsDetailsViewModel=ViewModelProvider(requireActivity()).get(ProductsDetailsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        product=args.product
        setViews()
        return binding.root
    }

    private fun setViews() {
        binding.productImageDetail.load(product.imageURL){
            crossfade(600)
        }
        binding.txtProductNameDetail.text=product.name.toString()
        binding.txtProductDateDetail.text=product.releaseDate.toString()
        binding.txtProductDescDetail.text=product.description.toString()
        binding.txtProductPriceNumDetail.text=product.price.value.toString()+" "+product.price.currency.toString()
        binding.productRatingDetail.numStars=5
        binding.productRatingDetail.rating=product.rating.toFloat()
        binding.longDescriptionDetail.text=product.longDescription
        binding.addToFavouriteDetial.setOnClickListener {
            //add to favourite

            productsDetailsViewModel.addToFavourite(product)
        }
        binding.footerDetail.setOnClickListener {
            findNavController().navigate(R.id.action_productDetails_to_webViewFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}