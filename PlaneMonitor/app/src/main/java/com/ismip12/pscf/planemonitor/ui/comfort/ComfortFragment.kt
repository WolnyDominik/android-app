package com.ismip12.pscf.planemonitor.ui.comfort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ismip12.pscf.planemonitor.R

class ComfortFragment : Fragment() {

    private lateinit var comfortViewModel: ComfortViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        comfortViewModel =
                ViewModelProvider(this).get(ComfortViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_comfort, container, false)
        val textView: TextView = root.findViewById(R.id.text_comfort)
        comfortViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
