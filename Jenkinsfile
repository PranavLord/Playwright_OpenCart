pipeline {
    agent any

    tools {
        maven 'Maven 3'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to QA") {
            steps {
                echo "Deploy to QA"
            }
        }

        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/PranavLord/Playwright_OpenCart.git'
                    bat '''
                    mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Dfile.encoding=UTF-8 -e -X
                    '''
                }
            }
        }

        stage('Debug Check for Report') {
            steps {
                echo "Listing root directory..."
                bat 'dir'
                echo "Listing build directory..."
                bat 'dir build'
                echo "Checking TestExecutionReport.html exists..."
                bat 'type build\\TestExecutionReport.html'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'build',
                             reportFiles: 'TestExecutionReport.html',
                             reportName: 'HTML Extent Report',
                             reportTitles: ''])
            }
        }
    }
}
