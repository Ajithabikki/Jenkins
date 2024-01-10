def call() {
    sh 'ls -ltr'
    sh 'cd java-maven-sonar-argocd-helm-k8s/spring-boot-app && mvn clean package'
}
