package com.lalabib.mhsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.mhsid.databinding.ItemMahasiswaBinding
import com.lalabib.mhsid.domain.model.Mahasiswa

class MahasiswaAdapter : ListAdapter<Mahasiswa, MahasiswaAdapter.ViewHolder>(MahasiswaDiffUtil) {
    private object MahasiswaDiffUtil : DiffUtil.ItemCallback<Mahasiswa>() {
        override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem.uniqueId == newItem.uniqueId
        }

        override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaAdapter.ViewHolder {
        val binding = ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaAdapter.ViewHolder, position: Int) {
        val mahasiswa = getItem(position)
        if (mahasiswa != null) {
            holder.bind(mahasiswa)
        }
    }

    class ViewHolder(private val binding: ItemMahasiswaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mhs: Mahasiswa) {
            binding.apply {
                tvName.text = mhs.nama
                tvNim.text = mhs.nim
                tvUniv.text = mhs.univ
                tvProdi.text = mhs.prodi
            }
        }
    }
}