package project.wgt.capitalviewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import project.wgt.capitalviewer.ui.CountryAdapter

class MainFragment : Fragment() {

    private lateinit var countryAdapter: CountryAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var loading: ProgressBar
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = view.findViewById(R.id.loading)
        setupRecyclerView(view)
        setupUiStateObserver(view)
        viewModel.fetchCountries()
    }

    private fun setupUiStateObserver(view: View) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        CountriesUiState.Loading -> {
                            setLoading()
                        }
                        is CountriesUiState.Success -> {
                            endLoading()
                            countryAdapter.submitList(uiState.countries)
                        }
                        is CountriesUiState.Error -> {
                            endLoading()
                            Toast.makeText(activity, uiState.errorMessage, LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun setLoading() {
        loading.visibility = VISIBLE
    }

    private fun endLoading() {
        loading.visibility = GONE
    }

    private fun setupRecyclerView(view: View) {
        countryAdapter = CountryAdapter()
        recyclerView = view.findViewById(R.id.country_list)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = countryAdapter
        }
    }
}