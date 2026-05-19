<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${headerEnabled}">

    <style>
    
    .franja-inferior {
        background-color: #ffffff;
    }
    
    .contenedor-central {
        font-family: sans-serif;
        font-weight: bold;
        min-width: 200px;
        background-color: #ffffff; /* Un fondo claro para el contenedor */
        border-radius: 10px; /* Bordes redondeados */
        padding: 10px;
        margin: -4px;
        text-align: center;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Sombra suave opcional */
    }
    
    .contenedor-central img {
        max-width: 150px; /* Ajusta el tamaño máximo de la imagen */
        height: auto;
        display: block; /* Evita espacio extra debajo de la imagen */
        margin: 0 auto 10px; /* Centra la imagen y añade espacio debajo */
    }
    
    .contenedor-central p {
        margin-top: 0;
        font-size: 1em;
        color: #333; /* Color del texto */
        margin: 0px;
    }
    </style>

    <table style="position: absolute; left: -10px; width: calc(100% + 10px);" border=0 cellpadding=0 cellspacing=0>
        <tr style="background-color: ${backgroundColor};">
            <td style="height: 10%; width: 40%;">&nbsp;</td>
            <td style="width: auto">&nbsp;</td>
            <td style="width: 40%">&nbsp;</td>
        </tr>

        <tr style="background-color: ${backgroundColor};">
            <td  style="height: 30%; width: 50%;">&nbsp;</td>
            <td style="width: auto;" rowspan=2>
                <div class="contenedor-central">
                    <img src="<c:url value="${logoUrl}"/>" alt="Logo Entitat">
                    <c:if test="${not empty text}">
                        <p>${text}</p>
                    </c:if>
                </div>
            </td>
            <td style="width: 50%;">&nbsp;</td>
        </tr>

        <tr>
            <td style="height: 40%; width: 50%;">&nbsp;</td>
            <td style="width: 50%;">&nbsp;</td>
        </tr>

        <tr>
            <td style="height: 10%; width: 50%:">&nbsp;</td>
            <td style="width: auto;">&nbsp;</td>
            <td style="width: 50%">&nbsp;</td>
        </tr>
    </table>
    <div style="height: 170px; margin-top: -10px;">&nbsp;</div>
    <%-- FINAL DE CAPÇALERA --%>
</c:if>

<div class="lead" style="margin-bottom: 10px; text-align: center;">

    <h3><fmt:message key="plugindefirma.seleccio.title" /></h3>
    <h5 style="line-height:10px; margin-top: 5px; margin-bottom: 0px;font-weight: 300;">
        <fmt:message key="plugindefirma.seleccio.subtitle" />
    </h5>

    <br />

    <div class="well" style="max-width:400px; margin: 0 auto 10px;">
        <c:forEach items="${moduls}" var="modul">
            <button type="button" id="plugin_${modul.codi}" class="btn btn-large btn-block btn-primary plugin-btn" style="white-space: normal;"
                data-url="<c:url value="${thecontext}/showsignaturemodule/${modul.pluginID}/${signaturesSetID}"/>">
                <div style="display: ${not empty modul.iconaID?'flex':'block'}; align-items: center; text-align: ${not empty modul.iconaID?'left':'center'};">
                    <c:if test="${not empty modul.iconaID}">
                        <img src="<c:url value="${pfi:fileUrlPublic(modul.icona)}"/>" alt="Icona" style="max-height: 40px; margin-right: 10px;"/>
                    </c:if>
                    <div>
                        <b>${modul.nom.traduccions[lang].valor}</b><br>
                        <small style="color: white;">
                            <i>${modul.descripcioCurta.traduccions[lang].valor}</i>
                        </small>
                    </div>
                </div>
            </button>
        </c:forEach>
        
        <%-- Boto de Cancel·lar en gris --%>
        <br/>
        <button type="button" class="btn btn-block btn-secondary plugin-btn" style="white-space: normal;"
            onclick="location.href='<c:url value="${thecontext}/cancelSignatureSelection/${signaturesSetID}"/>'">
            <b><fmt:message key="genapp.cancel" /></b>
        </button>
    </div>

    <br />

</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.plugin-btn');
    let isProcessing = false;
    
    buttons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (isProcessing) {
                e.preventDefault();
                return;
            }
            
            isProcessing = true;
            
            // Deshabilitar visualmente todos los botones
            buttons.forEach(btn => {
                btn.disabled = true;
                btn.classList.add('disabled');
                btn.style.opacity = '0.6';
                btn.style.cursor = 'not-allowed';
            });
            
            // Obtener la URL del data attribute
            const url = button.getAttribute('data-url');
            
            // Redirigir
            setTimeout(() => {
                window.location.href = '<c:url value="' + url + '"/>';
            }, 150);
        });
    });
});
</script>