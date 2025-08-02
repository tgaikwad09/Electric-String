package com.vibsense.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vibsense.models.MoodEntry

object FirebaseService {
    private const val TAG = "FirebaseService"
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private fun ensureAuth() {
        if (auth.currentUser == null) auth.signInAnonymously()
    }

    fun saveMood(entry: MoodEntry) {
        ensureAuth()
        db.collection("mood_history")
            .document(auth.uid ?: "unknown")
            .collection("entries")
            .add(entry)
            .addOnSuccessListener { Log.d(TAG, "Mood saved") }
            .addOnFailureListener { Log.e(TAG, "Error saving mood", it) }
    }

    fun fetchMoodHistory(callback: (List<MoodEntry>) -> Unit) {
        ensureAuth()
        db.collection("mood_history")
            .document(auth.uid ?: "unknown")
            .collection("entries")
            .get()
            .addOnSuccessListener { qs ->
                val list = qs.documents.mapNotNull { it.toObject(MoodEntry::class.java) }
                callback(list.sortedBy { it.date })
            }
            .addOnFailureListener {
                Log.e(TAG, "Error fetching", it)
                callback(emptyList())
            }
    }
}