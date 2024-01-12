## The Backend

The backend of this project is a robust and scalable system built on the foundations of Spring Boot, leveraging the power of Java Persistence API (JPA) for efficient and seamless data management. Employing microservices architecture, the backend is designed to enhance modularity, maintainability, and scalability, allowing for independent development and deployment of various components.

By containerizing the Spring Backend and MySQL Server using Docker, the backend system is easily deployable in diverse environments. The use of a custom Docker image, defined in the Dockerfile within the current directory, ensures a tailored and optimized deployment process.

Maven serves as the project's build automation tool, streamlining dependency management and project structure. This cohesive integration of technologies empowers the backend to deliver high-performance, maintainable, and extensible services for the overall success of the application.

The API layer of this project embodies a streamlined communication interface, facilitating seamless interaction between the frontend and backend components. Leveraging the Spring Boot framework, API calls are efficiently handled, ensuring responsiveness and reliability.

Microservices architecture is employed to modularize the API calls, enabling independent development and scalability. Java Persistence API (JPA) is intricately woven into the API structure, facilitating the seamless management of data interactions.

Each API call is carefully crafted to align with RESTful principles, promoting a standardized and intuitive approach to communication. These calls are designed to support various functionalities, fostering a dynamic and extensible system.

Maven, as the build automation tool, further enhances the efficiency of the API layer, managing dependencies and project structure. The overall architecture, encompassing Spring Boot, JPA, microservices, and Maven, ensures that API calls are not only performant but also maintainable and adaptable to future enhancements.

If you wish to deploy this backend, keep in mind that it needs a containered MySQL Server and you need to update the IP of the MySQL Server in the application.properties file.

To build the image use this command:
`docker build -t <IMAGE_NAME> .`

To deploy the custom image you can use:
`docker run --name <CONTAINER_NAME> --net=bridge -p 8088:8088 <IMAGE_NAME>`

**Keep in mind**
Set up the MySQL Server first in order to get the IP using:
`docker network inspect bridge`
