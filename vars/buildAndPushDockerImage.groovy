def call() {
    environment {
        DOCKER_IMAGE = "ajithabikki/cicd:${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('docker-cred')
    }
    script {
        sh 'cd java-maven-sonar-argocd-helm-k8s/spring-boot-app && docker build -t ${DOCKER_IMAGE} .'
        def dockerImage = docker.image("${DOCKER_IMAGE}")
        docker.withRegistry('https://index.docker.io/v1/', "docker-cred") {
            dockerImage.push()
        }
    }
}
