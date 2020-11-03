
Per configurar la firma de l'aplicació per fer un RELEASE cal tenir un keystore amb un certificat per signar, i
ficar les següents variables dins el fitxer amb els valors que pertoqui a "local.properties":

# Per signar l'app
# Ruta cap al keystore a emprar
signingKeystoreFile=C\:\\projectes\\portafib\\certificats\\keystore.jks
# Clau per accedir al keystore
signingKeystorePass=passKeystore
# Alias del certificat per signar dins el keystore
signingCertAlias=alias
# Clau del certificat per signar
signingCertPass=passCertificat