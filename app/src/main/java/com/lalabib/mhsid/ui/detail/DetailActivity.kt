package com.lalabib.mhsid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.mhsid.R
import com.lalabib.mhsid.adapter.SemesterAdapter
import com.lalabib.mhsid.adapter.StudiAdapter
import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.databinding.ActivityDetailBinding
import com.lalabib.mhsid.databinding.ContentDetailBinding
import com.lalabib.mhsid.domain.model.DetailMahasiswa
import com.lalabib.mhsid.utils.SharedObject.ganjil
import com.lalabib.mhsid.utils.SharedObject.genap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding

    private lateinit var smtAdapter: SemesterAdapter
    private lateinit var studiAdapter: StudiAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailContentBinding = binding.contentDetail

        setupAction()
        setupRecyclerView()
        setupData()
    }

    private fun setupAction() {
        binding.apply {
            icBackArrow.setOnClickListener { finish() }
        }

        recyclerviewSemester()
        binding.contentDetail.apply {
            val cvKuliah = cvStatusKuliah
            val tvKuliah = tvStatusKuliah
            val cvStudi = cvStatusStudi
            val tvStudi = tvStatusStudi

            cvKuliah.setOnClickListener {
                cardViewSelected(cvKuliah, tvKuliah, true)
                cardViewSelected(cvStudi, tvStudi, false)
                recyclerviewSemester()
            }

            cvStudi.setOnClickListener {
                cardViewSelected(cvKuliah, tvKuliah, false)
                cardViewSelected(cvStudi, tvStudi, true)
                recyclerviewStudi()
            }
        }
    }

    private fun setupRecyclerView() {
        smtAdapter = SemesterAdapter()
        studiAdapter = StudiAdapter()

        detailContentBinding.rvSmtMahasiswa.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = smtAdapter
        }

        detailContentBinding.rvStudiMahasiswa.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = studiAdapter
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
                            smtAdapter.submitList(detailMhs.data.dataSmtMahasiswa)
                            studiAdapter.submitList(detailMhs.data.dataKuliahMahasiswa)
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
                    tvSmtMasuk.append("$genap $actualSemester")
                } else {
                    tvSmtMasuk.append("$ganjil $actualSemester")
                }
            }

//            // Substring status
//            val status = mahasiswa.statusSaatIni
//            val index = status.indexOfFirst { it.isDigit() }
//            if (index != -1) {
//                tvStatusSaatIni.text = status.substring(0, index)
//            } else {
//                tvStatusSaatIni.text = status
//            }

        }
    }

    private fun cardViewSelected(cardView: CardView, textView: TextView, isSelected: Boolean) {
        cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                this@DetailActivity,
                if (isSelected) R.color.blue_500 else R.color.white
            )
        )

        textView.setTextColor(
            ContextCompat.getColor(
                this@DetailActivity,
                if (isSelected) R.color.white else R.color.black
            )
        )
    }

    private fun recyclerviewSemester() {
        binding.contentDetail.apply {
            cvListKuliah.visibility = View.VISIBLE
            cvListStudi.visibility = View.GONE
        }
    }

    private fun recyclerviewStudi() {
        binding.contentDetail.apply {
            cvListStudi.visibility = View.VISIBLE
            cvListKuliah.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}