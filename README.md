# Android native news application

This news app which fetches top headings utilizing news api.
<p>
  <img src="https://user-images.githubusercontent.com/57877668/167409897-4ff45b18-ae1f-4ca4-9593-47383319d60f.gif" width="250"/>
  <img src="https://user-images.githubusercontent.com/57877668/167409893-a0f1d336-df83-443a-8ced-5121fe52ce8c.gif" width="250"/>
  <img src="https://user-images.githubusercontent.com/57877668/167409883-849c9b8f-8956-46cf-828b-84e4c69d3a20.gif" width="250"/>
</p>

### Main application concepts
1. Unit tests for viewModels and separate classes are created.
2. Article list screen:</br>
* On app launch, data is being fetched while the loader is shown.
* After data is fetched, the loader is removed and list is displayed.
* Each list item comprises of title, short description(text is ellipsized if exceeds 3 lines), photo and icon(more).
* By clicking on the article the app navigates to article details' screen.
3. Article details screen
* This screen shows the image, author, publish date, title, content and link to the original article site.
* By clicking on the link button, app launches new activity and opens web browser.

### Project structure

```
.
├── di
│   └── ApiModule.kt
│
├── network
│   └── models
│   │   ├── Article.kt
│   │   └── Articles.kt
│   ├── NewsApi.kt  
│   ├── NewsInterceptor.kt
│   └── Repository.kt
│
├── presentation
│   └── articleDetails
│   │   ├── ArticleDetailsFragment.kt
│   │   └── ArticleDetailsViewModel.kt
│   ├── articleList  
│   │   ├── ArticleEvents.kt
│   │   ├── ArticleListAdapter.kt
│   │   ├── ArticleListFragment.kt
│   │   ├── ArticleListState.kt
│   │   └── ArticleListViewModel.kt
│   └── MainActivity.kt
│
└── NewsApplication.kt
```

### Technologies used:
1. MVVM architecture
2. Recycler view with diff utils to display a list.
3. Hilt for dependency injection
4. Glide to show images from URL.
5. Retrofit with gson converter to interact with the news API.
6. View binding.
7. Unit testing: mockito, turbine, coroutines testing.

### How to install and run app:
The only way to to launch this app is to clone this repo and build project using Android studio or from
<a href="https://developer.android.com/studio/build/building-cmdline">CMD</a>.
Happy coding with kotlin ;)
