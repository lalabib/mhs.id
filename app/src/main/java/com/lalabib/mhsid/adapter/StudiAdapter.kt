package com.lalabib.mhsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.mhsid.databinding.ItemStatusStudiBinding
import com.lalabib.mhsid.domain.model.DetailKuliahMahasiswa

class StudiAdapter :
    ListAdapter<DetailKuliahMahasiswa, StudiAdapter.ViewHolder>(StudiDiffUtil) {

    object StudiDiffUtil : DiffUtil.ItemCallback<DetailKuliahMahasiswa>() {
        override fun areItemsTheSame(oldItem: DetailKuliahMahasiswa, newItem: DetailKuliahMahasiswa): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DetailKuliahMahasiswa, newItem: DetailKuliahMahasiswa): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStatusStudiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studiMhs = getItem(position)
        if (studiMhs != null) {
            holder.bind(studiMhs)
        }
    }

    class ViewHolder(
        private val binding: ItemStatusStudiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(studiMhs: DetailKuliahMahasiswa) {
            binding.apply {
                tvKodeMatkul.text = studiMhs.kodeMatkul
                tvNamaMatkul.text = studiMhs.namaMatkul
                tvSks.text = studiMhs.sks
                tvSemester.text = studiMhs.idSemester
            }
        }
    }
}