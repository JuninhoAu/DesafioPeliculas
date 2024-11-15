package com.juni.desafiopeliculas.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.findNavController
import com.juni.desafiopeliculas.data.network.MovieService
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import com.juni.desafiopeliculas.domain.GetMovieListUseCase


class ListMoviesFragment : Fragment() {

    private val viewModel: ListMovieViewModel by lazy {
        ListMovieViewModel(
            GetMovieListUseCase(
                GetMovieListRepository(MovieService())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ListScreen(viewModel = viewModel) {
                        val action =
                            ListMoviesFragmentDirections.actionListMoviesFragmentToListDetailFragment(
                                movie = it
                            )
                        findNavController().navigate(action)
                    }
                }
            }

        }
    }
}