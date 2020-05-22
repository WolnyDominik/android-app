package com.ismip12.pscf.planemonitor.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ismip12.pscf.planemonitor.R

class ComponentsFragment : Fragment() {

    private lateinit var componentsViewModel: ComponentsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        componentsViewModel =
                ViewModelProvider(this).get(ComponentsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_components, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        componentsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
