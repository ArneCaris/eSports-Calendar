# Esport-Calendar RestAPI

Esport-Calendar RestAPI is a sub branch from the master to develop API functionality in the game calendar representation. It can show how this project consume API data to the need and feature of the project.

### Goals
  * Display matches by game

### Download & Setup

AndroidManifest.xml:
```xml

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

```

Gradle:
```gradle

android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
  implementation 'com.squareup.okhttp3:okhttp:3.14.0'
  implementation 'com.google.code.gson:gson:2.8.5'
}
```


### Documentation
  
1. Create fetchJson():
```java

    private void fetchJSON(){


        String game;  //game to fetch the match
        String token; //Token from pandaScore

        String url  = "https://api.pandascore.co/" + game + "/matches/upcoming?token=" + token;

        // Create request by Request of OkHttp
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        try {
            // Catch response
            Response response = client.newCall(request).execute();
            String body       =  response.body().string();

            //Convert response data into Java Object and store in an array
            Gson gson         = new GsonBuilder().create();
            Match[] matches = gson.fromJson(body, Match[].class);
        }

        // Catch errors
        catch (IOException e) {
            Log.d(TAG, "fetchJSON: There is errors" + e);
            e.printStackTrace();
        }
    }

```

Add fetchJSON() to onCreate() life cycle method of the activity


### License

The MIT License