


set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=128m

mkdir target

mvn install -DskipTests -Dregenerateapi -e