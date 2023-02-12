pipeline {
    agent any
    tools {
        maven "3.8.5"
    }
    stages {
        stage('maven version') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('run tests') {
            steps {
                sh "mvn test"
            }
        }
    }
    post {
        always {
            junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults : true)
        }
    }
}