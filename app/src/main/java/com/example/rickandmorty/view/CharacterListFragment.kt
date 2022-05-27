package com.example.rickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharacterListAdapter
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.viewmodel.CharacterListViewModel
import com.example.rickandmorty.model.Character
import kotlinx.coroutines.flow.collectLatest


class CharacterListFragment : Fragment(), CharacterListAdapter.OnItemClickListener<Character> {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()
    private val characterListAdapter by lazy { CharacterListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        setAdapter()
        setListeners()
        setData()

        return binding.root
    }

    private fun setAdapter() = with(binding) {
        characterList.adapter = characterListAdapter
    }

    private fun setListeners(){
        characterListAdapter.setOnClickListener(this)
    }

    private fun setData() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            getListData().collectLatest {
                this@CharacterListFragment.characterListAdapter.submitData(it)
            }
        }
    }

    override fun onItemClicked(position: Int, item: Character) {
        Navigation.findNavController(binding.root).navigate(R.id.navigateToDetailFragment,CharacterDetailFragmentArgs(
            item.id.toString()
        ).toBundle())
    }


}