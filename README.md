<!-- ABOUT THE PROJECT -->
## OMDB_MVVM
<p float="left">
  <img src="https://user-images.githubusercontent.com/18207490/159183665-378c141d-c92d-4829-97cb-811eb0df5bd0.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/159183670-f04081cd-7bb0-4a72-b5da-3ab11de0ddfa.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/159183675-5171f7d6-53a7-4ea8-82f9-5be1b8a138ab.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/159183678-3bfb6b59-8a95-49eb-91dd-50d0fe4bc19c.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/159183681-b7d12199-9148-4fbb-91fd-7f8c97a0f669.png" height="300">
</p>

This application is written using the OMDB API. The application, MVVM, Coroutines, Hilt, Navigation component features were used.

 * Architecture is written in MVVM. 
 * Help was obtained with data transfer with SharedViewModel. 
 * Asynchronous transactions were made with Coroutines. 
 * StateFlow was used to control the values returned in the Retrofit and to perform operations according to the returned values.
 * Picasso is used to display the pictures.
 * Hilt is used for Dependency Injection.

### Built With

Libraries used in the application.

* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [SharedViewModel](https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel#1)
* [Retrofit](https://square.github.io/retrofit/)
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
* [Picasso](https://square.github.io/picasso/)

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Get a free API Key at [https://www.omdbapi.com](https://www.omdbapi.com/apikey.aspx)
2. Enter your API in `build.gradle`
   ```js
   buildConfigField 'String', 'API_KEY', '"ENTER_YOUR_OMDB_API_HERE"'
   ```
