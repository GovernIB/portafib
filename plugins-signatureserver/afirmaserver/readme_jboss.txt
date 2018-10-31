
Dependència a usar en JBOSS 5.x :

    <dependency>
      <groupId>org.fundaciobit.plugins</groupId>
      <artifactId>plugin-signatureserver-afirmaserver</artifactId>
      <version>2.0.4</version>
      
      <exclusions> 
        <exclusion>  
          <groupId>xerces</groupId>
          <artifactId>xercesImpl</artifactId>
        </exclusion>

        <exclusion>  
          <groupId>com.sun.xml.bind</groupId>
          <artifactId>jaxb-impl</artifactId>
        </exclusion>

        <exclusion>  
          <groupId>xalan</groupId>
          <artifactId>xalan</artifactId>
        </exclusion>

        <exclusion>  
          <groupId>org.slf4j</groupId>
          <artifactId>log4j-over-slf4j</artifactId>
        </exclusion>

        <exclusion>  
          <groupId>org.slf4j</groupId>
          <artifactId>jcl-over-slf4j</artifactId>
        </exclusion>
        
        <exclusion>  
          <groupId>org.apache.ws.security</groupId>
          <artifactId>wss4j</artifactId>
        </exclusion>
        
        <exclusion>  
          <groupId>org.apache.santuario</groupId>
          <artifactId>xmlsec</artifactId>
        </exclusion>
      </exclusions> 
    </dependency>