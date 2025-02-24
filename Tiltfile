
current_dir = os.getcwd()
overlay_path = os.path.join(current_dir, "kube", "overlays", "dev")

# Define paths
backend_path = "./"
custom_values_path = "./my-backend-chart/values.yaml"

# Load the Helm remote extension
load('ext://helm_remote', 'helm_remote')
local("kubectl create namespace dev --dry-run=client -o yaml | kubectl apply -f -")

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
local("kind load image-archive backend1.tar --name kind-kind-cluster")
local("kubectl apply -f kube/base/backend-deployment.yaml")
local("kubectl rollout restart deployment backend-app")

# Define the Helm chart deployment using Helm commands
local("helm repo add bitnami https://charts.bitnami.com/bitnami")
local("helm repo update")
local("helm upgrade --install my-backend-chart ./my-backend-chart -f ./my-backend-chart/values.yaml -n dev")

k8s_yaml(kustomize(overlay_path))

# Update the k8s_resource with correct resource names
k8s_resource("backend-app", port_forwards=8080)
k8s_resource("postgres-db", port_forwards=5432)

# Ingress resource
k8s_yaml('./kube/base/ingress.yaml')

watch_file(backend_path + "src/main/java/de/adesso/demo/controller/UserController.java")
watch_file(backend_path + "src/")
watch_file(backend_path + "target/")
