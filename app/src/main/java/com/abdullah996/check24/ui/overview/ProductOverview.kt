package com.abdullah996.check24.ui.overview

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var products: MutableList<Product>
    private  var isConnected:Boolean=false


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
        setListeners()
        isConnected = hasInternetConnection()
        if (!isConnected){
            binding.contentWithData.visibility=View.INVISIBLE
            makeToast("no internet swipe to try again")
            binding.reload.visibility=View.VISIBLE
        }
        else{
            binding.reload.visibility=View.INVISIBLE
            binding.contentWithData.visibility=View.VISIBLE
            binding.contentWithData.visibility=View.VISIBLE
            binding.progressBar.visibility=View.VISIBLE
            loadProducts()


        }
        return binding.root
    }

    private fun setListeners() {
        binding.reload.setOnClickListener {
            isConnected=hasInternetConnection()
            if (!isConnected){
                binding.contentWithData.visibility=View.INVISIBLE
                makeToast("no internet swipe to try again")
            }
            else{
                binding.reload.visibility=View.INVISIBLE
                binding.contentWithData.visibility=View.VISIBLE
                binding.refresh.isRefreshing=true
                binding.progressBar.visibility=View.VISIBLE
                loadProducts()

            }
        }


        binding.refresh.setOnRefreshListener {
            isConnected=hasInternetConnection()
            if (!isConnected){
                binding.contentWithData.visibility=View.INVISIBLE
                makeToast("no internet swipe to try again")
            }
            else{
                binding.reload.visibility=View.INVISIBLE
                binding.contentWithData.visibility=View.VISIBLE
                binding.refresh.isRefreshing=true
                binding.progressBar.visibility=View.VISIBLE
                loadProducts()

            }

        }
        binding.footer.setOnClickListener {
            findNavController().navigate(R.id.action_productOverview_to_webViewFragment)
        }
        binding.filter1.setOnClickListener {

            binding.progressBar.visibility=View.VISIBLE
            loadProducts()
        }
        binding.filter2.setOnClickListener {
            binding.progressBar.visibility=View.VISIBLE
            loadAvailableProducts()
        }
    }

    private fun loadAvailableProducts() {
        productOverviewViewModel.getAllProducts().observe(viewLifecycleOwner,{
           for (item in it.products){
               if (item.available){
                   products.add(item)
               }
           }
            productsAdapter.saveData(products)
            binding.progressBar.visibility=View.INVISIBLE
        })
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

    private fun hasInternetConnection():Boolean{
        val connectivityManager=requireActivity().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork=connectivityManager.activeNetwork?:return false
        val capabilities=connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return  when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}