package com.example.master.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    protected lateinit var dataBinding: T

    @LayoutRes
    abstract fun provideLayoutResId(): Int

    abstract fun setupViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        setupDataBinding()
        setupViewModel()
    }

    // Setup
    internal open fun setupDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, provideLayoutResId())
        dataBinding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}
