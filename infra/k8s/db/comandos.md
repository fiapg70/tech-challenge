## Infra Postgres K8S
### Na Infra do PostgreSQL podemos usar o Kubernetes pelo Minikube:
#### Iniciar o minikube no Linux:
```sh
$ minikube start --vm-driver=virtualbox
```
#### Aplicar os serviços no K8S:
```sh
$ kubectl apply -f db-persistent-volume.yaml
$ kubectl apply -f db-volume-claim.yaml
$ kubectl apply -f db-configmap.yaml
$ kubectl apply -f db-deployment.yaml
$ kubectl apply -f db-service.yaml
$ kubectl apply -f db-endpoints.yaml
```
#### Verificar os serviços criados:
```sh
$ kubectl get all
$ kubectl get svc
```

#### Testar o PosgreSQL Local:
```sh
$ kubectl exec -it postgresdb-cb476c675-qcst8 --psql -h 192.168.59.100 -U admin --password -p 5432 sevenfood
```
#### Para excluir o Cluster
```sh
$ kubectl delete -f db-endpoints.yaml
$ kubectl delete -f db-service.yaml
$ kubectl delete -f db-deployment.yaml
$ kubectl delete -f db-configmap.yaml
$ kubectl delete -f db-volume-claim.yaml
$ kubectl delete -f db-persistent-volume.yaml
```
#### Para rodar o K8S Dashboard
```sh
$ minikube dashboard
```
#### Para escutar os serviços
```sh
$ kubectl get nodes -o wide
$ kubectl get svc -o wide
```
#### Para pegar o ip exposto:
```sh
$ minikube ip
 ex: 192.168.59.100
```
