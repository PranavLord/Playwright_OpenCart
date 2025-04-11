pipeline {
    agent any

    tools {
        maven 'Maven 3'
    }

    environment {
        REPORT_DIR = 'build'
        REPORT_FILE = 'TestExecutionReport.html'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/PranavLord/Playwright_OpenCart.git'
            }
        }

        stage('Debug Suite XML Path') {
             steps {
                echo 'Checking if the testng_regression.xml file exists and is readable...'
                bat 'dir src\\test\\resources\\testrunners'
                bat 'type src\\test\\resources\\testrunners\\testng_regression.xml'
         }
    }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Dfile.encoding=UTF-8'
            }
        }

        stage('Verify Report File Exists') {
            steps {
                bat 'dir /s /b build\\*.html'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: "${env.REPORT_DIR}",
                             reportFiles: "${env.REPORT_FILE}",
                             reportName: 'HTML Extent Report'])
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
