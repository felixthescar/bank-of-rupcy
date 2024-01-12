docker network create container-bridge --driver bridge

docker run --name sql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql
docker run --name sql-server --network=bridge -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql

docker run --name java-server --network=host -p 8088:8088 bor-app
docker run --name java-server --net=bridge -p 8088:8088 bor-app

docker network connect container-bridge sql-server2
docker network connect container-bridge java-server2