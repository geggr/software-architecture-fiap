#!/bin/bash

#### TEXT FORMATING HELPERS
TC_RED=$(tput setaf 1)
TF_BOLD=$(tput bold)
TF_RESET=$(tput sgr0)

function log() {
  echo -e "${TC_RED}${TF_BOLD}[$(date '+%Y.%m.%d %H:%M':%S)]${TF_RESET} $*"
}

#### MINIKUBE HELPERS
MINIKUBE_RUNNING="0"
MINIKUBE_PROFILE="totem-express"
MINIKUBE_ADDONS=("metrics-server")
MINIKUBE_CLUSTER_IP=""

function mk() {
  minikube $@ -p $MINIKUBE_PROFILE
}

### K8S helpers
K8S_NAMESPACE="totem-express"

### APPLICATION HELPERS
RESOURCE_FILES=("totem-express-configmap.yaml"
  "totem-express-svc.yaml"
  "totem-express-secret.yaml"
  "totem-express-deployment.yaml"
  "totem-express-db-configmap.yaml"
  "totem-express-db-svc.yaml"
  "totem-express-db-secret.yaml"
  "totem-express-db-deployment.yaml"
  "totem-express-hpa.yaml")

function is_minikube_running() {
  log "Checando se minikube está rodando"
  if [[ $MINIKUBE_RUNNING == "1" ]]; then
    log "minikube está rodando"
    return 1
  fi
  minikube_status=$(mk status --format='{{.Host}}')
  if [[ "$minikube_status" =~ "Running" ]]; then
    MINIKUBE_RUNNING="1"
  else
    MINIKUBE_RUNNING="0"
  fi
}

function get_node_ip() {
  MINIKUBE_CLUSTER_IP=$(mk ip)
}

function te_start() {
  namespace=${1:-$K8S_NAMESPACE}
  log "Iniciando o minikube e aplicando os arquivos"
  is_minikube_running
  if [[ $MINIKUBE_RUNNING == "0" ]]; then
    log "Como o minikube não está rodando ou configurado vou rodar o setup"
    te_setup
  fi
  log "Criando namespace ${TF_BOLD}$namespace${TF_RESET}"
  kubectl create namespace $namespace --dry-run=client -o yaml | kubectl apply -f -
  log "Criando os recursos"
  for resource in "${RESOURCE_FILES[@]}"; do
    log "Aplicando arquivo ${TF_BOLD}${resource}${TF_RESET} no namespace ${TF_BOLD}$namespace${TF_RESET}"
    kubectl apply -n $namespace -f $resource
  done
  get_node_ip
  log "Para acessar o progama acesse http://${MINIKUBE_CLUSTER_IP}:30000/"
}

function te_stop() {
  log "Vou parar o minikube sem remover o aplicativo do cluster"
  is_minikube_running
  if [[ "$MINIKUBE_RUNNING" == "1" ]]; then
    mk stop
  fi
}

function te_purge() {
  log "Destruindo totem express e minikube"
  mk delete
}

function te_setup() {
  log "Configurando projeto e minikube, verificando se o minikube está rodando já"
  is_minikube_running
  if [[ $MINIKUBE_RUNNING == "1" ]]; then
    log "Minikube já está rodando com o profile ${MINIKUBE_PROFILE}"
  else
    log "vou iniciar minikube com os addons ${TF_BOLD}${MINIKUBE_ADDONS[*]}${TF_RESET} e profile ${TF_BOLD}${MINIKUBE_PROFILE}${TF_RESET}"
    mk start --addons=${MINIKUBE_ADDONS[*]}
  fi
  log "Pronto o cluster está pronto para rodar a aplicação"
  get_node_ip
  log "O ip do cluster é: ${TC_RED}${TF_BOLD}${MINIKUBE_CLUSTER_IP}${TF_RESET}"
}

function te_help() {
  log "Como usar ./totem-express-cli.sh <commando> [parâmetros], commandos:"
  log "\t${TF_BOLD}setup${TF_RESET}: apenas inicia o minikube e ativa os addons necessários para aplicação rodar"
  log "\t${TF_BOLD}start [namespace, padrão totem-express]${TF_RESET}: dá start na minikube se estiver parado e cria os recursos e retorna o ip do node para acessar"
  log "\t${TF_BOLD}stop [namespace, padrão totem-express]${TF_RESET}: para o minikube e consequentemente a aplicação"
  log "\t${TF_BOLD}purge${TF_RESET}: ${TF_BOLD}remove tudo${TF_RESET} inclusive o cluster do minikube"
  log "\t${TF_BOLD}copy_image${TF_RESET}: faz build e copia a imagem local para o minikube"
  log "Para funcionar corretamente precisa ter instalado o minikube, docker e kubectl"
}

subcommand=$1
case $subcommand in
"" | "-h" | "--help")
  te_help
  ;;
*)
  shift
  te_${subcommand} $@
  if [ $? = 127 ]; then
    log "Error: '$subcommand' is not a known subcommand." >&2
    te_help
    exit 1
  fi
  ;;
esac
