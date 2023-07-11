pipeline{

    agent any

    tools {
        jdk "Java17"
        maven "Maven3"
     }

    stages{

        stage('Clean workspace') {
             steps {
                  cleanWs()
              }
         }
        stage('Check out from SCM') {
             steps {
                  git branch: 'master', credentialsId:'github', url:'https://github.com/nguepempoue/serviceorganisation.git'
             }
         }

        stage('Test') {
              steps {
                   sh 'mvn test'
              }
          }

          stage('Build') {
             steps {
                 sh 'mvn clean package'
               }
           }

          stage('Deploy') {
               steps{
                 sh 'docker build -t serviceorganisation .'
                 sh 'docker run -p 8083:8083 serviceorganisation'
                }
          }


     }
}