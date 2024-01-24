docker build . -t rogeriofontes/sevenfood-api:1.0.1
docker image ls

docker login
docker push rogeriofontes/sevenfood-api:1.0.1