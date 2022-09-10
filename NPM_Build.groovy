pipeline {
    agent {label 'slave01'}




    environment {
		DOCKERHUB_CREDENTIALS=credentials('DockerHub')
	}

    stages{
        stage ('Install NPM on slvae machine') {
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
