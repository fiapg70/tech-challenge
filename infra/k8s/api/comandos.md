#Iniciar o minikube no Linux:

$ minikube start --vm-driver=virtualbox

$ kubectl apply -f api-configmap.yaml
$ kubectl apply -f api-deployment.yaml
$ kubectl apply -f api-svc.yaml

$ kubectl get all

# Para excluir o Cluster
$ kubectl delete -f api-svc.yaml
$ kubectl delete -f api-deployment.yaml
$ kubectl delete -f api-configmap.yaml

$ minikube dashboard

kubectl logs PODNAME

kubectl delete all --all

kubectl get nodes -o wide

-> INTERNAL-IP 
http://192.168.59.100:30091/api/swagger-ui/index.html
