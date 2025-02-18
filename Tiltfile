# Define paths
backend_path = "C:/adesso/demo/"
load('ext://helm_remote', 'helm_remote')

allow_k8s_contexts('kind-kind')
#set_namespace("dev")  # Ensure resources are deployed in the 'dev' namespace
namespace = "dev"


# Step 1: Rebuild the jar when source changes.
local_resource(
    "build-backend",
    "mvn clean install",  # Adjust if you're using Gradle
    deps=[backend_path + "src/"],
)

custom_build(
    "localhost/backend-app",  # Image name
    "podman build -t localhost/backend-app .",  # Build command
    deps=["C:/adesso/demo/src/","C:/adesso/demo/target"],
    tag='latest',	# Dependencies to track changes
    skips_local_docker=True,
	live_update=[  # Sync changes live
        sync("C:/adesso/demo/target/", "/demo/"),
	#	,restart_process()   Restart Java app for changes to apply
],)

#k8s_custom_deploy(
#    name="backend-app",
#    apply_cmd="kubectl apply -o json -f kube/base/backend-deployment.yaml",
#    delete_cmd="kubectl delete -f kube/base/backend-deployment.yaml",
#    deps=["kube/backend-deployment.yaml"]
#)

local("podman build -t localhost/backend-app .")
local("podman image save -o backend1.tar localhost/backend-app")
local("kind load image-archive backend1.tar --name kind-cluster")
local("kubectl apply -f kube/base/backend-deployment.yaml")
local("kubectl rollout restart deployment backend-app")

k8s_yaml(kustomize("C:/adesso/demo/kube/overlays/dev"))

helm_remote(    'postgresql', 
   repo_url='https://charts.bitnami.com/bitnami',
   namespace='dev',
   values="C:/adesso/demo/database/postgresql-multi-database-local.yaml"  # Path to your YAML file
)
 
watch_file("C:/adesso/demo/src/main/java/de/adesso/demo/controller/UserController.java")
watch_file("C:/adesso/demo/src/")
watch_file("C:/adesso/demo/target/")



