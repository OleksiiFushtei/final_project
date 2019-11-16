package com.example.final_project.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.final_project.R
import com.example.final_project.core.MainApplication

class DevicesFragment :
    Fragment() {

    companion object {
        private const val ID =
            "id"

        fun newInstance(
            caught: Int
        ): DevicesFragment {
            val args =
                Bundle()
            args.putSerializable(
                ID,
                caught
            )
            val fragment =
                DevicesFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View =
            inflater.inflate(
                R.layout.fragment_device,
                container,
                false
            )

        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val app =
            context?.applicationContext as MainApplication

        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )

        progressBar?.visibility =
            View.VISIBLE

        return view
    }


}
