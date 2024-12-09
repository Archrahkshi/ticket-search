package com.archrahkshi.ticketsearch.ui

import android.content.Context.MODE_PRIVATE
import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.domain.getOffers
import kotlinx.coroutines.launch

private const val SAVED_DEPARTURE_TEXT_KEY = "SAVED_DEPARTURE_TEXT"

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(requireActivity().findViewById<EditText>(R.id.departure_text_field)) {
            val sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
            setText(sharedPreferences.getString(SAVED_DEPARTURE_TEXT_KEY, ""))
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sharedPreferences.edit {
                        putString(SAVED_DEPARTURE_TEXT_KEY, text.toString())
                    }
                }
            }
        }
        with(requireActivity().findViewById<RecyclerView>(R.id.concerts)) {
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            lifecycleScope.launch {
                adapter = ConcertsAdapter(
                    getOffers(),
                    listOf(R.raw.concert0, R.raw.concert1, R.raw.concert2).map {
                        decodeStream(resources.openRawResource(it))
                    }
                )
            }
        }
    }
}
