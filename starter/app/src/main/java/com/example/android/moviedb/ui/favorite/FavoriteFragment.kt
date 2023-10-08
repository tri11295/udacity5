package com.example.android.moviedb.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.moviedb.databinding.FragmentFavoriteBinding
import com.example.android.moviedb.ui.hot.adapter.HotMovieAdapter
import com.google.android.material.appbar.AppBarLayout

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val hotMovieAdapter by lazy {
        HotMovieAdapter {
            findNavController().navigate(
                FavoriteFragmentDirections.actionItemFavoritePageToDetailMovieFragment(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater).apply {
            favoriteViewModel.initDataBase(requireContext())
            viewModel = favoriteViewModel
            lifecycleOwner = this@FavoriteFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = hotMovieAdapter
        favoriteViewModel.movieResult.observe(viewLifecycleOwner) {
            hotMovieAdapter.setData(it.toMutableList())
        }
    }
}