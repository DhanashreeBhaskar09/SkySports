package com.example.master.ui.base

import android.os.Bundle
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
