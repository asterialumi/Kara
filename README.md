# 🌿 Kara
> *"Time often slips away. We do know we worked, but we lose track on where exactly that time was spent, what specific skills we practiced, and how much time we put into different areas."*
>
> **How do we fix that?**

Introducing **Kara**, a modern offline-first time tracker tool designed for artists, students, hobbyists and the like. It allows you to keep track of "work hours" and turn them into meaningful data by organizing time into **Projects** and specialized **Tags**.


## ✨ Key Features

*    **Multi-Account Support** – Create multiple local profiles to keep your school, work, and personal tracking completely separate on a single device.
*    **Persistent Stopwatch** – A "reboot-proof" timer. Whether you switch apps, close Kara, or restart your phone, your session stays active and accurate.
*    **Granular Organization** – Categorize every entry by Project and add comma-separated Tags (e.g., #anatomy, #shading) to see exactly where your effort goes.
*    **Real-Time Analytics** – Instant insights. View your total time tracked and see your projects and tags ranked by total duration.
*    **Data Portability** – You own your data. Export your entire history into a portable **JSON** format via the clipboard and restore it instantly on any device.
*    **Privacy Focused** – 100% offline. No cloud, no tracking, no data collection. Your productivity data stays on your machine.


## 🛠️ Tech Stack & Architecture

This school project (for our mobile development) was built with a focus on **Scalability** and **Clean Code**:

*   **Language:** 100% [Kotlin](https://kotlinlang.org/).
*   **Architecture:** **MVP (Model-View-Presenter)** + **Vertical Slicing**.
*   **Persistence:** **GSON** for JSON serialization and **SharedPreferences** for lightweight, high-performance local storage.
*   **UI/UX:** Material Design Components, RecyclerView, and Kotlin Extension Functions for a modernized interface.


## 🚀 How to Use

1.  **Onboard:** Register a local account and log-in.
2.  **Organize:** Visit the **Home** tab and use the `+` buttons to create your Projects (e.g., *Digital Art*) and common Tags (e.g., *Anatomy*).
3.  **Track:** Go to the **Timer** tab, select your project, type a description, and hit **Play**. You can pause and resume as needed. Add tags if you want, and they will be automatically added to your list if they didn't exist yet.
4.  **Save:** Hit **Stop** to commit your session to history. You can view them on the **Entries** tab. Kara will automatically update your statistics.
5.  **Analyze:** Head to the **Stats** tab to see a beautiful breakdown of your productivity trends! You'll see a list of your top projects and top tags.
6.  **Backup:** Use the **Settings** gear on top to export your data to the clipboard for safe-keeping. You can choose to import them to a different account, or to a different device.


## 📌 Upcoming Features *(if I'm not lazy)*

*   A proper **Searching** and **Filtering** for entries
*   QOL features such as **Quick Entry Creation**, **Updating Projects/Tags/Entries**, **Changing Password**, etc.
*   An actual count-down **Timer** and **Pomodoro**
*   **Calendar View** + **Upgraded Analytics Interface**
*   **More customization for Analytics Display and Projects**
*   **Time-based Tasks/Goals/Streaks**


## 📲 Installation

1.  Download the latest **`Kara_v1.0-beta.apk`** from the [Releases](https://github.com/asterialumi/Kara/releases/tag/v1.0-beta) section.
2.  Transfer the file to your Android device.
3.  Tap the file to install (you may need to "Allow from this source" in your phone settings).
4.  Start your time-tracking journey!


### 💡 Tip
*You can instantly fill the app with sample data by using the **Import** feature in settings and pasting a pre-formatted Kara JSON string! Here's an [example text](https://github.com/user-attachments/files/28221010/sample.txt)
 you can copy.*
