## Infra EKS com Dashboard
### Na Infra para configuramos o EKS utilizamos o Dashboard para vermos as informações criadas
#### Tutorial para subir o Kubernetes Dashboard
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/#deploying-the-dashboard-ui
#### Iniciar o gerenciar o terraforme para eks
```sh
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
```

#### Tutorial para configurar o usuario para acessar o Kubernetes Dashboard
https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md
#### Iniciar o gerenciar o terraforme para eks
```sh
kubectl proxy
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/.
```
