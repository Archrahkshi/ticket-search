package com.archrahkshi.ticketsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _views: Binding? = null
    private val views get() = _views!!

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _views = inflateBinding(inflater, container)
        return views.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
    }
}
