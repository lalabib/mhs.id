package com.lalabib.mhsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.mhsid.databinding.ItemMahasiswaBinding
import com.lalabib.mhsid.domain.model.Mahasiswa

class MahasiswaAdapter(private val onItemClick: (Mahasiswa) -> Unit) :
    ListAdapter<Mahasiswa, MahasiswaAdapter.ViewHolder>(MahasiswaDiffUtil) {

    private object MahasiswaDiffUtil : DiffUtil.ItemCallback<Mahasiswa>() {
        override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem.uniqueId == newItem.uniqueId
        }

        override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(onItemClick, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mahasiswa = getItem(position)
        if (mahasiswa != null) {
            holder.bind(mahasiswa)
        }
    }

    class ViewHolder(
        private val onItemClick: (Mahasiswa) -> Unit,
        private val binding: ItemMahasiswaBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mahasiswa: Mahasiswa) {
            binding.apply {
                tvName.text = mahasiswa.nama
                tvNim.text = mahasiswa.nim
                tvProdi.text = mahasiswa.prodi

                // Remove spaces
                val input = mahasiswa.univ
                val result = input.trimStart()
                tvUniv.text = result
            }
            itemView.setOnClickListener { onItemClick(mahasiswa) }
        }
    }
}