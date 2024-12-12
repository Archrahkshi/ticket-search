package com.archrahkshi.ticketsearch.ui

import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.data.Destination
import com.archrahkshi.ticketsearch.databinding.FragmentDestinationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DestinationFragment : BottomSheetDialogFragment() {
    private var _views: FragmentDestinationBinding? = null
    private val views get() = _views!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _views = FragmentDestinationBinding.inflate(inflater, container, false)
        return views.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.departureText.text = arguments?.getString(SAVED_DEPARTURE_TEXT_KEY, "")
        views.clearIcon.setOnClickListener {
            views.destinationTextField.text.clear()
        }
        with(views.popularDestinations) {
            layoutManager = LinearLayoutManager(context)
            adapter =
                PopularDestinationsAdapter(getPopularDestinations(), views.destinationTextField)
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
        views.complexRouteView.setOnClickListener(nyiClickListener)
        views.destinationWhereverView.setOnClickListener {
            views.destinationTextField.setText(getPopularDestinations().map { it.title }.random())
        }
        views.weekendView.setOnClickListener(nyiClickListener)
        views.hotTicketsView.setOnClickListener(nyiClickListener)
    }

    private fun getPopularDestinations() = listOf(
        Destination("Стамбул", "Популярное направление", rawToBitmap(R.raw.istanbul)),
        Destination("Сочи", "Популярное направление", rawToBitmap(R.raw.sochi)),
        Destination("Пхукет", "Популярное направление", rawToBitmap(R.raw.phuket))
    )

    private fun rawToBitmap(@RawRes rawRes: Int) =
        decodeStream(resources.openRawResource(rawRes))

    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
    }
}
