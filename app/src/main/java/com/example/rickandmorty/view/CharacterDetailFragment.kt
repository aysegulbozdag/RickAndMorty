package com.example.rickandmorty.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.network.Status
import com.example.rickandmorty.viewmodel.CharacterDetailViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.db.entity.FavCharacter
import com.example.rickandmorty.viewmodel.ViewModelFactory

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var bindig: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindig = FragmentCharacterDetailBinding.inflate(inflater)

        setInit()
        getCharacter()

        return bindig.root
    }

    private fun setInit(){
        viewModelFactory = ViewModelFactory(requireContext(), args.characterId)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                context,
                args.characterId
            )
        )[CharacterDetailViewModel::class.java]

        bindig.characterDetail = this
        viewModel.getFavCharacter.observe(this, Observer {
            bindig.favCharacterModel = it
        })
    }

  private suspend fun getEpisode(){
        viewModel.episodeState.collect {
            it.data?.let { episode ->
                bindig.episode = episode
            }
        }
    }

    fun getCharacter(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterState.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { character ->
                            bindig.character = character
                            getEpisode()
                        }
                    }
                    else -> {
                        Log.i("message", it.message.toString())
                    }
                }
            }
        }
    }

    fun saveCharacter() {
        viewModel.insert(FavCharacter(args.characterId, true))
    }


}