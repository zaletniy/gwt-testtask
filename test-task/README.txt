In this folder you see test-task Maven project.
Some hints how to use it:

============================
Build
============================
mvn clean install


============================
Run in servlet container
============================
1. mvn clean install
2. deploy target/test-task-1.0-SNAPSHOT.war
3. open with you browser http://YOU_CONTAINER_HOST:YOUR_CONTAINER_PORT/test-task-1.0-SNAPSHOT/testtask.html


============================
Prepare test coverage report
============================
1. mvn cobertura:cobertura
2. open with you browser target/site/cobertura/index.html

If any questions feel free to ask.
Ilya