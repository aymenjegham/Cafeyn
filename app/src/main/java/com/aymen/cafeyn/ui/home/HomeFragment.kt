package com.aymen.cafeyn.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.databinding.FragmentHomeBinding
import com.aymen.cafeyn.global.helper.Navigation
import com.aymen.cafeyn.ui.base.BaseFragment
import com.aymen.cafeyn.ui.home.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var photoAdapter: PhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding =
            FragmentHomeBinding.inflate(layoutInflater, container, false)

        bind(binding)

        registerHomeObservers(binding)

        return binding.root
    }


    private fun bind(binding: FragmentHomeBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        photoAdapter = PhotoAdapter(
            viewModel.navigate(),
            requireContext(),
            picasso
        )
        binding.adapter = photoAdapter
    }


    private fun registerHomeObservers(binding: FragmentHomeBinding) {
        registerBaseObservers(viewModel)
        registerSubmitAdapter()
        setupRefresh(binding)

    }

    private fun registerSubmitAdapter() {
        viewModel.resultPhotoList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        photoAdapter.submitList(list)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRefresh(binding: FragmentHomeBinding) {
        binding.refresh.setOnRefreshListener {
            viewModel.fetchPhotos()
            binding.refresh.isRefreshing = false
        }
    }

    private fun showError(msg: String) {
        viewModel.showSnackBar(msg) { viewModel.fetchPhotos() }
    }

    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {

            is Navigation.NavigationDetails -> findNavController().navigate(
                HomeFragmentDirections.toDetails(navigationTo.photoItem)
            )

            else -> null
        }
    }


}