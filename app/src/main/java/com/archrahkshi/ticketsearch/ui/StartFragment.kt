package com.archrahkshi.ticketsearch.ui

import android.content.Context.MODE_PRIVATE
import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.FragmentStartBinding
import com.archrahkshi.ticketsearch.domain.getOffers
import kotlinx.coroutines.launch

const val SAVED_DEPARTURE_TEXT_KEY = "SAVED_DEPARTURE_TEXT_KEY"
private const val PRICE_TEMPLATE = "{price}"

class StartFragment : BaseFragment<FragmentStartBinding>() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentStartBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.departureTextField) {
            val sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
            setText(sharedPreferences.getString(SAVED_DEPARTURE_TEXT_KEY, ""))
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sharedPreferences.edit {
                        putString(SAVED_DEPARTURE_TEXT_KEY, text.toString())
                    }
                }
            }
            binding.destinationText.setOnClickListener {
                if (text.isNotEmpty()) {
                    DestinationFragment().apply {
                        arguments = bundleOf(SAVED_DEPARTURE_TEXT_KEY to text.toString())
                    }.show(parentFragmentManager, null)
                }
            }
        }
        with(binding.concerts) {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            lifecycleScope.launch {
                adapter = ConcertsAdapter(
                    getOffers(),
                    getImages(),
                    context.getString(R.string.concert_flight_price, PRICE_TEMPLATE)
                )
            }
            addItemDecoration(
                DividerItemDecoration(context, HORIZONTAL).apply {
                    getDrawable(context, R.drawable.concert_list_divider)?.let(::setDrawable)
                }
            )
        }
    }

    private fun getImages() =
        listOf(R.raw.concert0, R.raw.concert1, R.raw.concert2).map {
            decodeStream(resources.openRawResource(it))
        }
}
