package com.aymen.cafeyn.ui.base


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aymen.cafeyn.global.helper.DebugLog
import com.aymen.cafeyn.global.helper.Navigation
import com.aymen.cafeyn.ui.data.DialogAction
import com.aymen.cafeyn.ui.dialog.progress.ProgressDialog
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.Lazy
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    @Inject
    protected lateinit var picassoLazy: Lazy<Picasso>

    protected val picasso: Picasso
        get() = picassoLazy.get()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }


    protected fun registerBaseObservers(viewModel: BaseViewModel) {

        registerProgressDialogObserver(viewModel)
        registerShowToastObserver(viewModel)
        registerSnackBarObserver(viewModel)
        registerNavigationObserver(viewModel)

    }

    private fun registerProgressDialogObserver(viewModel: BaseViewModel) {
        viewModel.showProgressBar.observe(this, Observer(::showProgressDialog))
    }


    private fun registerShowToastObserver(viewModel: BaseViewModel) {
        viewModel.showToast.observe(this, Observer(::showToast))
    }

    private fun registerSnackBarObserver(viewModel: BaseViewModel) {
        viewModel.showSnackBar.observe(this) {
            showSnackBar(it.first, it.second)
        }
    }

    private fun registerNavigationObserver(viewModel: BaseViewModel) {
        viewModel.navigation.observe(this, Observer(::navigate))
    }

    fun showProgressDialog(action: DialogAction) {
        progressDialog = if (action == DialogAction.DISMISS) {
            progressDialog?.dismiss()
            null
        } else {
            ProgressDialog(this, action == DialogAction.SHOW)
                .apply { show() }
        }
    }


    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showSnackBar(message: String, action: () -> Unit) {



        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("Refresh", MyRefreshListener(action))
            .show()
    }

    class MyRefreshListener(private val action: () -> Unit) : View.OnClickListener {

        override fun onClick(v: View) {
            action()
        }
    }


    open fun navigate(navigationTo: Navigation) {
        when (navigationTo) {
            is Navigation.Back -> navigateBack(navigationTo.ShouldFinish)
        }

    }

    private fun navigateBack(shouldFinish: Boolean) {
        if (shouldFinish) {
            super.onBackPressed()
            finish()
        } else {
            onBackPressed()
        }
    }


}