
rm -r .\build\

./gradlew build

docker rmi demo/app1:1

docker build -t demo/app1:1 .

docker run -e "SPRING_PROFILES_ACTIVE=prod"  --rm -it -p 8564:8080 demo/app1:1

