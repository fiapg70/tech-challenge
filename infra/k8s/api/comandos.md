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

===
  #env:
          #  - name: DB_HOST
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: db_host
          #  - name: DB_USERNAME
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: db_username
          #  - name: DB_PASSWORD
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: db_password
          #  - name: DB_NAME
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: db_name
          #  - name: JWT_SECRET
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: jwt_secret
          #  - name: USER_POOL_ID
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: user_pool_id
          #  - name: ADMIN_POOL_ID
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: admin_pool_id
          #  - name: POOL_CLIENT_CLIENT_ID
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: pool_client_client_id
          #  - name: POOL_ADMIN_CLIENT_ID
          #    valueFrom:
          #      secretKeyRef:
          #        name: fiap-tech-secrets
          #        key: pool_admin_client_id