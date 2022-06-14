## Library Application
This is a test application which has android application and android library. 
Library provides the average of numbers to the client applications.

### Library features: 
1. Fetches the data from server and provides to client application.
2. Does the calucations and provides average of numbers to client application.
3. Provides the feature of adding and deleting the numbers and maintains the distinct numbers.
4. Provides offline support to client applications so that users entered values are not ignored on next launch.

### Application features: 
1. Displays the list of numbers provided by SDK.
2. Provides the UI for adding and deleting the numbers and does number validations.
3. Displays the average of all the numbers, provided by SDK.

<br><br>
### Application Architecture
Application is based on MVVM + CLEAN architure.<br>
MVVM provides better handling of configurations and CLEAN provides layered architecture to manage code in a better way.

![Architecture Diagram](https://uploads.toptal.io/blog/image/127608/toptal-blog-image-1543413671794-80993a19fea97477524763c908b50a7a.png)

<br><br>
## Technical Details
1. It is Using Androidx and Jetpacks.
2. It is Using Retrofit for network communications.
3. It is Using Viemodels and Flow.
4. It is Using Hilt for dependency injection.
5. It is Using Navigation Architecture.
6. It is Using data binding.
7. It is Using Espresso for UI test cases. Mockito and Junit for non ui testing.
8. It is using Mock web server to simulate server response and coroutine based test techniques.
<br>
This app is focused for phone and supports portrait and landscape modes

<br><br>
### How to use this app
In Android studio, create a new application from version control by using following url: <br>
https://github.com/sharadshrivastava/NumberLibrary.git
