

  (a) Copiar el fitxer jboss  a /etc/init.d

  (b) Editar el fitxer jboss i modificar les variables d'entorn definides dins de l'script (SERVER, JBOSS_HOME, JAVAPTH, JAVA_HOME)
  
  (c) COPIAR LES MATEIXES VARIABLES AL RUN.CONF

  (d) Configurar el servei:
            $ sudo chmod 777 /etc/init.d/jboss 
            $ sudo chkconfig -add jboss

  (d) Arrancar el servei "$ sudo /etc/init.d/jboss start"


Per més detalls veure Manual de Instal·lació