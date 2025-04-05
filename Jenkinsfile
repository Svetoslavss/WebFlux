pipeline {
    agent any

    tools {
        maven 'Maven 3.8.4'
    }

    stages {
        stage('Dependencies') {
            steps {
                echo 'Fetching dependencies...'
                bat 'mvn clean install -DskipTests' 
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean package -DskipTests'  
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'This will run regardless of the outcome.'
        }
        success {
            echo 'Build, Dependencies setup, and Tests were successful!'
        }
        failure {
            echo 'Build, Dependencies setup, or Tests failed.'
        }
    }
}
