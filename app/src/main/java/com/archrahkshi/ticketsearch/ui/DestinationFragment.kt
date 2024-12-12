package com.archrahkshi.ticketsearch.ui

import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.data.Destination
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DestinationFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_destination, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.departure_text).text =
            arguments?.getString(SAVED_DEPARTURE_TEXT_KEY, "")
        val destinationTextField = view.findViewById<EditText>(R.id.destination_text_field)
        view.findViewById<ImageView>(R.id.clear_icon).setOnClickListener {
            destinationTextField.text.clear()
        }
        with(view.findViewById<RecyclerView>(R.id.popular_destinations)) {
            layoutManager = LinearLayoutManager(context)
            adapter = PopularDestinationsAdapter(getPopularDestinations(), destinationTextField)
            addItemDecoration(
                DividerItemDecoration(context, VERTICAL).apply {
                    getDrawable(context, R.drawable.popular_destinations_list_divider)
                        ?.let(::setDrawable)
                }
            )
        }
        val nyiClickListener = OnClickListener {
            dismiss()
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NyiFragment())
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<TextView>(R.id.complex_route_view).setOnClickListener(nyiClickListener)
        view.findViewById<TextView>(R.id.destination_wherever_view).setOnClickListener {
            destinationTextField.setText(getPopularDestinations().map { it.title }.random())
        }
        view.findViewById<TextView>(R.id.weekend_view).setOnClickListener(nyiClickListener)
        view.findViewById<TextView>(R.id.hot_tickets_view).setOnClickListener(nyiClickListener)

        lifecycleScope
    }

    private fun getPopularDestinations() = listOf(
        Destination("Стамбул", "Популярное направление", rawToBitmap(R.raw.istanbul)),
        Destination("Сочи", "Популярное направление", rawToBitmap(R.raw.sochi)),
        Destination("Пхукет", "Популярное направление", rawToBitmap(R.raw.phuket))
    )

    private fun rawToBitmap(@RawRes rawRes: Int) =
        decodeStream(resources.openRawResource(rawRes))
}
