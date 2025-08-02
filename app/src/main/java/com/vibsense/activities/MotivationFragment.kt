package com.vibsense.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vibsense.app.databinding.FragmentMotivationBinding
import org.json.JSONArray

class MotivationFragment : Fragment() {
    private lateinit var binding: FragmentMotivationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMotivationBinding.inflate(inflater, container, false)
        loadQuotes()
        return binding.root
    }

    private fun loadQuotes() {
        val input = resources.openRawResource(R.raw.quotes).bufferedReader().use { it.readText() }
        val arr = JSONArray(input)
        val random = arr.getJSONObject((0 until arr.length()).random())
        binding.quoteText.text = random.getString("quote")
        binding.authorText.text = random.getString("author")
    }
}