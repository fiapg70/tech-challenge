#Iniciar o minikube no Linux:

$ minikube start --vm-driver=virtualbox

$ kubectl apply -f db-persistent-volume.yaml
$ kubectl apply -f db-volume-claim.yaml
$ kubectl apply -f db-configmap.yaml
$ kubectl apply -f db-deployment.yaml
$ kubectl apply -f db-service.yaml
$ kubectl apply -f db-endpoints.yaml

$ kubectl get all
$ kubectl get svc

$ kubectl exec -it postgresdb-cb476c675-qcst8 --psql -h 192.168.59.100 -U admin --password -p 5432 sevenfood
$ 

# Para excluir o Cluster
$ kubectl delete -f db-endpoints.yaml
$ kubectl delete -f db-service.yaml
$ kubectl delete -f db-deployment.yaml
$ kubectl delete -f db-configmap.yaml
$ kubectl delete -f db-volume-claim.yaml
$ kubectl delete -f db-persistent-volume.yaml

$ minikube dashboard

$ kubectl get nodes -o wide

minikube ip
192.168.59.100

kubectl get svc -o wide