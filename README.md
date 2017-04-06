# Spring Boot and React starter app

> Starter webapp using Spring Boot on the backend and React on the frontend, with 
Maven and Webpack as build tools, hot reloading on both sides and without xml configuration.

## Quickstart
To run the app you just need to:

    git clone https://github.com/dlizarra/spring-boot-react-webpack-starter.git ./starter
    cd starter
    mvn spring-boot:run

To check everything is running you can:

    # Visit the homepage
    http://localhost:8080
    
    # Go to the sample REST endpoint
    http://localhost:8080/api/users
    
    # Login to the H2 console (JDBC URL: 'jdbc:h2:mem:embedded', user = 'h2')
    http://localhost:8080/h2-console

## Start developing
The Java code is available at `src/main/java` as usual, and the frontend files are in 
`src/main/frontend`.

### Running the backend
Run `StarterMain` class from your IDE.

### Running the frontend
Go to `src/main/frontend` and run `npm start`. (Run `npm install` before that if it's the first time)

Now we should work with `localhost:9090` (this is where we'll see our live changes reflected)
 instead of `localhost:8080`.

### Hot reloading
In the **backend** we make use of Spring DevTools to enable hot reloading, 
so every time we make a change in our files an application restart will
be triggered automatically.

Keep in mind that Spring DevTools automatic restart only works if we run the 
application by running the main method in our app, and not if for example we run 
the app with maven with `mvn spring-boot:run`.

In the **frontend** we use Webpack Dev Server hot module replacement 
through the npm script `start`. Once the script is running the Dev Server will be 
watching for any changes on our frontend files.

This way we can be really productive since we don't have to worry about recompiling and deploying
our server or client side code every time we make changes.


### Profiles

The project comes prepared for being used in three different environments plus 
another one for testing. We use Spring Profiles in combination with Boot feature for 
loading properties files by naming convention (application-*\<profile name\>*.properties).

You can find the profile constants in 
[StarterProfiles](src/main/java/com/dlizarra/starter/StarterProfiles.java) 
and the properties files in `src/main/resources`.

### Database
The database connections are configured in 
[DatabaseConfig](src/main/java/com/dlizarra/starter/DatabaseConfig.java)
where we can find a working H2 embedded database connection for the default profile, and the staging and production configurations examples for working with an external database.

### Repository layer
The project includes three base data repositories:

- [ReadOnlyRepository](src/main/java/com/dlizarra/starter/support/jpa/ReadOnlyRepository.java): We can use this base repository when we want to make sure the application doesn't insert or update that type of entity, as it just exposes a set of methods to read entities.
- [CustomCrudRepository](src/main/java/com/dlizarra/starter/support/jpa/CustomCrudRepository.java): It's the same as the `CrudRepository` that Spring Data provides, but the `findOne`method in the custom version returns a Java 8 `Optional<T>` object instead of `<T>`. It's just a small difference but it avoids having to override the `findOne` method in every repository to make it return an `Optional` object. This repository is intended to be used when we don't need paging or sorting capabilities for that entity.
- [CustomJpaRepository](src/main/java/com/dlizarra/starter/support/jpa/CustomJpaRepository.java): Again, it's there to provide the same funcionality as the Spring `JpaRepository` but returning `Optional<T>`. We can extend this base repository if we want CRUD operations plus paging and sorting capabilities.

### Security
All the boilerplate for the initial Spring Security configuration is already created. These are they key classes:

- [User](src/main/java/com/dlizarra/starter/user/User.java), [Role](src/main/java/com/dlizarra/starter/role/Role.java) and  [RoleName](src/main/java/com/dlizarra/starter/role/RoleName.java) which are populated by [data.sql](src/main/resources/data.sql) file for the default profile only.
- [CustomUserDetails](src/main/java/com/dlizarra/starter/support/security/CustomUserDetails.java)
- [CustomUserDetailsService](src/main/java/com/dlizarra/starter/support/security/CustomUserDetailsService.java)
- [SecurityConfig](src/main/java/com/dlizarra/starter/SecurityConfig.java) with just very basic security rules.

### DTO-Entity mapping
The project includes Orika and it already has a class, [OrikaBeanMapper](src/main/java/com/dlizarra/starter/support/orika/OrikaBeanMapper.java), ready to be injected anywhere and be used to do any mapping. It will also scan the project on startup searching for custom mappers and components.

You can see how to use it in [UserServiceImpl](src/main/java/com/dlizarra/starter/user/UserServiceImpl.java) or in this sample [project](https://github.com/dlizarra/orika-spring-integration).

This, along with Lombok annotations for auto-generating getters, setters, toString methods and such, allows us to have much cleaner Entities and DTOs classes.

### Unit and integration testing
For **unit testing** we included Spring Test, JUnit, Mockito and AssertJ as well as an [AbstractUnitTest](src/test/java/com/dlizarra/starter/support/AbstractUnitTest.java) class that we can extend to include the boilerplate annotations and configuration for every test. [UserServiceTest](src/test/java/com/dlizarra/starter/user/UserServiceTest.java) can serve as an example.

To create integration tests we can extend [AbstractIntegrationTest](src/test/java/com/dlizarra/starter/support/AbstractIntegrationTest.java) and make use of Spring `@sql` annotation to run a databse script before every test, just like it's done in [UserRepositoryTest](src/test/java/com/dlizarra/starter/user/UserRepositoryTest.java).

### Code coverage
The project is also ready to use Cobertura as a code coverage utility and Coveralls to show a nice graphical representation of the results, get a badge with the results, etc. 

The only thing you need to do is to create an account in [Coveralls.io](http://coveralls.io) and add your repo token key [here](pom.xml#L134) in the pom.xml.

And if you want to use different tools you just need to remove the plugins from the pom.

### Linting
We added ESLint preconfigured with Airbnb rules, which you can override and extend in [.eslintrc.js](src/main/frontend/.eslintrc.js) file. To lint the code you can run `npm run eslint` or configure your IDE/text editor to do so.

### Continuous integration and deployment
A [travis.yml](.travis.yml) file is included with a minimal configuration just to use jdk 8, trigger the code analysis tool and deploy the app to Heroku using the `api_key` in the file. 

We also included a Heroku [Procfile](Procfile) which declares the `web` process type and the java command to run our app and specifies which Spring Profile we want to use.

### Other ways to run the app
#### Run everything from Maven

    mvn generate-resources spring-boot:run

The Maven goal `generate-resources` will execute the frontend-maven-plugin to install Node
and Npm the first time, run npm install to download all the libraries  that are not 
present already and tell webpack to generate our `bundle.js`. It's the equivalent of running `npm run build` or `npm start` on a terminal.

#### Run Maven and Webpack separately (no hot-reloading)

    mvn spring-boot:run
In a second terminal:
    
    cd src/main/frontend
    npm run build

## Tech stack and libraries
### Backend
- [Spring Boot](http://projects.spring.io/spring-boot/)
- [Spring MVC](http://docs.spring.io/autorepo/docs/spring/3.2.x/spring-framework-reference/html/mvc.html)
- [Spring Data](http://projects.spring.io/spring-data/)
- [Spring Security](http://projects.spring.io/spring-security/)
- [Spring Test](http://docs.spring.io/autorepo/docs/spring-framework/3.2.x/spring-framework-reference/html/testing.html)
- [JUnit](http://junit.org/)
- [Mockito](http://mockito.org/)
- [AssertJ](http://joel-costigliola.github.io/assertj/)
- [Lombok](https://projectlombok.org/)
- [Orika](http://orika-mapper.github.io/orika-docs/)
- [Maven](https://maven.apache.org/)

### Frontend
- [Node](https://nodejs.org/en/)
- [React](https://facebook.github.io/react/)
- [Redux](http://redux.js.org/)
- [Webpack](https://webpack.github.io/)
- [Axios](https://github.com/mzabriskie/axios)
- [Babel](https://babeljs.io/)
- [ES6](http://www.ecma-international.org/ecma-262/6.0/)
- [ESLint](http://eslint.org/)

---
