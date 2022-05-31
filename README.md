# todo example project

This project is the result of following the steps described in the [How to use Quarkus with the Service Binding Operator](https://developers.redhat.com/articles/2021/12/22/how-use-quarkus-service-binding-operator). This project is using the [automatic service binding](https://quarkus.io/guides/deploying-to-kubernetes#automatic-service-binding) feature provided by the `quarkus-kubernetes-service-binding` extension.

Other useful links:
- [PGO, the Postgres Operator](https://access.crunchydata.com/documentation/postgres-operator/5.1.1/quickstart/)
- [SBO, the Service Binding Operator](https://github.com/redhat-developer/service-binding-operator) and the [official documentation](https://redhat-developer.github.io/service-binding-operator/userguide/intro.html)

### Create a new kind cluster

```shell
cd infra
./setup-env.sh
```

### Install the Service Binding Operator
```shell
kubectl create -f https://operatorhub.io/install/service-binding-operator.yaml
```

### Install the Postgres Operator
From [PGO Quickstart site](https://access.crunchydata.com/documentation/postgres-operator/5.1.1/quickstart/)

```shell
YOUR_GITHUB_UN="<your GitHub username>"
git clone --depth 1 "git@github.com:${YOUR_GITHUB_UN}/postgres-operator-examples.git"
cd postgres-operator-examples
```

This  steps:
```shell
kubectl apply -k kustomize/install/namespace
kubectl apply --server-side -k kustomize/install/default
kubectl config set-context --current --namespace=postgres-operator
kubectl get pods
```

#### Create a Postgres Cluster

```shell
kubectl apply -k kustomize/postgres
```

### Build the container image
```shell
mvn clean install -Dquarkus.container-image.build=true -DskipTests
```

### Load the Docker image to the cluster
```shell
kind load docker-image amunozhe/todo-example:1.0.0-SNAPSHOT
```

### Deploy the application
```shell
kubectl apply -f target/kubernetes/kubernetes.yml
```
or

```shell
mvn clean install -Dquarkus.kubernetes.deploy=true -DskipTests
```

### Verify the installation
```shell
kubectl port-forward service/todo-example 8080:80
```


## Running locally in dev mode

Run:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 \
    --name postgres-quarkus-rest-http-crud -e POSTGRES_USER=restcrud \
    -e POSTGRES_PASSWORD=restcrud -e POSTGRES_DB=rest-crud \
    -p 5432:5432 postgres:13.1
```

```bash
cd quarkus-todo
mvn quarkus:dev
```