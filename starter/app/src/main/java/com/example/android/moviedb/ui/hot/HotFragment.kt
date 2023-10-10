package com.example.android.moviedb.ui.hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.android.moviedb.R
import com.example.android.moviedb.databinding.FragmentHotBinding
import com.example.android.moviedb.ui.hot.adapter.HotMovieAdapter
import com.example.android.moviedb.ui.hot.viewmodel.HotMovieViewModel
import com.example.android.moviedb.ultis.Constant
import com.example.android.moviedb.ultis.HotMovieType

class HotFragment : Fragment() {

    private val hotMovieViewModel: HotMovieViewModel by viewModels()
    private val hotMovieAdapter by lazy {
        HotMovieAdapter {
            findNavController().navigate(
                HotFragmentDirections.actionItemHotPageToDetailMovieFragment(
                    it
                )
            )
        }
    }
    private lateinit var binding: FragmentHotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHotBinding>(
            inflater,
            R.layout.fragment_hot,
            container,
            false
        ).apply {
            viewModel = hotMovieViewModel
            lifecycleOwner = this@HotFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        hotMovieViewModel.getStateType()?.let { type ->
            hotMovieViewModel.typeHotMovie.value = type
            when (type) {
                HotMovieType.POPULAR.path -> {
                    setButtonClick(binding.btnPopular)
                    setButtonNotClick(binding.btnTopRate)
                    setButtonNotClick(binding.btnUpComing)
                }

                HotMovieType.TOP_RATED.path -> {
                    setButtonClick(binding.btnTopRate)
                    setButtonNotClick(binding.btnPopular)
                    setButtonNotClick(binding.btnUpComing)
                }

                else -> {
                    setButtonClick(binding.btnUpComing)
                    setButtonNotClick(binding.btnTopRate)
                    setButtonNotClick(binding.btnPopular)
                }
            }
        }
        setOnClickButton()
    }

    private fun initData() {
        binding.adapter = hotMovieAdapter
    }

    private fun setOnClickButton() {
        binding.btnPopular.setOnClickListener {
            changeDataMovie(HotMovieType.POPULAR)
            setButtonClick(binding.btnPopular)
            setButtonNotClick(binding.btnTopRate)
            setButtonNotClick(binding.btnUpComing)
        }
        binding.btnTopRate.setOnClickListener {
            changeDataMovie(HotMovieType.TOP_RATED)
            setButtonClick(binding.btnTopRate)
            setButtonNotClick(binding.btnPopular)
            setButtonNotClick(binding.btnUpComing)
        }
        binding.btnUpComing.setOnClickListener {
            changeDataMovie(HotMovieType.UP_COMING)
            setButtonClick(binding.btnUpComing)
            setButtonNotClick(binding.btnTopRate)
            setButtonNotClick(binding.btnPopular)
        }
    }

    private fun changeDataMovie(typeHotMovie: HotMovieType) {
        binding.recyclerViewHotMovie.layoutManager?.scrollToPosition(0)
        with(hotMovieViewModel) {
            addHotMovieChange(typeHotMovie)
            fetchDataHotMovie(Constant.DEFAULT_PAGE, typeHotMovie.path)
        }
    }

    private fun setButtonClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private fun setButtonNotClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button_not_click, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }
}