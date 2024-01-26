## Infra Postgres K8S
### Na Infra do PostgreSQL podemos usar o Kubernetes pelo Minikube:
#### Iniciar o minikube no Linux:
```sh
$ minikube start --vm-driver=virtualbox
```
#### Aplicar os serviços no K8S:
```sh
$ kubectl apply -f api-configmap.yaml
$ kubectl apply -f api-deployment.yaml
$ kubectl apply -f api-svc.yaml
```
#### Verificar os serviços criados:
```sh
$ kubectl get all
```
#### Para excluir o Cluster
```sh
$ kubectl delete -f api-svc.yaml
$ kubectl delete -f api-deployment.yaml
$ kubectl delete -f api-configmap.yaml
$ kubectl delete all --all
```
#### Para excluir o Cluster
```sh
$ minikube dashboard
```

#### Para ver os logs dos podes
```sh
kubectl logs <<PODNAME>>
```

#### Para escutar os serviços
```sh
kubectl get nodes -o wide
```

#### Rodar o serviço
Para rodar a IP tem que pegar o internal ip ou o ip externo e somar com a url do swagger ou do actuactor
http://192.168.59.100:30091/api/swagger-ui/index.html
http://192.168.59.100:30091/actuactor/health