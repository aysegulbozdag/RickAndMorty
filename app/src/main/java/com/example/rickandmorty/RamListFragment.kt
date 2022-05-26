package com.example.rickandmorty

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.map
import com.example.rickandmorty.viewmodel.RamListViewModel
import com.example.rickandmorty.databinding.FragmentRamListBinding
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.RickAndMortyApiService
import com.example.rickandmorty.repository.CharacterRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class RamListFragment : Fragment() {
    private val viewModel: RamListViewModel by viewModels()

   // private  var viewModel : RamListViewModel?= null
    private lateinit var viewModelFactory: ViewModelFactoryy
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRamListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userItemsUiStates.collectLatest {
                it.map {
                  binding.txt.setText(it.name)
                }
            }
        }


        return binding.root
    }


}