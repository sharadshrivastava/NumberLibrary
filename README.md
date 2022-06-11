Library Used:
1. Using Androidx and Jetpacks.
2. Using Retrofit for network communications.
3. Using Viemodels and LiveData.
4. Using Glide for image loading.
5. Using Hilt for dependency injection.
6. Using Navigation Architecture.
7. Using data binding.
8. Using Espresso for UI test cases. Mockito and Junit for non ui testing.
9. This app is focused for phone and supports portrait and landscape modes.

Architecture:
Used MVVM+CLEAN architecture.
MVVM is helpful for configuration changes like device rotation, font and language changes.
CLEAN architecture gives domain layer to write business use cases.
Both together provide layered architecture with Android's configuration handling support.

Debug:
If you get 'unknownHostException' please cold boot your emulator.
It happens only on emulator due to 'googleapis.com'
Android Studio -> Tools -> AVD Manager -> Emulator Window -> Actions -> Cold Boot Now