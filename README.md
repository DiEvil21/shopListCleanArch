# Shopping list

```MVVM, Clean Architecture, RecylerView```

<img src="https://github.com/DiEvil21/shopListCleanArch/assets/55885322/2b38ec85-1179-4a68-a712-c5ac915c15ed" height="300"/></h1>

```Clean architecture``` - allows you to write easy-to-read, testable and extensible code. Its essence is to reduce code coherence by dividing the application into layers.
Three main layers of pure architecture: ```data, domain, presentation```.

The ```Domain``` layer is the most independent layer and represents the business logic of the application. Works with the repository interface.

```Data``` layer - layer for working with data. Provides a specific implementation of the repository. Depends on data sources. Knows about the existence of the domain layer.

```Presentation``` - is responsible for displaying data and interacting with the user. Knows about the domain layer, but should not know anything about the data layer.

```ViewModel``` - is part of the presentation layer, works with usecase.

<img src="https://github.com/DiEvil21/shopListCleanArch/assets/55885322/00682d37-8292-4d36-9c66-66bdc42f21ee" height="300"/></h1>
