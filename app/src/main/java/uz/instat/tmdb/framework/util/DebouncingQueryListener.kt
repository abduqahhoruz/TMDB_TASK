package uz.instat.tmdb.framework.util

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.instat.tmdb.data.network.NetworkConstants

internal class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {
    var debouncePeriod: Long = NetworkConstants.SEARCH_DEBOUNCE
    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}