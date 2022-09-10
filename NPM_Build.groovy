pipeline {
    agent {label 'slave01'}

    tools {
        npm "npm"
    }


    environment {
		DOCKERHUB_CREDENTIALS=credentials('DockerHub')
	}

    stages{

        stage ('Install npm') {
            steps {
                sh "npm install -g npm"
            }
        }   

        stage ('Build container') {
            steps {
                sh "docker build . -t mikesoroceanu/simplewebapp-npm-docker:${BUILD_ID}"
            }
        }
	
  }
}
