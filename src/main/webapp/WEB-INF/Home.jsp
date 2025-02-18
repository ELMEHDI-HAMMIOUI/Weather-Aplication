<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/weather_logo.png" type="image/x-icon">
	<title>Weather</title>
	<style>
		body {
		  	display: flex;
		  	justify-content: center;
		  	align-items: center;
		  	height: 100vh;
			background-image: url('${pageContext.request.contextPath}/img/weatherBG.jpg ');
			background-size: cover;
			background-repeat: no-repeat;
			background-position: center;
		}
	</style>
</head>
<body>
<div class="container">
   <div class="search-bar">
        <form method="POST">
            <input type="text" name="city" placeholder="City ..." required>
            <input type="submit" value="Search">
        </form>
    </div>

    <c:if test="${not empty weatherData}">
 <div class="weather-card">
    <h2 class="city">${weatherData.city}, ${weatherData.country}</h2>
    <p class="temperature">${weatherData.temp}°C</p>
    <p class="condition">${weatherData.condition}</p> <!-- Dynamic weather condition -->
    

    <div class="weather-details">
        <p>Humidity: <span class="humidity">${weatherData.humidity}%</span></p>
        <p>Wind Speed: <span class="wind">${weatherData.wind_speed} m/s</span></p>
        <p>Pressure: <span class="pressure">${weatherData.pressure} hPa</span></p> <!-- Atmospheric pressure -->
        <p>Feels Like: <span class="feels-like">${weatherData.feels}°C</span></p> <!-- Feels like temperature -->
        <p>Max Temp: <span class="max-temp">${weatherData.temp_max}°C</span></p> <!-- Maximum temperature -->
        <p>Min Temp: <span class="min-temp">${weatherData.temp_min}°C</span></p> <!-- Minimum temperature -->
        <p>Wind Direction: <span class="wind-dir">${weatherData.wind_deg}°</span></p> <!-- Wind direction -->
    </div>
	</div>

    </c:if>

    <c:if test="${empty weatherData}">
        <p>No weather data available for the specified city.</p>
    </c:if>
</div>
</body>
</html>


