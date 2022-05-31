#!/bin/sh

kind delete cluster
docker rm -f $(docker ps -aq)
./kind-reg-ingress.sh
#kubectl create namespace sbodemo
#kubectl config set-context --current --namespace=sbodemo

# install OLM
curl -L https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.21.2/install.sh -o install.sh
chmod +x install.sh
./install.sh v0.21.2


