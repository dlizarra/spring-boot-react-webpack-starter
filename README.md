# Spring Boot and React starter app

> Starter webapp using Spring Boot on the backend and React on the frontend, with 
Maven and Webpack as build tools, with hot reloading on both sides and with no xml configuration.

## Quickstart
To run the app you just need to:

    git clone https://github.com/dlizarra/spring-boot-react-webpack-starter.git ./starter
    cd starter
    mvn spring-boot:run

Now you can:

    # Visit the homepage
    http://localhost:8080
    
    # Go to the sample REST endpoint
    http://localhost:8080/users
    
    # Login to the H2 console (JDBC URL: 'jdbc:h2:mem:embedded', user = 'h2')
    http://localhost:8080/h2-console

## Start developing
The Java code is available at `src/main/java` as usual, and the frontend files are in 
`src/main/frontend`.

### Running the backend
Run `StarterMain` class from your IDE.

### Running the frontend
Go to `src/main/frontend` and run `npm start`.

Now we should work with `localhost:9090` (this is where we'll see our live changes reflected)
 instead of `localhost:8080`.

### Hot reloading
In the **backend** we make use of Spring DevTools to enable hot reloading, 
so every time we make a change in our files an application restart will
be triggered automatically.

Keep in mind that Spring DevTools automatic restart only works if we run the 
application by running the main method in `StarterMain`, and not if for example we run 
the app with maven with `mvn spring-boot:run`.

In the **frontend** we use Webpack Dev Server hot module replacement 
through the npm script `start`. Once the script is running the Dev Server will be 
watching for any changes on our frontend files.

This way we can be really productive since we don't have to worry about recompiling and deploying
our server or client side code every time we make changes.

### Other ways of running the app
#### Run everything from Maven

    mvn generate-resources spring-boot:run

The Maven goal `generate-resources` will execute the frontend-maven-plugin to install Node
and Npm the first time, run npm install to download all the libraries  that are not 
present already and tell webpack to generate our `bundle.js`. It's the equivalent of running `webpack` 
or `npm start` on a terminal.

#### Run Maven and Webpack separately

    mvn spring-boot:run
In a second terminal:
    
    cd src/main/frontend
    webpack

### Profiles

The project comes prepared for being used in three different environments plus 
another one for testing. We use Spring Profiles in combination with Boot feature for 
loading properties files by naming convention (application-*\<profile name\>*.properties).

You can find the profile constants in 
[StarterProfiles](spring-boot-react-webpack-starter/src/main/java/com/dlizarra/starter/StarterProfiles.java) 
and the properties files in `src/main/resources`.

### Database
The database connections are configured in 
[DatabaseConfig](src/main/java/com/dlizarra/starter/DatabaseConfig.jav)
where we can find a working H2 embedded database 

## Tech stack and libraries
### Backend
- Spring



---
