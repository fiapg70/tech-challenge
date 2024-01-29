docker build . -t rogeriofontes/sevenfood-api:1.0.3
docker image ls

docker login
docker push rogeriofontes/sevenfood-api:1.0.3

docker-compose logs -f --tail 1000