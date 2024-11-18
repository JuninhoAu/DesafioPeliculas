package com.juni.desafiopeliculas.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.juni.desafiopeliculas.domain.model.MovieModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMoviesFragment : Fragment() {

    private val viewModel: ListMovieViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ListScreen(viewModel = viewModel) {
                        itemDetailScreen(it)
                    }
                }
            }

        }
    }

    private fun itemDetailScreen(movieModel: MovieModel) {
        val fragmentDir = ListMoviesFragmentDirections
        val action = fragmentDir.actionListMoviesFragmentToListDetailFragment(movie = movieModel)
        findNavController().navigate(action)
    }
}