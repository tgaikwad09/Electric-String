package com.vibsense.models

import java.util.Date

data class MoodEntry(
    val date: Date = Date(),
    val emotion: String = "Neutral"
) {
    fun scale(): Float = when (emotion) {
        "Happy" -> 3f
        "Neutral" -> 2f
        "Sad" -> 1f
        "Angry" -> 0f
        else -> 2f
    }
}