package com.example.rickandmorty.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.RowRvBinding
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.util.BaseDiffUtilItemCallback

class CharacterListAdapter : PagingDataAdapter<Character, CharacterListAdapter.MyViewHolder>(
    BaseDiffUtilItemCallback<Character>()
) {

    private lateinit var clickListener: OnItemClickListener<Character>

    fun setOnClickListener(listener: OnItemClickListener<Character>){
        this.clickListener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position) as Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowRvBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding)
    }

   inner class MyViewHolder(private val binding: RowRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) = with(binding) {
            character = item
            binding.characterItemLayout.setOnClickListener {
                clickListener.onItemClicked(bindingAdapterPosition, item)
            }
        }

    }

    interface OnItemClickListener<T>{
        fun onItemClicked(position: Int, item: T)
    }
}


