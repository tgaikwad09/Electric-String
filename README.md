# VibSense – Voice Emotion Detection & Mental Wellness App

VibSense is an Android app that detects the emotional tone of a user’s voice and offers motivational content and mood-tracking analytics to support mental wellness.

---

## Features
1. Animated splash screen with logo.
2. Home screen with microphone to **Record Emotion** and display detected mood (Happy, Sad, Angry, Neutral) with emoji.
3. On-device voice-emotion detection powered by TensorFlow Lite (placeholder model).
4. Motivational Suggestions page – quotes & YouTube links tailored to mood.
5. Mood Tracker with daily emotion trend chart (MPAndroidChart).
6. Firebase Authentication (anonymous) & Cloud Firestore for mood history.
7. Bottom Navigation (Home, Motivation, Mood Tracker).
8. Material Design, dark theme by default with optional light mode.

---

## Project Structure
```
VibSense/
 ├─ app/
 │   ├─ src/main/java/com/vibsense/
 │   │   ├─ activities/ …  UI screens & fragments
 │   │   ├─ firebase/ …    Firebase helpers
 │   │   ├─ models/ …      Data classes
 │   │   └─ utils/ …       Reusable utilities
 │   ├─ res/ …             XML layouts, values, drawables, raw
 │   └─ build.gradle       Module Gradle script
 ├─ build.gradle           Project Gradle script
 └─ settings.gradle        Settings
```

---

## Getting Started
### Prerequisites
* Android Studio Flamingo ± or newer
* Android SDK 34
* Java 17
* A Firebase project (for Auth + Firestore)

### 1. Clone & Import
```bash
git clone <repo-url>
cd VibSense
open -a "Android Studio" .
```
Android Studio will sync Gradle automatically.

### 2. Firebase Setup
1. Create a Firebase project → **Add Android app** with package `com.vibsense.app`.
2. Download `google-services.json` and place it in `app/`.
3. In Firebase console, enable **Authentication → Anonymous**.
4. Enable **Cloud Firestore** (in test mode for quick start).

### 3. Build & Run
Click ▶️ **Run** in Android Studio or execute:
```bash
./gradlew installDebug
```
Grant **Record Audio** permission when prompted.

### 4. Testing Emotion Detection
A lightweight placeholder model returns a random emotion if a real model is not bundled. Plug your own TFLite model in `utils/EmotionDetector.kt` by replacing `detectPlaceholder()`.

---

## Customisation
* **Themes** – switch to light mode in Settings page (not yet implemented).
* **Quotes** – edit `res/raw/quotes.json` or connect to Firestore collection `quotes`.
* **Charts** – adjust look & feel in `MoodTrackerFragment`.

---

## License
MIT
