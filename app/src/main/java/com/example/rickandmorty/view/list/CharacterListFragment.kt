package com.example.rickandmorty.view.list

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.view.filter.FilterCharacter
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.view.detail.CharacterDetailFragmentArgs
import com.example.rickandmorty.view.filter.CharacterFilterFragmentArgs
import kotlinx.coroutines.flow.collectLatest


class CharacterListFragment() : Fragment(), CharacterListAdapter.OnItemClickListener<Character>,
    FilterCharacter,
    Parcelable {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()
    private val characterListAdapter by lazy { CharacterListAdapter() }

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater)
        binding.characterListFragment = this
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
            getListData(null,null).collectLatest {
                this@CharacterListFragment.characterListAdapter.submitData(it)
            }
        }
    }

    override fun onItemClicked(position: Int, item: Character) {
        Navigation.findNavController(binding.root).navigate(R.id.navigateToDetailFragment,CharacterDetailFragmentArgs(
            item.id.toString()
        ).toBundle())
    }

    fun filterBtnClick() {
        Navigation.findNavController(binding.root).navigate(

            R.id.navigateToFilterFragment, CharacterFilterFragmentArgs(
                this@CharacterListFragment
            ).toBundle()
        )
    }

    override fun filterValue(name: String?, status: String?): Unit = with(viewModel) {
        status?.let { name?.let { it1 ->  viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            getListData(it1,it).collectLatest {
                this@CharacterListFragment.characterListAdapter.submitData(it)
            }
        } } }

    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<CharacterListFragment> {
        override fun createFromParcel(parcel: Parcel): CharacterListFragment {
            return CharacterListFragment(parcel)
        }

        override fun newArray(size: Int): Array<CharacterListFragment?> {
            return arrayOfNulls(size)
        }
    }

}