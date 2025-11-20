# Тестирование в кластере k8s

```shell
$ kind create cluster --config kind.yml

$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

$ echo "127.0.0.1    echo-client.local" | sudo tee -a /etc/hosts

$ helm install echo-server romanow/java-service --values=server.yml
$ helm install echo-client romanow/java-service --values=client.yml

$ helm scale --replicas=3 deployment/echo-server
```
