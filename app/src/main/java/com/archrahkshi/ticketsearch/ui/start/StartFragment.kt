package com.archrahkshi.ticketsearch.ui.start

import android.content.Context.MODE_PRIVATE
import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.FragmentStartBinding
import com.archrahkshi.ticketsearch.ui.BaseFragment
import com.archrahkshi.ticketsearch.ui.DEPARTURE_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.destination.ChooseDestinationFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment<FragmentStartBinding>() {
    private val viewModel by viewModel<StartViewModel>()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentStartBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(views.departureTextField) {
            setText(
                requireActivity().getPreferences(MODE_PRIVATE).getString(DEPARTURE_TEXT_KEY, "")
            )
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    saveDepartureText(text.toString())
                }
            }
            views.destinationText.setOnClickListener {
                if (text.isNotEmpty()) {
                    openDestinationFragment(text.toString())
                }
            }
            setOnEditorActionListener { _, actionId, event ->
                val isEnterKeyPressed =
                    event?.keyCode == KEYCODE_ENTER && event.action == ACTION_DOWN
                if (actionId == IME_ACTION_DONE || isEnterKeyPressed) {
                    saveDepartureText(text.toString())
                    openDestinationFragment(text.toString())
                    true
                } else {
                    false
                }
            }
        }
        with(views.concerts) {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            lifecycleScope.launch {
                adapter = ConcertsAdapter(viewModel.getOffers(), getImages())
            }
            addItemDecoration(
                DividerItemDecoration(context, HORIZONTAL).apply {
                    getDrawable(context, R.drawable.concert_list_divider)?.let(::setDrawable)
                }
            )
        }
    }

    private fun saveDepartureText(text: String) {
        requireActivity().getPreferences(MODE_PRIVATE).edit {
            putString(DEPARTURE_TEXT_KEY, text)
        }
    }

    private fun openDestinationFragment(text: String) {
        ChooseDestinationFragment().apply {
            arguments = bundleOf(DEPARTURE_TEXT_KEY to text)
        }.show(parentFragmentManager, null)
    }

    private fun getImages() =
        listOf(R.raw.concert0, R.raw.concert1, R.raw.concert2).map {
            decodeStream(resources.openRawResource(it))
        }
}
