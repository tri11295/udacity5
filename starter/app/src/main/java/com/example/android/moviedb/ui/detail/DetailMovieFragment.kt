package com.example.android.moviedb.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.moviedb.R
import com.example.android.moviedb.databinding.FragmentDetailMovieBinding
import com.example.android.moviedb.ultis.Constant.URI_YOUTUBE_APP
import com.example.android.moviedb.ultis.Constant.URI_YOUTUBE_WEBSITE
import com.google.android.material.appbar.AppBarLayout

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()
    private var idMovie: Int? = null
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()
    private val recommendationAdapter by lazy {
        RecommendationAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        binding.apply {
            detailMovieViewModel.initDataBase(requireContext())
            viewModel = this@DetailMovieFragment.detailMovieViewModel
            lifecycleOwner = this@DetailMovieFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initEvent()
        handleEvent()
    }

    private fun initData() {
        idMovie = args.id
        idMovie?.let {
            detailMovieViewModel.getDetailMovie(it)
            detailMovieViewModel.checkFavorite(it) { isFavorite ->
                if (isFavorite) {
                    binding.imageFavorite.setImageResource(R.drawable.ic_heart_red)
                } else {
                    binding.imageFavorite.setImageResource(R.drawable.ic_heart_default)
                }
            }
        }
    }

    private fun initView() {
        binding.recommendAdapter = recommendationAdapter
    }

    private fun initEvent() {
        with(binding) {
            imageBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            imagePlay.setOnClickListener {
                detailMovieViewModel.keyYoutube?.let { key -> openYouTube(key) } ?: Toast.makeText(
                    context,
                    getString(R.string.no_video),
                    Toast.LENGTH_SHORT
                ).show()
            }
            imageFavorite.setOnClickListener {
                if (detailMovieViewModel.isFavorite) {
                    detailMovieViewModel.unSaveMovie()
                    imageFavorite.setImageResource(R.drawable.ic_heart_default)
                } else {
                    detailMovieViewModel.saveMovie()
                    imageFavorite.setImageResource(R.drawable.ic_heart_red)
                }
            }
        }
    }

    private fun openYouTube(keyYoutube: String) {
        try {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_APP + keyYoutube)
                )
            )
        } catch (e: ActivityNotFoundException) {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_WEBSITE + keyYoutube)
                )
            )
        }
    }

    private fun handleEvent() {
        binding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
