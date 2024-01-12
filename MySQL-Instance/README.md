## MySQL/MariaDB server

This is just a ready to deploy image from [DockerHub](https://hub.docker.com/).

To deploy this container you can use the following command:
`docker run --name sql-server --network=bridge -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql`