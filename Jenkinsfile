pipeline {
    agent any

    stages {
        stage('Dependencies') {
            steps {
                echo 'Fetching dependencies...'
                sh 'mvn clean install -DskipTests' 
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean package -DskipTests'  
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build, Dependencies setup, and Tests were successful!'
        }
        failure {
            echo 'Build, Dependencies setup, or Tests failed.'
        }
        always {
            echo 'This will run regardless of the outcome.'
        }
    }
}

