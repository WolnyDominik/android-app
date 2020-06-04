package com.ismip12.pscf.planemonitor.ui.components

import android.graphics.Color
import android.graphics.Color.GRAY
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ismip12.pscf.planemonitor.R
import com.ismip12.pscf.planemonitor.R.*
import com.ismip12.pscf.planemonitor.data_model.mech_parts.Alert
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertCode
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertLevel
import kotlinx.android.synthetic.main.fragment_components.*
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
        val root = inflater.inflate(layout.fragment_components, container, false)
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
        componentsViewModel.alertDoor.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                manageAlerts(it)
            }
        })
        componentsViewModel.noConnection.observe(viewLifecycleOwner, Observer {
            if(it==true){
                Toast.makeText(context,"Brak połączenia z czujnikami",Toast.LENGTH_SHORT).show()
            }
        })


        return root
    }

    fun manageAlerts(alerts: List<Alert>) {
        val red = ContextCompat.getColor(requireContext(), R.color.red)
        val orange = ContextCompat.getColor(requireContext(), R.color.orange)
        reset()
        for (al in alerts) {
            if (al.level.equals((AlertLevel.WARNING.text))) {//WARNING
                if (al.code.equals(AlertCode.ICE.text)) { //LOD
                    iceLabel.setTextColor(orange)
                    iceLabel.visibility = View.VISIBLE
                } else if (al.code.equals(AlertCode.LANDING_GEAR.text)) { //KOLA
                    gears.setTextColor(orange)
                    gears.tooltipText = "Podwozie jest wysunięte"
                } else if (al.code.equals(AlertCode.DOOR.text)) { //DRZWI
                    if (al.sensorId == 0) { //fl
                        flDoor.setTextColor(orange)
                        flDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 1) { //fr
                        frDoor.setTextColor(orange)
                        frDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 2) { //rl
                        rlDoor.setTextColor(orange)
                        rlDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 3) { //rr
                        rrDoor.setTextColor(orange)
                        rrDoor.tooltipText = "Drzwi są otwarte"
                    }
                } else if (al.code.equals(AlertCode.ECAM_HYD_HIGH.text)) { //high pressure
                    if (al.sensorId == 0) { //red
                        redHydro.setTextColor(orange)
                        redHydro.tooltipText = "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    } else if (al.sensorId == 1) { //blue
                        blueHydro.setTextColor(orange)
                        blueHydro.tooltipText = "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    } else if (al.sensorId == 2) { //yellow
                        yellowHydro.setTextColor(orange)
                        yellowHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    }
                } else if (al.code.equals(AlertCode.ECAM_HYD_HIGH.text)) { //low pressure
                    if (al.sensorId == 0) { //red
                        redHydro.setTextColor(orange)
                        redHydro.tooltipText = "Ciśnienie w układzie hydraulicznym jest za niskie"
                    } else if (al.sensorId == 1) { //blue
                        blueHydro.setTextColor(orange)
                        blueHydro.tooltipText = "Ciśnienie w układzie hydraulicznym jest za niskie"
                    } else if (al.sensorId == 2) { //yellow
                        yellowHydro.setTextColor(orange)
                        blueHydro.tooltipText = "Ciśnienie w układzie hydraulicznym jest za niskie"
                    }
                } else if(al.code.equals(AlertCode.NETWORK_ERROR.text)){
                    Toast.makeText(context,"Brak połączenia z siecią",Toast.LENGTH_SHORT).show()
                }
            } else if (al.level.equals(AlertLevel.DANGER.text)) {
                if (al.code.equals(AlertCode.ICE.text)) {
                    iceLabel.setTextColor(red)
                    iceLabel.visibility = View.VISIBLE
                } else if (al.code.equals(AlertCode.LANDING_GEAR.text)) { //KOLA
                    gears.setTextColor(red)
                    gears.tooltipText = "Podwozie jest wysunięte"
                } else if (al.code.equals(AlertCode.DOOR.text)) { //DRZWI
                    if (al.sensorId == 0) { //fl
                        flDoor.setTextColor(red)
                        flDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 1) { //fr
                        frDoor.setTextColor(red)
                        frDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 2) { //rl
                        rlDoor.setTextColor(red)
                        rlDoor.tooltipText = "Drzwi są otwarte"
                    } else if (al.sensorId == 3) { //rr
                        rrDoor.setTextColor(red)
                        rrDoor.tooltipText = "Drzwi są otwarte"
                    }
                } else if (al.code.equals(AlertCode.ECAM_HYD_HIGH.text)) { //high pressure
                    if (al.sensorId == 0) { //red
                        redHydro.setTextColor(red)
                        redHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    } else if (al.sensorId == 1) { //blue
                        blueHydro.setTextColor(red)
                        blueHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    } else if (al.sensorId == 2) { //yellow
                        yellowHydro.setTextColor(red)
                        yellowHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za wysokie"
                    }
                } else if (al.code.equals(AlertCode.ECAM_HYD_HIGH.text)) { //low pressure
                    if (al.sensorId == 0) { //red
                        redHydro.setTextColor(red)
                        redHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za niskie"
                    } else if (al.sensorId == 1) { //blue
                        blueHydro.setTextColor(red)
                        blueHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za niskie"
                    } else if (al.sensorId == 2) { //yellow
                        yellowHydro.setTextColor(red)
                        blueHydro.tooltipText =
                            "Ciśnienie w układzie hydraulicznym jest za niskie"
                    }
                } else if(al.code.equals(AlertCode.NETWORK_ERROR.text)){
                Toast.makeText(context,"Brak połączenia z siecią",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun reset() {
        val black = ContextCompat.getColor(requireContext(), R.color.black)
        iceLabel.visibility = View.INVISIBLE
        flDoor.setTextColor(black)
        frDoor.setTextColor(black)
        rlDoor.setTextColor(black)
        rrDoor.setTextColor(black)
        redHydro.setTextColor(black)
        blueHydro.setTextColor(black)
        yellowHydro.setTextColor(black)
        gears.setTextColor(black)
        flDoor.tooltipText = "";
        frDoor.tooltipText = "";
        rlDoor.tooltipText = "";
        rrDoor.tooltipText = "";
        redHydro.tooltipText = "";
        blueHydro.tooltipText = "";
        yellowHydro.tooltipText = "";
        gears.tooltipText = "";
    }
}
