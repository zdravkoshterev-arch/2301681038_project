# Car Maintenance Tracker

## Project Idea
Car Maintenance Tracker is a mobile application designed to help vehicle owners keep track of their car's service history. Users can log maintenance tasks such as oil changes, tire rotations, and brake repairs, including mileage, cost, and date.

## How It Works
The app allows users to view a list of all maintenance records on the main screen. By tapping the floating action button, users can add new records. Existing records can be edited or deleted by tapping on them in the history list. All data is persisted locally using a Room database.

## Architecture
The application follows the **MVVM (Model-View-ViewModel)** architecture:
- **Model**: Room entities and DAO for data persistence.
- **View**: Jetpack Compose screens for the UI.
- **ViewModel**: Handles UI logic and provides data from the repository to the UI using StateFlow.
- **Repository Pattern**: Abstracts data sources and provides a clean API for the ViewModel.

## User Flow
1. **Service History Screen**: View all logged maintenance records.
2. **Add Record**: Click the '+' button to navigate to the Add screen.
3. **Save Record**: Fill in service name, mileage, cost, and notes, then tap 'Save'.
4. **Edit Record**: Tap a record in the history list to edit its details.
5. **Delete Record**: Tap a record and click the trash icon in the top bar to delete it.

## Technical Requirements
- Kotlin
- Min SDK 24
- Room Database
- Navigation Component (Compose)
- Material 3 Design
- MVVM Architecture

## Installation Instructions
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device (API 24+).

## Testing
- **Unit Tests**:
  - `ServiceRepositoryTest`: Verifies repository logic using MockK.
  - `ServiceViewModelTest`: Verifies ViewModel logic and repository interaction.
- **UI Tests**:
  - `MainActivityTest`: Verifies screen navigation and presence of key UI elements using Compose Test Rule.

To run tests:
- Right-click `app/src/test` and select "Run 'Tests in 'com.example...'"
- Right-click `app/src/androidTest` and select "Run 'Tests in 'com.example...'"

## Screenshots Section
(Add screenshots here)

## APK Section
(Link to APK here)
