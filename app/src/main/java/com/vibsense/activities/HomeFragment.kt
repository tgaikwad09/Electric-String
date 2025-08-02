package com.vibsense.activities

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.vibsense.app.databinding.FragmentHomeBinding
import com.vibsense.models.MoodEntry
import com.vibsense.utils.EmotionDetector
import com.vibsense.firebase.FirebaseService
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var recorder: MediaRecorder? = null
    private lateinit var audioFile: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recordFab.setOnClickListener { handleRecord() }
        return binding.root
    }

    private fun handleRecord() {
        if (recorder == null) startRecording() else stopRecording()
    }

    private fun startRecording() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 1001)
            return
        }
        audioFile = File(requireContext().cacheDir, "temp_audio_${System.currentTimeMillis()}.3gp")
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(audioFile.absolutePath)
            prepare()
            start()
        }
        binding.recordFab.setImageResource(android.R.drawable.ic_media_pause)
    }

    private fun stopRecording() {
        recorder?.apply { stop(); release() }
        recorder = null
        binding.recordFab.setImageResource(android.R.drawable.ic_btn_speak_now)

        val emotion = EmotionDetector.detect(audioFile)
        binding.emotionText.text = emotion.label
        binding.emotionEmoji.text = emotion.emoji

        // Save to Firebase
        FirebaseService.saveMood(MoodEntry(Date(), emotion.label))
    }
}