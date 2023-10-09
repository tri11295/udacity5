package com.example.android.moviedb.ui.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.moviedb.databinding.FragmentActorBinding
import com.google.android.material.appbar.AppBarLayout

class ActorFragment : Fragment() {

    private val actorDetailViewModel: ActorDetailViewModel by viewModels()

    private val args: ActorFragmentArgs by navArgs()

    private lateinit var binding: FragmentActorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActorBinding.inflate(layoutInflater).apply {
            actorViewModel = actorDetailViewModel
            lifecycleOwner = this@ActorFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actorDetailViewModel.getActorDetail(args.id)
        binding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / binding.appbarLayout.totalScrollRange.toFloat()
            binding.motionLayout.progress = seekPosition
        }
        binding.appbarLayout.addOnOffsetChangedListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.actorViewModel = null
    }
}