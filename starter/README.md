## Movie App

design link : https://www.figma.com/file/6qeZiGTdwhy3cwSbraA1Tb/Untitled?type=design&node-id=0-1&mode=design&t=NMQ3yZRq8nITQW9V-0
feature doc : https://docs.google.com/spreadsheets/d/1mCFnZJTNpAuplg8DevZcefJZYxm6N5z8gpcyM1MBey0/edit#gid=1232208841

This app demonstrates the following views and techniques:

* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects. 
* [Glide](https://bumptech.github.io/glide/) to load and cache images by URL.
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.
  
It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments

