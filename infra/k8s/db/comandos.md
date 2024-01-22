#Iniciar o minikube no Linux:

$ minikube start --vm-driver=virtualbox

$ kubectl apply -f db-persistent-volume.yaml
$ kubectl apply -f db-volume-claim.yaml
$ kubectl apply -f db-configmap.yaml
$ kubectl apply -f db-deployment.yaml
$ kubectl apply -f db-service.yaml

$ kubectl get all

$ minikube dashboard

$ kubectl exec -it postgresdb-7b475497d6-jd5lj -- psql -h localhost -U admin --password -p 5432 sevenfood

# Para excluir o Cluster
$ kubectl delete -f db-service.yaml
$ kubectl delete -f db-deployment.yaml
$ kubectl delete -f db-configmap.yaml
$ kubectl delete -f db-volume-claim.yaml
$ kubectl delete -f db-persistent-volume.yaml