package com.example.master.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<S : Any> : ViewModel() {
    protected val bag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }
}