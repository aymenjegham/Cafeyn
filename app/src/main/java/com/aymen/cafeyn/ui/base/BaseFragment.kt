package com.aymen.cafeyn.ui.base


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aymen.cafeyn.global.helper.Navigation
import com.squareup.picasso.Picasso
import dagger.Lazy
import javax.inject.Inject

abstract class BaseFragment : Fragment() {


    @Inject
    protected lateinit var picassoLazy: Lazy<Picasso>

    protected val picasso: Picasso
        get() = picassoLazy.get()


    protected fun registerBaseObservers(viewModel: BaseViewModel) {
        registerProgressDialogObserver(viewModel)
        registerShowToastObserver(viewModel)
        registerSnackBarObserver(viewModel)
        registerNavigationObserver(viewModel)
    }


    private fun registerProgressDialogObserver(viewModel: BaseViewModel) {
        (activity as? BaseActivity)?.apply {
            viewModel.showProgressBar.observe(viewLifecycleOwner, Observer(::showProgressDialog))
        }
    }

    private fun registerShowToastObserver(viewModel: BaseViewModel) {
        (activity as? BaseActivity)?.apply {
            viewModel.showToast.observe(viewLifecycleOwner, Observer(::showToast))
        }

    }

    private fun registerSnackBarObserver(viewModel: BaseViewModel) {
        (activity as? BaseActivity)?.apply {
            viewModel.showSnackBar.observe(viewLifecycleOwner) {
                showSnackBar(it.first, it.second)
            }
        }
    }

    private fun registerNavigationObserver(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer (::navigate))
    }

    protected open fun navigate(navigationTo: Navigation) {
        when (navigationTo) {
            is Navigation.Back -> findNavController().navigateUp()
            else -> (activity as? BaseActivity)?.navigate(navigationTo)

        }
    }

}
