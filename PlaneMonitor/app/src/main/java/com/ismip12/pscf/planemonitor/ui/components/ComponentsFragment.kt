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
import org.w3c.dom.Text

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
        var flDoor: TextView = root.findViewById(R.id.flDoor)
        var frDoor: TextView = root.findViewById(R.id.frDoor)
        var rlDoor: TextView = root.findViewById(R.id.rlDoor)
        var rrDoor: TextView = root.findViewById(R.id.rrDoor)
        var gears: TextView = root.findViewById(R.id.gears)
        var redHydro: TextView = root.findViewById(R.id.redHydro)
        var blueHydro: TextView = root.findViewById(R.id.blueHydro)
        var yellowHydro: TextView = root.findViewById(R.id.yellowHydro)
        componentsViewModel.flDoor.observe(viewLifecycleOwner, Observer {
            flDoor.text = it
        })
        componentsViewModel.frDoor.observe(viewLifecycleOwner, Observer {
            frDoor.text = it
        })
        componentsViewModel.rlDoor.observe(viewLifecycleOwner, Observer {
            rlDoor.text = it
        })
        componentsViewModel.rrDoor.observe(viewLifecycleOwner, Observer {
            rrDoor.text = it
        })
        componentsViewModel.gears.observe(viewLifecycleOwner, Observer {
            gears.text = it
        })
        componentsViewModel.redHydro.observe(viewLifecycleOwner, Observer {
            redHydro.text = it
        })
        componentsViewModel.blueHydro.observe(viewLifecycleOwner, Observer {
            blueHydro.text = it
        })
        componentsViewModel.yellowHydro.observe(viewLifecycleOwner, Observer {
            yellowHydro.text = it
        })
        return root
    }
}
