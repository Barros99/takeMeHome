<div id="top"></div>
<br />

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#technologies-used">Technologies Used</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#api-documentation">API Documentation</a></li>
    <li><a href="#accessing-the-h2-database-interface">H2 Database Access</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

[![Take Me Home][product-screenshot]](https://github.com/Barros99/takemehome)

"Take Me Home," an adoption website for animals.

Users have the ability to register an animal and upload a photo. The system supports listing registered animals and changing their status between available and adopted.

The frontend was developed using React (with Vite) and runs on port 5173.

Frontend Access:
http://localhost:5173

The backend was developed in Java with Spring Boot and operates on port 8080.

Backend Access:
http://localhost:8080/api/animals

For easier deployment, Docker has been introduced. There is a section explaining how to run the application directly and easily.

The API documentation section contains links describing the endpoints and their HTTP methods in greater detail.

<p align="right">(<a href="#top">back to top</a>)</p>

## Technologies Used

These are some of the technologies used in this project:

- [Java](https://www.java.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [H2 Database](https://dbdb.io/db/h2/)
- [React](https://react.dev/)
- [Vite](https://vitejs.dev/)

<p align="right">(<a href="#top">back to top</a>)</p>

## Prerequisites

[ Note ] - To run the application, you must have [Docker](https://docs.docker.com/) installed on your machine.

Docker installation guide:

- https://docs.docker.com/engine/install/

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

Follow the steps below to run the application:

1. Clone the repository:
   ```sh
   git clone git@github.com:Barros99/takemehome.git
   ```
2. Navigate to the project's root directory:
   ```sh
   cd takeMehome
   ```
3. Run the following command to start the application:
   ```js
   docker-compose up -d
   ```
4. To access the frontend, copy and paste the address below into your browser:
   ```js
   http://localhost:5473
   ```
5. To access the API, copy and paste the address below into your browser:
   ```js
   http://localhost:8080/api/animals
   ```

[Back to Top](#top)

<!-- DOCUMENTATION -->

## API Documentation

The documentation can be accessed at the following address:

Standard format:

```js
  http://localhost:8080/swagger-ui-custom.html
```

JSON format:

```js
  http://localhost:8080/api-docs
```

[Back to Top](#top)

<!-- DATABASE -->

## Accessing the H2 Database Interface

To access the H2 in-memory database interface:

Use the following credentials:
Username: sa
Password: password
Fill in the JDBC URL field with: jdbc:h2:mem:pawsitive
Access the H2 console at

```js
 http://localhost:8080/h2-console
```

[Back to Top](#top)

<!-- LICENSE -->

## License

Distributed under the MIT License.

[Back to Top](#top)

<!-- CONTACT -->

## Contact

Project Link: [https://github.com/Barros99/takemehome](https://github.com/Barros99/takemehome)

Jean Barros - [LinkedIn Profile](https://www.linkedin.com/in/j3anbarros/)

<p align="right">(<a href="#top">back to top</a>)</p>

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/j3anbarros/
[product-screenshot]: /back/src/main/resources/assets/aw.png
