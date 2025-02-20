# Define paths
backend_path = "C:/adesso/demo/"
custom_values_path = "C:/adesso/demo/my-backend-chart/values.yaml"

# Load the Helm remote extension
load('ext://helm_remote', 'helm_remote')

allow_k8s_contexts('kind-kind')
namespace = "dev"

# Step 1: Rebuild the jar when source changes.
local_resource(
    "build-backend",
    "mvn clean install -Dspring.profiles.active=docker,seeding",  # Activating docker and seeding profiles
    deps=[backend_path + "src/"],
)

# Apply Kubernetes manifests for backend app
local("podman build -t localhost/backend-app .")
local("podman image save -o backend1.tar localhost/backend-app")
local("kind load image-archive backend1.tar --name kind-cluster")
local("kubectl apply -f kube/base/backend-deployment.yaml")
local("kubectl rollout restart deployment backend-app")

# Define the Helm chart deployment using Helm commands
local("helm repo add bitnami https://charts.bitnami.com/bitnami")
local("helm repo update")
local("helm upgrade --install my-backend-chart ./my-backend-chart -f C:/adesso/demo/my-backend-chart/values.yaml -n dev")

k8s_yaml(kustomize("C:/adesso/demo/kube/overlays/dev"))

# Update the k8s_resource with correct resource names
k8s_resource("backend-app", port_forwards=8080)
k8s_resource("postgres-db", port_forwards=5432)

# Ingress resource
k8s_yaml('C:/adesso/demo/kube/base/ingress.yaml')

watch_file(backend_path + "src/main/java/de/adesso/demo/controller/UserController.java")
watch_file(backend_path + "src/")
watch_file(backend_path + "target/")
