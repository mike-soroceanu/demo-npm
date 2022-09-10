pipeline {
    agent {label 'slave01'}

    environment {
		DOCKERHUB_CREDENTIALS=credentials('DockerHub')
	}

    parameters{
      string(defaultValue: '9090', description: 'Port used in this build.', name: 'PORT')
    }

    stages{
		stage('Login to Docker Hub') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}


		stage('Pull image from Docker Hub') {

			steps {
				sh "docker pull mikesoroceanu/simplewebapp-npm-docker"
			}
		}

		stage('Run container') {

			steps {
				sh "docker run -d -p {params.PORT}:4000 --name MySimpleWebContainer mikesoroceanu/simplewebapp-npm-docker"
			}
		}
	
  }
}
