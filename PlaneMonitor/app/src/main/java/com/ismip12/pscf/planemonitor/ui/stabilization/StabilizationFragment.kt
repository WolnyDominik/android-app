package com.ismip12.pscf.planemonitor.ui.stabilization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ismip12.pscf.planemonitor.R

class StabilizationFragment : Fragment() {

    private lateinit var stabilizationViewModel: StabilizationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        stabilizationViewModel =
                ViewModelProvider(this).get(StabilizationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_stabilization, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        stabilizationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
