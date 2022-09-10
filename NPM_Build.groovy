pipeline {
    agent {label 'slave01'}

    tools {
        nodejs "nodejs"
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

        stage ('Build container') {
            steps {
                sh "docker image tag mikesoroceanu/simplewebapp-npm-docker:${BUILD_ID} mikesoroceanu/simplewebapp-npm-docker:latest"
            }
        }

		stage('Login to Docker Hub') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}


		stage('Push image to Docker Hub') {

			steps {
				sh "docker push mikesoroceanu/simplewebapp-npm-docker:${BUILD_ID}"
                sh "docker push mikesoroceanu/simplewebapp-npm-docker:latest"
			}
		}
	
  }
}
