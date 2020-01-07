# Android App: Using Java

## Repository for my Whats The Weather app

![Whats The Weather](whats-the-weather.gif "Whats The Weather")

Find out how to build a simple Whats The Weather app. Author Melvin Kisten tackles AsyncTask, JSONArray, InputStream, HttpURLConnection classes and APIs. 

## Instructions
1. Make sure you have these installed
	- [Android Studio](https://developer.android.com/studio#downloads "Android Studio")

2. Clone this repository into your local machine using the terminal (mac) or [Gitbash (PC)](https://git-scm.com/download/win "Gitbash (PC)")
	
	`> git clone https://github.com/iammelvink/whats-the-weather.git`
3. Vist [Open Weather Map](https://openweathermap.org/ "Open Weather Map") and [Sign Up](https://home.openweathermap.org/users/sign_up "Sign Up") or [Sign In](https://home.openweathermap.org/users/sign_in "Sign In") if you already have an account 

4. Copy your API key from [Open Weather Map](https://openweathermap.org/ "Open Weather Map") and paste at file : 
	`app\src\main\java\com\iammelvink\whatstheweather\MainActivity.java` at the line where you see this `YOUR_API_KEY`:

	```java
		task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + searchCity + "&APPID=YOUR_API_KEY").get();
	```
5. Run Android Studio and choose Open an existing Android Sudio project
6. Press play button to run the app 

## More Stuff
Check out some of my other stuff on [iammelvink](https://iammelvink.github.io/ "iammelvink Portfolio Website"). Follow me on [Twitter](https://twitter.com/iammelvink "iammelvink") and [Instagram](https://www.instagram.com/iammelvink "iammelvink"), or check out my [YouTube channel](https://www.youtube.com/channel/UCwMGEkyU2QOqEEKJ1E5pe7w "WiiLearnTech YouTube").
