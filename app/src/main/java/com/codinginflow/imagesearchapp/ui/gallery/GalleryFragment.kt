package com.codinginflow.imagesearchapp.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>() //since we have added annotation @AndroidEntryPoint, 'GalleryViewModel' will be added auto by Hilt/Dagger

    private var _binding : FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) //this is where we want to observe live data

        _binding = FragmentGalleryBinding.bind(view)
        val adapter = UnsplashPhotoAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true) //simply means that the dimension of the content inside the recyclerview item is not going to change
            recyclerView.adapter = adapter
        }

        viewModel.photos.observe(viewLifecycleOwner) {  //this function will be triggered whenever our 'photos live data' changes
            adapter.submitData(viewLifecycleOwner.lifecycle , it)   //here the lifecycle indicates the lifecycle of the view inside the fragment and not of the fragment itself
        }  //important to add this because we want to stop updating our ui whenever our fragment is destroyed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}