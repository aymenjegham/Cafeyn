package com.aymen.cafeyn.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aymen.cafeyn.databinding.FragmentDetailsBinding
import com.aymen.cafeyn.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : BaseFragment() {


    private val viewModel by viewModels<DetailsViewModel>()

    private val args by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding =
            FragmentDetailsBinding.inflate(layoutInflater, container, false)

        bind(binding)

        return binding.root
    }

    private fun bind(binding: FragmentDetailsBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.setPhotoItem(args.photoItem)

        registerDetailsObservers()

    }

    private fun registerDetailsObservers() {
        registerBaseObservers(viewModel)
    }

}