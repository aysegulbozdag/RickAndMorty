package com.example.rickandmorty.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.Status
import com.example.rickandmorty.viewmodel.CharacterDetailViewModel
import kotlinx.coroutines.launch

class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var bindig: FragmentCharacterDetailBinding
     val args : CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindig = FragmentCharacterDetailBinding.inflate(inflater)
        viewModel.getNewComment(args.characterId.toInt())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.commentState.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { character ->
                            bindig.character = character
                        }
                    }
                    else -> { Log.i("message",it.message.toString())}
                }
            }
        }

        return bindig.root
    }

}