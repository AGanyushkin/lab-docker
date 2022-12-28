
rm -r .\build\

./gradlew build

docker rmi demo/app1:1

docker build -t demo/app1:1 .

docker-compose up
