package com.dispensa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.popup_insert_aliment.view.*

class AlimentDialogFragment : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var rootView: View = inflater.inflate(R.layout.popup_insert_aliment, container, false)

        rootView.buttonNo.setOnClickListener {
            dismiss()
        }

        rootView.buttonYes.setOnClickListener {

            //Qui ci inseriamo l'invio della quantità e l'aggiunta alla dispensa persoanle
        }

        return rootView
    }
}