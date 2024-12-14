package com.archrahkshi.ticketsearch.ui.destination

import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.bundle.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.data.vo.Destination
import com.archrahkshi.ticketsearch.databinding.FragmentChooseDestinationBinding
import com.archrahkshi.ticketsearch.ui.DEPARTURE_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.DESTINATION_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.NyiFragment
import com.archrahkshi.ticketsearch.ui.search.SearchFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseDestinationFragment : BottomSheetDialogFragment() {
    private var _views: FragmentChooseDestinationBinding? = null
    private val views get() = _views!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _views = FragmentChooseDestinationBinding.inflate(inflater, container, false)
        return views.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.departureText.text = arguments?.getString(DEPARTURE_TEXT_KEY, "")
        views.clearIcon.setOnClickListener {
            views.destinationTextField.text.clear()
        }
        with(views.popularDestinations) {
            layoutManager = LinearLayoutManager(context)
            adapter = PopularDestinationsAdapter(getPopularDestinations())
            addItemDecoration(
                DividerItemDecoration(context, VERTICAL).apply {
                    getDrawable(context, R.drawable.popular_destinations_list_divider)
                        ?.let(::setDrawable)
                }
            )
        }
        val nyiClickListener = OnClickListener {
            dismiss()
            parentFragmentManager.commit {
                add(R.id.fragment_container, NyiFragment())
                addToBackStack(null)
            }
        }
        views.complexRouteView.setOnClickListener(nyiClickListener)
        views.destinationWhereverView.setOnClickListener {
            navigateToSearch(getPopularDestinations().map { it.title }.random())
        }
        views.weekendView.setOnClickListener(nyiClickListener)
        views.hotTicketsView.setOnClickListener(nyiClickListener)
    }

    private fun getPopularDestinations(): List<Destination> {
        val subtitle = getString(R.string.popular_destination)
        return listOf(
            Destination(
                getString(R.string.istanbul),
                subtitle,
                rawToBitmap(R.raw.istanbul),
                ::navigateToSearch
            ),
            Destination(
                getString(R.string.sochi),
                subtitle,
                rawToBitmap(R.raw.sochi),
                ::navigateToSearch
            ),
            Destination(
                getString(R.string.phuket),
                subtitle,
                rawToBitmap(R.raw.phuket),
                ::navigateToSearch
            )
        )
    }

    private fun navigateToSearch(destinationText: String) {
        dismiss()
        parentFragmentManager.commit {
            add(
                R.id.fragment_container,
                SearchFragment::class.java,
                bundleOf(
                    DEPARTURE_TEXT_KEY to views.departureText.text,
                    DESTINATION_TEXT_KEY to destinationText
                )
            )
            addToBackStack(null)
        }
    }

    private fun rawToBitmap(@RawRes rawRes: Int) =
        decodeStream(resources.openRawResource(rawRes))

    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
    }
}
