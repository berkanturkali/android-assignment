
<a href="https://play.google.com/store/apps/details?id=com.dogan.arabam&hl=tr"><img src="https://arbimg1.mncdn.com/assets/dist/img/tek-tur-large.gif"/>

|                                                                  Home                                                                  |                                                                  Details                                                                  |       
|:--------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="screenshots/home_fragment.gif" alt="home" width="250"/> <img src="screenshots/home_fragment_fab.gif" alt="fab" width="250"/> |                                  <img src="screenshots/details_fragment.gif" alt="details" width="250"/>                                  |

|                                  Favorites                                  |                                                              Sort & Filter                                                              |
|:---------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|  
| <img src="screenshots/favorites_fragment.gif" alt="favorites" width="250"/> | <img src="screenshots/sort_fragment.gif" alt="sort" width="250"/> <img src="screenshots/filter_by_model.gif" alt="filter" width="250"/> |

|                        Advert Images                         |      
|:------------------------------------------------------------:|
 | <img src="screenshots/slider.gif" alt="slider" width="250"/> |


## Features
* Kotlin Coroutines & Flow
* Android Architecture Components
* Multi-module
* XML & Compose
* Kotlin DSL

## Libraries

* [Jetpack Compose](https://developer.android.com/develop/ui/compose/setup)
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle)
* [Dagger Hilt](https://dagger.dev/hilt/)
* [Retrofit](https://square.github.io/retrofit/)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Room](https://developer.android.com/training/data-storage/room)
* [Jetpack Datastore](https://developer.android.com/topic/libraries/architecture/datastore)
* [Timber](https://github.com/JakeWharton/timber)
* [Glide](https://github.com/bumptech/glide)
* [Coil](https://coil-kt.github.io/coil/)

## Module Design

| Module name        | Type                 | Description                                                      |
| -------------      | -------------        | -------------                                                    |
| [app](/app/)       | Android Application  | MainActivity, ArabamApp class also Navigation setup.                |
| [core](/core/)     | Android Library | Cache-related components and DI modules.                                |
| [common](/common/)     | Android Library | Common components like resources, views, base classes                               |
| [libraries:remote](/libraries/remote/)   | Java/Kotlin Library  | Remote-related Components            |
| [libraries:data](/libraries/data/)     | Java/Kotlin Library      | Data-related components.                                      |
| [libraries:database](/libraries/database/) |Java/Kotlin Library    | Room-related components.                           |
| [libraries:domain](/libraries/domain/) | Java/Kotlin Library       | Domain-related components.                         |
| [listing](/listing/) | Android Library   | UI components for the Listing/Home screen.                         |
| [details](/details/) | Android Library      | UI components for the Details screen.                                              |
| [favorites](/favorites/) | Android Library      | UI components for the Favorites screen.                                              |

## API

### arabam.com SandBox API ###

* Web Postman -> https://www.postman.com/yusufcakmak/workspace/arabam-assigment/overview
* Postman Collection -> https://www.getpostman.com/collections/d0c83044d06639384b1b
* Swagger -> http://sandbox.arabamd.com/swagger/index.html