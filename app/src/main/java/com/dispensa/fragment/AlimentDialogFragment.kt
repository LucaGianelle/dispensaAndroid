package com.dispensa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dispensa.R
import com.dispensa.utils.DbCommunication
import kotlinx.android.synthetic.main.popup_insert_aliment.*
import kotlinx.android.synthetic.main.popup_insert_aliment.view.*

class AlimentDialogFragment : DialogFragment(){



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.popup_insert_aliment, container, false)

        rootView.buttonNo.setOnClickListener {
            dismiss()
        }

        rootView.buttonYes.setOnClickListener {

            val qt :String = quantitAliment.text.toString()
            if(qt != "")
            {
                DbCommunication.inserimentoAlimentoPersonale(qt)
                dismiss()
            }

        }

        return rootView
    }
}