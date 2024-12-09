package com.archrahkshi.ticketsearch.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.archrahkshi.ticketsearch.R

private const val SAVED_DEPARTURE_TEXT = "SAVED_DEPARTURE_TEXT"

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
        with(requireActivity().findViewById<EditText>(R.id.departure_text_field)) {
            setText(sharedPreferences.getString(SAVED_DEPARTURE_TEXT, ""))
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sharedPreferences.edit {
                        putString(SAVED_DEPARTURE_TEXT, text.toString())
                    }
                }
            }
        }
    }
}
