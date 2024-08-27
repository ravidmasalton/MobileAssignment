
# MobileAssignment - Thief Escape Game

## Project Overview

**MobileAssignment** is a thrilling mobile game where the player controls a thief escaping from vehicles while collecting sacks of gold. The goal is to accumulate the highest score by collecting as many sacks as possible before getting caught. Players can submit their names if they lose, and their scores may enter the top 10 leaderboard if they rank high enough. The game also features a map indicating the player's location when they played.

## Features

- **Thief Escape Gameplay**: Control the thief and evade capture while collecting sacks of gold.
- **Score System**: Earn points for each sack of gold collected.
- **Leaderboard**: Submit your name if you lose, and your score might be recorded in the top 10 high scores.
- **Location Tracking**: The game includes a map that shows the location of the player at the time they played.

## Installation

1. **Clone the repository**:
    ```bash
    git clone <https://github.com/ravidmasalton/MobileAssignment.git>
    ```
   
2. **Open the project in Android Studio**:
    - Open Android Studio.
    - Select `Open an existing project`.
    - Navigate to the directory where the repository was cloned and select the project.

3. **Set up the Google Maps API Key**:
    - Obtain a Google Maps API key from the [Google Cloud Console](https://console.cloud.google.com/).
    - In the project, find the `AndroidManifest.xml` file located in the `app/src/main/` directory.
    - Replace `com.google.android.geo.API_KEY` with your actual API key:
      ```xml
      <meta-data
          android:name="com.google.android.geo.API_KEY"
          android:value="YOUR_API_KEY_HERE"/>
      ```

4. **Build the project**:
    - Let Gradle sync and build the project.
    - Ensure that you have a connected Android device or an emulator set up.

5. **Run the application**:
    - Click the "Run" button in Android Studio.
    - Select your device/emulator to run the app.

## Usage

- **Start the Game**: Launch the app and start controlling the thief using the on-screen controls.
- **Collect Gold**: Navigate through the game, avoiding obstacles and collecting sacks of gold.
- **Submit Your Score**: If caught, you can submit your name if your score is high enough to make the leaderboard.
- **View Leaderboard**: Check out the top 10 scores to see if you made it to the list.
- **Map Feature**: View the map to see player location.

## Dependencies

- **Android SDK**: Ensure you have the latest version of the Android SDK installed.
- **Google Maps SDK**: Used for the location tracking feature.


## Screenshots

Here are some screenshots of the game in action:

- **Opening Screen**: This image shows the main gameplay screen where the thief is escaping and collecting gold. On this screen, the user can choose to play in fast mode or select whether to use the mobile device's sensors for controlling the player’s movement.
  
  ![Opening screen](https://github.com/user-attachments/assets/20e41e9f-cb92-48af-bfee-03dba6e73a50)


  




- **Main Game**: This image shows the main gameplay.


  ![Main game](https://github.com/user-attachments/assets/f239cdb5-9304-453a-b75e-5dc0c7b78455)



  

- **Insert Name After Loss**: This image displays the screen where the player can insert their name after losing.

  ![Insert name after loss](https://github.com/user-attachments/assets/e11f4da7-7ec5-4d8a-b5c5-b50b640a70c3)
  





- **Score Table**: This image displays the leaderboard with the top 10 high scores. When you click on a user's name, the map is updated and shows the location where that user played the game.
  
  ![Score table](https://github.com/user-attachments/assets/40d17256-48dd-49d5-b8d4-1bd79c48d93b)



Thank you for checking out MobileAssignment! I hope you enjoy playing the game as much as I enjoyed creating it. Happy gaming!




