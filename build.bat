call mvn clean package
call docker build . --rm -t swatigor17/codenames-backend:1.1
call docker push swatigor17/codenames-backend:1.1