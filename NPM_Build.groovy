pipeline {
    agent {label 'slave01'}

    tools {
        nodejs "node"
    }


    environment {
		DOCKERHUB_CREDENTIALS=credentials('DockerHub')
	}

    stages{

 

        stage ('Build container') {
            steps {
                sh "docker build . -t mikesoroceanu/simplewebapp-npm-docker:${BUILD_ID}"
            }
        }
	
  }
}
