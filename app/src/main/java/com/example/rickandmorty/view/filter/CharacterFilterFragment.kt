package com.example.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.FragmentFilterCharacterBinding
import com.example.rickandmorty.interfaces.FilterCharacter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CharacterFilterFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterCharacterBinding
    private lateinit var callback: FilterCharacter
    private val args: CharacterFilterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterCharacterBinding.inflate(inflater)
        binding.characterFilterFragment = this
        callback = args.filterListener
        return binding.root
    }

    fun setQueryButton(name: String) {
        val status = binding.queryStatus.selectedItem.toString().toLowerCase()
        callback.filterValue(name, status)
        dismiss()
    }
}