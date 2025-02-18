# Define paths
backend_path = "C:/adesso/demo/"

# Load the Helm remote extension
load('ext://helm_remote', 'helm_remote')

allow_k8s_contexts('kind-kind')
namespace = "dev"

# Step 1: Rebuild the jar when source changes.
local_resource(
    "build-backend",
    "mvn clean install",  # Adjust if you're using Gradle
    deps=[backend_path + "src/"],
)

# Apply Kubernetes manifests for backend app
local("podman build -t localhost/backend-app .")
local("podman image save -o backend1.tar localhost/backend-app")
local("kind load image-archive backend1.tar --name kind-cluster")
local("kubectl apply -f kube/base/backend-deployment.yaml")
local("kubectl rollout restart deployment backend-app")

# Define the Helm chart deployment
#helm_remote(
#    chart='bitnami/postgresql',  # Correct Chart Name
#    repo_url='https://charts.bitnami.com/bitnami',  # Correct Repository URL
#    namespace='dev',  # Namespace
#    values="C:/adesso/demo/database/postgresql-multi-database-local.yaml"  # Correct Path to values.yaml file
#)

k8s_yaml(kustomize("C:/adesso/demo/kube/overlays/dev"))

k8s_resource("backend-app", port_forwards=8080)
#k8s_resource("bitnami/postgresql", port_forwards=5432)

watch_file(backend_path + "src/main/java/de/adesso/demo/controller/UserController.java")
watch_file(backend_path + "src/")
watch_file(backend_path + "target/")
