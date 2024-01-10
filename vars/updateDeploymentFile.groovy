def call() {
    environment {
        GIT_REPO_NAME = "Jenkins"
        GIT_USER_NAME = "Ajithabikki"
    }
    withCredentials([string(credentialsId: 'github', variable: 'GITHUB_TOKEN')]) {
        sh '''
            git config user.email "ajithabikki9@gmail.com"
            git config user.name "Ajithabikki"
            BUILD_NUMBER=${BUILD_NUMBER}
            sed -i "s/replaceImageTag/${BUILD_NUMBER}/g" java-maven-sonar-argocd-helm-k8s/spring-boot-app-manifests/deployment.yml
            git add java-maven-sonar-argocd-helm-k8s/spring-boot-app-manifests/deployment.yml
            git commit -m "Update deployment image to version ${BUILD_NUMBER}"
            git push https://${GITHUB_TOKEN}@github.com/${GIT_USER_NAME}/${GIT_REPO_NAME} HEAD:main
        '''
    }
}
