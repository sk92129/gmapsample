# Google Maps API Key Configuration

This project is configured to automatically read the Google Maps API key from `lib/dev.json` and make it available to both iOS and Android builds.

## Configuration Files

### 1. `lib/dev.json`
Contains the Google Maps API key:
```json
{
  "GMAP_API_KEY": "YOUR_API_KEY_HERE"
}
```

### 2. `lib/config/app_config.dart`
Flutter configuration service that reads the API key from `dev.json` and provides it to the app.

### 3. Android Configuration

#### `android/app/build.gradle.kts`
- Added a function `getApiKey()` that reads the API key from `dev.json`
- Added `buildConfigField` and `manifestPlaceholders` to make the API key available to the Android manifest

#### `android/app/src/main/AndroidManifest.xml`
- Already configured with the Google Maps API key placeholder: `${GMA_API_KEY}`

### 4. iOS Configuration

#### `ios/scripts/read_api_key.sh`
- Shell script that reads the API key from `dev.json` using Python
- Made executable with `chmod +x`

#### `ios/Runner/Info.plist`
- Added `GMA_API_KEY` key that references the build setting

#### `ios/Runner.xcodeproj/project.pbxproj`
- Added `GMA_API_KEY` build setting to Debug, Release, and Profile configurations
- Uses the shell script to read the API key during build

## How It Works

1. **Flutter App**: The `AppConfig` class reads the API key from `dev.json` at runtime
2. **Android Build**: The Gradle build script reads `dev.json` and injects the API key into the manifest
3. **iOS Build**: The Xcode build process runs the shell script to read `dev.json` and sets the environment variable

## Usage

The API key is automatically available in:
- Flutter code via `AppConfig.gmapApiKey`
- Android manifest for Google Maps plugin
- iOS Info.plist for Google Maps plugin

## Security Notes

- `dev.json` should be added to `.gitignore` to prevent committing API keys
- For production, consider using environment variables or secure key management
- The API key in `dev.json` is for development purposes only

## Testing

The app displays "API Key loaded: Yes/No" at the bottom of the screen to confirm the key was loaded successfully.
