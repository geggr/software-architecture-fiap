#!/bin/bash

apply_all() {
  echo "Aplicando configurações..."
  kubectl apply -f totem-express-configmap.yaml
  kubectl apply -f totem-express-svc.yaml
  kubectl apply -f totem-express-secret.yaml
  kubectl apply -f totem-express-deployment.yaml
  kubectl apply -f totem-express-db-configmap.yaml
  kubectl apply -f totem-express-db-svc.yaml
  kubectl apply -f totem-express-db-secret.yaml
  kubectl apply -f totem-express-db-deployment.yaml
  kubectl apply -f totem-express-hpa.yaml
  echo "Configurações aplicadas com sucesso."
}

delete_all() {
  echo "Deletando todos os recursos..."
  kubectl delete -f totem-express-deployment.yaml
  kubectl delete -f totem-express-svc.yaml
  kubectl delete -f totem-express-secret.yaml
  kubectl delete -f totem-express-configmap.yaml
  kubectl delete -f totem-express-db-deployment.yaml
  kubectl delete -f totem-express-db-svc.yaml
  kubectl delete -f totem-express-db-configmap.yaml
  kubectl delete -f totem-express-db-secret.yaml
  kubectl delete -f totem-express-hpa.yaml
  echo "Todos os recursos foram deletados."
}

if [ "$1" == "delete" ]; then
  delete_all
else
  apply_all
fi
