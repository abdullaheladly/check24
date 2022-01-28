package com.abdullah996.check24.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.abdullah996.check24.R
import com.abdullah996.check24.data.model.Product
import com.abdullah996.check24.databinding.FragmentProductOverviewBinding
import com.abdullah996.check24.ui.overview.adpter.ProductOverviewAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductOverview : Fragment(),OnClickListeners {

    private var _binding:FragmentProductOverviewBinding?=null
    private val binding get() = _binding!!
    private lateinit var productOverviewViewModel: ProductOverviewViewModel
    private val productsAdapter by lazy { ProductOverviewAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productOverviewViewModel=ViewModelProvider(requireActivity()).get(ProductOverviewViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProductOverviewBinding.inflate(layoutInflater,container,false)
        setupRecycleView()
        binding.progressBar.visibility=View.VISIBLE
        loadProducts()
        setListeners()
        return binding.root
    }

    private fun setListeners() {

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing=true
            binding.progressBar.visibility=View.VISIBLE
            loadProducts()
        }
        binding.footer.setOnClickListener {
            findNavController().navigate(R.id.action_productOverview_to_webViewFragment)
        }
    }

    private fun setupRecycleView() {
        binding.rvProduct.adapter=productsAdapter
        binding.rvProduct.layoutManager=LinearLayoutManager(requireContext())
    }

    private fun loadProducts() {
        productOverviewViewModel.getAllProducts().observe(viewLifecycleOwner,{
            if (it != null) {
                productsAdapter.saveData(it.products)
                binding.filter1.text=it.filters[0].toString()
                binding.filter2.text=it.filters[1].toString()
                binding.filter3.text=it.filters[2].toString()
                binding.txtTitle.text=it.header.headerTitle
                binding.txtSubTitle.text=it.header.headerDescription
                binding.progressBar.visibility=View.INVISIBLE
                binding.refresh.isRefreshing=false

            }
        })
    }



    private fun makeToast(s:String){
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onProductItemClick(product: Product) {
        val action=ProductOverviewDirections.actionProductOverviewToProductDetails(product)
        findNavController().navigate(action)
    }
}