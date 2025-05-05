

# CommonInfo

Configuracions generals de firma i identificacio del solicitant i solicitat

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**signProfile** | **String** | Identificador único del usuario |  [optional] |
|**languageUI** | **String** | Idioma en que retornar valors i missatges d&#39;error |  |
|**username** | **String** |  - FIRMA WEB: Requerit. És el codi d&#39;usuari dins l&#39;entitat. Per exemple en entorn CAIB serien els \&quot;u800xx\&quot; o \&quot;u[DNI]\&quot;   -FIRMA EN SERVIDOR: Opcional. Es reconama que valgui null a no ser que l&#39;administrador digui el contrari. És la configuració de firma en el sistema específic de firma. Per exemple amb el Plugin de @firma federat et pots connectar amb un usuari-password però aquest pot tenir diverses configuracions per fer firmes en servidor o àlies: \&quot;username\&quot; s&#39;utilitza de definir aquesta configuració o àlies. |  [optional] |
|**administrationID** | **String** |  - FIRMA WEB: Requerit. És el DNI de la persona signant. Si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest.   - FIRMA EN SERVIDOR: Opcional. És el CIF o NIF associat al certificat en servidor. Si es defineix i si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest. |  |
|**organizationID** | **String** | Opcional. És el CIF de l&#39;organització representada pel signant. Si esta activa la validació dins PortaFIB llavors es valida que el Certificat sigui un certificat de representant d&#39;aquest CIF. |  [optional] |
|**signerEmail** | **String** |  - FIRMA WEB: Opcional. Correu del Firmant. Per afegir a les dades de la firma.   - FIRMA EN SERVIDOR: Opcional. Correu del departament que ordena la firma. Per afegir a les dades de la firma. |  [optional] |



