<%@ include file="/WEB-INF/views/html_header.jsp"%>

<style>
    body{
        background-color: #CEE3F6;
    }
</style>

  <br/>
  <br/>
  
<div class="lead" style="margin-bottom:10px; text-align:center;">
  

  <fmt:message key="plugindefirma.seleccio.title"/>
  <br/>
  <h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
  <fmt:message key="plugindefirma.seleccio.subtitle"/>
  </h5>
  <br/>
  <small>
     Si no voleu que aparegui aquesta pantalla quan només hi ha un plugin, llavors anau a la <br/>
     classe <b>org.fundaciobit.plugins.signatureweb.exemple.controller.SignatureModuleController</b><br/>
     i editau el camp estatic stepSelectionWhenOnlyOnePlugin i assignau-li un valor true;
  </small>
  
  <br/>
  <div class="well" style="max-width: 400px; margin: 0 auto 10px;">
  <c:forEach items="${plugins}" var="plugin">
     <button type="button" class="btn btn-large btn-block btn-primary" onclick="location.href='<c:url value="/common/signmodule/showsignaturemodule/${plugin.pluginID}/${signaturesSetID}"/>'">
     <b>${plugin.nom}</b><br>
     <small>
     <i>${plugin.descripcioCurta}</i>
     </small>
     </button>
  </c:forEach>
  </div>
  
  <br/>
  
</div>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>