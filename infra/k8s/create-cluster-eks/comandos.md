$ terraform init
$ terraform apply

#Output:
cluster_endpoint = "https://ED58E6E33C42447303E75EF332BA95B5.gr7.us-east-1.eks.amazonaws.com"
cluster_name = "sevenfood-eks-PXg5kcxt"
cluster_security_group_id = "sg-0c5d772d25fca30c4"
region = "us-east-1"

$ aws eks update-kubeconfig --region us-east-1 --name sevenfood-eks-PXg5kcxt

# Para pergar o as variaves no output do terraform, melhor usar:
aws eks --region $(terraform output -raw region) update-kubeconfig --name $(terraform output -raw cluster_name)

$ kubectl get nodes

$ terraform destroy


#describe Cluster EKS
aws eks --region us-east-1 describe-cluster --name sevenfood --query cluster.status

curl -o aws-iam-authenticator https://amazon-eks.s3-us-west-2.amazonaws.com/1.11.5/2018-12-06/bin/linux/amd64/aws-iam-authenticator

chmod +x aws-iam-authenticator

mv aws-iam-authenticator ~/.local/bin


