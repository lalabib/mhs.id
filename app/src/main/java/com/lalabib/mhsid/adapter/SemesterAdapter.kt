package com.lalabib.mhsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.mhsid.databinding.ItemStatusKuliahBinding
import com.lalabib.mhsid.domain.model.DetailSmtMahasiswa
import com.lalabib.mhsid.utils.SharedObject.zero

class SemesterAdapter :
    ListAdapter<DetailSmtMahasiswa, SemesterAdapter.ViewHolder>(SemesterDiffUtil) {

    object SemesterDiffUtil : DiffUtil.ItemCallback<DetailSmtMahasiswa>() {
        override fun areItemsTheSame(oldItem: DetailSmtMahasiswa, newItem: DetailSmtMahasiswa): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DetailSmtMahasiswa, newItem: DetailSmtMahasiswa): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStatusKuliahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val smtMahasiswa = getItem(position)
        if (smtMahasiswa != null) {
            holder.bind(smtMahasiswa)
        }
    }

    class ViewHolder(
        private val binding: ItemStatusKuliahBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(smtMahasiswa: DetailSmtMahasiswa) {
            binding.apply {
                tvStatus.text = smtMahasiswa.statusMahasiswa
                tvSemester.text = smtMahasiswa.idSemester

                // Check sks is null or not
                val sks = smtMahasiswa.sks
                if (sks != null) {
                    tvSks.text = smtMahasiswa.sks
                } else {
                    tvSks.text = zero
                }
            }
        }
    }
}