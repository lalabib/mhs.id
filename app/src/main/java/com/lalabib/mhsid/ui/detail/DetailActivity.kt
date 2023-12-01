package com.lalabib.mhsid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lalabib.mhsid.R
import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.databinding.ActivityDetailBinding
import com.lalabib.mhsid.databinding.ContentDetailBinding
import com.lalabib.mhsid.domain.model.DetailMahasiswa
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailContentBinding = binding.contentDetail

        setupAction()
        setupData()
    }

    private fun setupAction() {
        binding.apply {
            icBackArrow.setOnClickListener { finish() }
        }
    }

    private fun setupData() {
        val uniqueId = intent.getStringExtra(EXTRA_DATA)
        if (uniqueId != null) {
            detailViewModel.getDetailMahasiswa(uniqueId).observe(this) { detailMhs ->
                if (detailMhs != null) {
                    when (detailMhs) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            populateMahasiswa(detailMhs.data.dataMahasiswa)
                        }
                        is Result.Error -> {
                            Toast.makeText(
                                this@DetailActivity,
                                getString(R.string.error_data),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun populateMahasiswa(mahasiswa: DetailMahasiswa) {
        binding.contentDetail.apply {
            tvName.text = mahasiswa.nama
            tvNim.text = mahasiswa.nim
            tvJenisKelamin.text = mahasiswa.jenisKelamin
            tvUniv.text = mahasiswa.univ
            tvProdi.text = mahasiswa.prodi
            tvJenjang.text = mahasiswa.jenjang
            tvStatusAwal.text = mahasiswa.statusAwal
            tvStatusSaatIni.text = mahasiswa.statusSaatIni

            // Convert semester data
            val semester = mahasiswa.smtAwal.toIntOrNull()
            val actualSemester = mahasiswa.smtAwal.substring(0, mahasiswa.smtAwal.length - 1)

            if (semester != null) {
                if (semester % 2 == 0) {
                    tvSmtMasuk.append("${getString(R.string.genap)} $actualSemester")
                } else {
                    tvSmtMasuk.append("${getString(R.string.ganjil)} $actualSemester")
                }
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}