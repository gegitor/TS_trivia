# TSTrivia

TSTrivia is an Android trivia application specifically designed for the game Twilight Struggle. It helps players test and improve their knowledge of the game's cards and events.

## Getting Started

### Building and Running the App

1. Clone the repository: `git clone https://github.com/your-username/tstrivia.git` (Replace `your-username` with the actual username or project path if known, otherwise leave as a placeholder).
2. Open the project in Android Studio.
3. Let Android Studio download the necessary Gradle dependencies.
4. Run the app on an Android emulator or a physical device.

## Technologies Used

- Kotlin
- Jetpack Compose
- Hilt (for Dependency Injection)
- SQLDelight (for local database)
- Timber (for logging)
- Android SDK

## Project Structure

The project follows a modular structure:

*   `app`: The main application module that brings together all other modules. It contains the UI (using Jetpack Compose) and handles user interaction.
*   `core`: Provides core functionalities and utilities that can be shared across different parts of the application.
*   `database`: Manages the local database using SQLDelight. It includes schema definitions and queries.
*   `ui-common`: Contains common UI components, themes, and resources used throughout the application's user interface.

## Contributing

Contributions are welcome! If you'd like to contribute to TSTrivia, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and ensure your code builds and any existing tests pass.
4. Submit a pull request with a clear description of your changes.

## License

This project is currently not licensed. Consider adding a license like the MIT License to define how others can use and contribute to the project.
