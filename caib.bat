

mvn -T 8 -Dmaven.javadoc.skip=true -DskipTests=true clean package -Dcaib -Pws-portafirmas -Ppassarelaweb-v1 -Ppassarelaserver-v1 -Pws-portafib-v1 -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true