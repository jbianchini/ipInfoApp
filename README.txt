INSTALLATION:

1 - Open your command line inside the project root directory.
2 - Run: 'mvn clean install'
3 - Run: 'docker build -t ipinfo-app .'
4 - Run: 'docker-compose up'

API end points:

IP INFO: localhost:8080/info/1.2.3.4
MAX DISTANCE: localhost:8080/dist/max
MIN DISTANCE: localhost:8080/dist/min
AVERAGE DISTANCE: localhost:8080/dist/avg