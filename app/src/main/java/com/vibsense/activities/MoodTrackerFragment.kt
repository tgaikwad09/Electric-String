package com.vibsense.activities

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.vibsense.app.databinding.FragmentMoodTrackerBinding
import com.vibsense.firebase.FirebaseService

class MoodTrackerFragment : Fragment() {
    private lateinit var binding: FragmentMoodTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoodTrackerBinding.inflate(inflater, container, false)
        loadChart()
        return binding.root
    }

    private fun loadChart() {
        FirebaseService.fetchMoodHistory { list ->
            val entries = list.mapIndexed { index, mood -> Entry(index.toFloat(), mood.scale()) }
            val dataSet = LineDataSet(entries, "Mood Trend").apply {
                color = Color.CYAN
                setCircleColor(Color.WHITE)
                valueTextColor = Color.WHITE
            }
            binding.lineChart.apply {
                data = LineData(dataSet)
                description = Description().apply { text = "Daily Mood" }
                invalidate()
            }
        }
    }
}