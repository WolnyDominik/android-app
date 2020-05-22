package com.ismip12.pscf.planemonitor.ui.engines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ismip12.pscf.planemonitor.R

class EnginesFragment : Fragment() {

    private lateinit var enginesViewModel: EnginesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        enginesViewModel =
                ViewModelProvider(this).get(EnginesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_engines, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        enginesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
