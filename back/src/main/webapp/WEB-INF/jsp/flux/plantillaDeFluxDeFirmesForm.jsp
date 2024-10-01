<%@page import="es.caib.portafib.persistence.UsuariEntitatJPA"
%><%@page import="es.caib.portafib.persistence.EstatDeFirmaJPA"
%><%@page import="es.caib.portafib.persistence.UsuariPersonaJPA"
%><%@page import="es.caib.portafib.model.entity.UsuariEntitat"
%><%@page import="es.caib.portafib.model.entity.EstatDeFirma"
%><%@page import="es.caib.portafib.model.entity.UsuariPersona"
%><%@page import="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields"
%><%@ page contentType="text/html; charset=UTF-8" language="java"
%><%@page import="es.caib.portafib.logic.utils.BlocUtils"
%><%@page import="es.caib.portafib.logic.utils.FirmaUtils"
%><%@page import="es.caib.portafib.persistence.BlocDeFirmesJPA"
%><%@page import="es.caib.portafib.persistence.FirmaJPA"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<style>
.radius {
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
}
</style>

<c:set var="contexte" value="${fluxDeFirmesForm.contexte}" />

<%--                  VARIABLE 'usuariEntitat'                         --%>
<%-- Booleà: + True  : indica que és una plantilla de usuari-entitat   --%>
<%--         + False : indica que és una plantilla de usuari-aplicacio --%>
<%--         + null  : indica que no és una plantilla                  --%>
<c:if test="${not empty fluxDeFirmesForm.plantillaFluxDeFirmes}">

  <c:if test="${empty fluxDeFirmesForm.plantillaFluxDeFirmes.usuariEntitatID}">
    <c:set var="usuariEntitat" value="false" />
  </c:if>
  
  <%-- not empty fluxDeFirmesForm.plantillaFluxDeFirmes.usuariAplicacioID">  --%>
  <c:if test="${not empty fluxDeFirmesForm.plantillaFluxDeFirmes.usuariEntitatID}">
    <c:set var="usuariEntitat" value="true" />
  </c:if>

</c:if>

<c:set var="readOnly" value="${fluxDeFirmesForm.readOnly || onlyFlux}" />

<c:if test="${fluxDeFirmesForm.nou}">
<c:url var="action"  value="${contexte}/new" />
</c:if>
<c:if test="${not fluxDeFirmesForm.nou}">
<c:url var="action"  value="${contexte}/${fluxDeFirmesForm.fluxDeFirmes.fluxDeFirmesID}/edit" />
</c:if>

<form:form name="fluxDeFirmesForm" action="${action}" modelAttribute="fluxDeFirmesForm"
  method="post" enctype="multipart/form-data">

  <c:if test="${not onlyFlux}">

    <form:hidden path="nou" />

    <form:hidden path="redirectOnModify" />

    <form:hidden path="readOnly" />

    <form:hidden path="contexte" />

    <input type="hidden" name="usuariEntitatID" id="usuariEntitatID" />

    <input type="hidden" name="blocID" id="blocID" />

    <input type="hidden" name="blocOrdre" id="blocOrdre" />

    <input type="hidden" name="minimDeFirmes" id="minimDeFirmes" />

    <input type="hidden" name="firmaID" id="firmaID" />
    
    <input type="hidden" name="revisorID" id="revisorID" />
    
    <input  type="hidden" name="motiu" id="motiu" />
    
    <%@include file="../webdb/fluxDeFirmesFormTitle.jsp" %>

  </c:if>

  <style type="text/css">
      .table-responsive { display:inline-table !important;} 
  </style>

  <div class="module_content">
    <div class="tab_container">

        <c:if test="${not onlyFlux}">
      
       <%@include file="../webdb/fluxDeFirmesFormCorePre.jsp" %>
             
       <%@include file="../webdb/fluxDeFirmesFormCore.jsp" %>

       <%-- Mostrar Descripció: Miram si es Plantilla o Flux --%>
       <c:if test="${not empty usuariEntitat}" >     
       <%@include file="../webdb/plantillaFluxDeFirmesFormCore.jsp" %>
       </c:if>
       
      <c:if test="${fluxDeFirmesForm.nou}">
        <tr>
          <td><label><fmt:message key="usuarientitatprimerafirma" />
              &nbsp;(*)</label>
          </td>
          <td>
              <%--  usuariEntitatPrimeraFirma --%>
              <%@ include file="/WEB-INF/jsp/common/seleccioUsuariField.jsp"%>
        </tr>
      </c:if>

        <%@include file="../webdb/fluxDeFirmesFormCorePost.jsp" %>
        
        <c:if test="${fluxDeFirmesForm.nou || (not isPlantillaRest && not fluxDeFirmesForm.nou)}">
           <%@include file="../webdb/fluxDeFirmesFormButtons.jsp" %>
        </c:if>

        <script type="text/javascript">
           <%-- plantillaFluxDeFirmes.descripcio --%>
           document.getElementById("<%=PlantillaFluxDeFirmesFields.DESCRIPCIO.getFullName()%>").rows=1;
        </script> 
  
        <%-- Final de if de onlyFlux --%>
      </c:if>


      <!-- No mostram el flux si estam en NOU  -->
      <c:if test="${!fluxDeFirmesForm.nou}">
      
        <%-- XYZ ZZZ Si estam amb flux rest llavors mostrar boto de OK i Cancelar  --%>
        
        <c:if test="${isPlantillaRest && not readOnly}">
        <center>
        <a href="<c:url value="${contexte}/finalRestOK"/>" class="btn btn-large btn-primary"> <fmt:message key="genapp.save" /> </a>
        <a href="<c:url value="${contexte}/finalRestCanceled"/>" class="btn btn-large btn-secondary"><fmt:message key="genapp.cancel" /></a>
        </center>
        </c:if>
        
      
        <center>
          <c:if test="${readOnly == false || onlyFlux == false}">            
            <hr />
            <p class="text-error">
              <fmt:message key="avis.edicioflux" />
            </p>
            <br>
          </c:if>

          <%-- LLEGENDA NOMÉS SI ESTAM VISUALITZANT EL FLUX --%>
          <c:if test="${readOnly}">
                <div style="float: right; font-size: 0.75em; border: 1px solid #000000;">
                    <h6>Llegenda</h6>
                    <p style="margin: 4px; text-align: left;">
                        <span style="border: 2px solid #0000ff; margin: 2px; padding: 1px 4px;" class="radius">&nbsp;</span>
                        <fmt:message key="llegenda.bloc" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="border: 2px solid #00ff00; margin: 2px; padding: 1px 4px;" class="radius">&nbsp;</span>
                        <fmt:message key="llegenda.firma" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="border: 2px solid #ff0000; margin: 2px; padding: 1px 4px;" class="radius">&nbsp;</span>
                        <fmt:message key="llegenda.revisio" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="background:#FFFFFF; border: 1px solid #000000; margin: 2px; padding: 1px 4px;">&nbsp;</span>
                        <fmt:message key="llegenda.noiniciat" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="background:#C0F7FE; border: 1px solid #000000; margin: 2px; padding: 1px 4px;">&nbsp;</span>
                        <fmt:message key="llegenda.enproces" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="background:#BBFFBB; border: 1px solid #000000; margin: 2px; padding: 1px 4px;">&nbsp;</span>
                        <fmt:message key="llegenda.firmat" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="background:#D0D0D0; border: 1px solid #000000; margin: 2px; padding: 1px 4px;">&nbsp;</span>
                        <fmt:message key="llegenda.descartat" />
                    </p>
                    <p style="margin: 4px; text-align: left;">
                        <span style="background:#FF7575; border: 1px solid #000000; margin: 2px; padding: 1px 4px;">&nbsp;</span>
                        <fmt:message key="llegenda.rebutjat" />
                    </p>
                </div>
          </c:if>

          <%-- IMPORTANT: No eliminar cellpadding ni cellspacing ja que sino apareixen espais entre files. --%>
          <table cellpadding="0" cellspacing="0" style="padding:0px 0px 0px 0px; margin: 0px 0px 0px 0px; border-collapse: separate; border-spacing: 0px;">

            <%-- CASELLA DE INICI  --%>
            <tr style="padding:0px; margin: 0px;" align="center">
              <td colspan="2" style="padding:0px; margin: 0px">
                  <button class="btn btn-success" type="button">
                    <fmt:message key="inici" />
                  </button>
              </td>
            </tr>
            
            <c:set var="blocseparator">
               <tr style="padding:0px; margin: 0px; height: 10px; line-height: 10px">
                <td width="50%">&nbsp;</td>
                <td width="50%" style="border-left: 2px solid #0000ff;">&nbsp;</td>
               </tr>
            </c:set>
            

            <%--  AFEGIR BLOC DE FIRMES AL PRINCIPI --%>
            <c:if test="${readOnly == false}">
               <c:out value="${blocseparator}" escapeXml="false" />
               <tr style="padding:0px; margin: 0px; text-align: center;">
                <td colspan="2" >
                    <button type="button" class="btn btn-primary btn-sm" onclick="javascript:afegirBlocSelectUser('0')" title="<fmt:message key="blocdefirmes.nou"/>">
                        <i class="fas fa-plus-circle"></i>
                        &nbsp;
                        <fmt:message key="blocdefirmes.nou" />                        
                    </button>
                </td>
              </tr>
            </c:if>

            <c:forEach items="${fluxDeFirmesForm.fluxDeFirmes.blocDeFirmess}" var="bloc">
              <%
               BlocDeFirmesJPA bloc = (BlocDeFirmesJPA) pageContext.getAttribute("bloc");
              %>
              <c:out value="${blocseparator}" escapeXml="false" />
              <tr>
                <td colspan="2" style="text-align:center;">

                    <%-- UN BLOC --%>
                    <c:set var="background"
                      value="${fluxDeFirmesForm.backgroundColorsOfBloc[bloc.blocDeFirmesID]}" />
                    <c:if test="${not empty background}">
                      <c:set var="background">background:\#${background};</c:set>
                    </c:if>

                    <div class="radius" style="${background} max-width:600px; border: 2px solid #0000ff; margin: 0px; padding: 0px; display: inline-block">
                      <c:if test="${pfi:isDesenvolupament()}">
                       ${bloc.blocDeFirmesID}
                      </c:if>
                      <table style="width:100%">

                        

                          <%-- ACCIONS SOBRE EL BLOC --%>
                          <c:if test="${readOnly == true}">
                            <c:if test="${fn:length(bloc.firmas) != bloc.minimDeFirmes}">
                              <small><fmt:message key="blocDeFirmes.minimDeFirmes" />: ${bloc.minimDeFirmes}</small>
                            </c:if>
                          </c:if>
                          
                          <tr>
                            <td>
                              <%-- border: 2px solid #0000ff;   margin: 4px; --%>
                              <div class="radius"  style="float: right;  padding: 4px;text-align: center;">
                                  <c:if test="${readOnly == false}">

                                  <button type="button" class="btn btn-success btn-sm" onclick="javascript:afegirFirmaSelectUser('${bloc.blocDeFirmesID}')" title="<fmt:message key="firma.nova"/>">
                                    <i class="fas fa-plus-circle"></i> <fmt:message key="firma.nova" />                        
                                  </button>
                                  
                                  <%-- Calcul del menor valor admisible per MinimDeFirmes --%>
                                  <%
                                  pageContext.setAttribute("minimBloc", BlocUtils.minimFirmes(bloc.getFirmas()));
                                  %>
                                  <div class="btn-group" style="padding-top: 2px;">
                                    <button
                                      class="btn btn-info btn-sm dropdown-toggle"
                                      data-toggle="dropdown"
                                      title="<fmt:message key="blocDeFirmes.minimDeFirmes"/>">
                                      <fmt:message key="blocDeFirmes.minimDeFirmes" />
                                      : ${bloc.minimDeFirmes}<span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                      <c:forEach var="i" begin="${minimBloc}"  end="${fn:length(bloc.firmas)}">
                                        <li><a href="#"
                                          onclick="changeMinimDeFirmesNum('${bloc.blocDeFirmesID}', '${i}')">${i}</a>
                                        </li>
                                      </c:forEach>
                                    </ul>
                                  </div>
                                  

                                  <c:if test="${fn:length(fluxDeFirmesForm.fluxDeFirmes.blocDeFirmess) > 1}">
                                    <div style="padding-top: 2px;display: inline-block;">
                                      <button class="btn btn-danger btn-sm"
                                        title="<fmt:message key="genapp.delete"/>"
                                        onclick="eliminarBloc('${bloc.blocDeFirmesID}')">
                                        <fmt:message key="genapp.delete.item" >
                                          <fmt:param><fmt:message key="blocdefirmes.simple"/></fmt:param>
                                        </fmt:message>
                                      </button>
                                    </div>
                                  </c:if>
                                  <%-- Final d'accions sobre el bloc --%>
                                 </c:if>
                                 
                              </div>
                             </td>
                             </tr>
                         
<tr>
                          <%-- LLISTAT DE FIRMES DEL BLOC --%>
                          <td width="100%">
                            <span style="text-align: center;">
                              <c:forEach items="${bloc.firmas}" var="firma">
                                <%
                                FirmaJPA firma = (FirmaJPA) pageContext.getAttribute("firma");
                                %>
                                <c:set var="background"
                                  value="${fluxDeFirmesForm.backgroundColorsOfFirma[firma.firmaID]}" />
                                <c:if test="${not empty background}">
                                  <c:set var="background">background:#${background};</c:set>
                                </c:if>
                                
                                <c:if test="${firma.usuariEntitat.actiu == false}">
                                  <c:set var="backgroundimage">background-image: url(<c:url value='/img/userdisabled.png'/>);background-repeat: no-repeat;background-position: center; </c:set>                                  
                                </c:if>

                                <div class="radius" style="${background} ${backgroundimage} float:left; border: 2px solid #00ff00; margin: 4px; padding: 8px; text-align: left">

                                  <c:if test="${pfi:isDesenvolupament()}">
                                      ${firma.firmaID}
                                  </c:if>

                                  <c:choose>
         
                                    <%--  CARREC --%>
                                    <c:when test = "${not empty firma.usuariEntitat.carrec}">
                                      <b>${firma.usuariEntitat.carrec}</b>
                                      <br/>
                                      <small>

                                        <c:remove var = "usuariEntitatFirmant"/>
	                                    <c:forEach items="${firma.estatDeFirmas}" var="estatdefirma">
                                           <c:if test = "${estatdefirma.tipusEstatDeFirmaInicialID == 1}">
                                               <c:set var="usuariEntitatFirmant" value="${estatdefirma.usuariEntitat}" />
                                           </c:if>                                 
	                                    </c:forEach>

                                        <c:if test = "${empty firma.estatDeFirmas}">
                                            ${firma.usuariEntitat.usuariPersona.nom}&nbsp;
                                            ${firma.usuariEntitat.usuariPersona.llinatges}
                                            <br>
                                        </c:if> 
    
                                        <c:if test = "${not empty usuariEntitatFirmant}">
                                            ${usuariEntitatFirmant.usuariPersona.nom}&nbsp;
                                            ${usuariEntitatFirmant.usuariPersona.llinatges}
                                            <br>
                                        </c:if> 
                                      </small>
                                    </c:when>
                                     
                                    <%--  USUARI EXTERN --%>
                                    <c:when test = "${not empty firma.usuariExternNom}">
                                      <i>${firma.usuariExternNom}&nbsp;${firma.usuariExternLlinatges}</i><br/>
                                      <small>
                                        <i class="fas fa-envelope"></i>${firma.usuariExternEmail}<br/>
                                        ${pfi:ofuscar(firma.usuariEntitat.usuariPersona.nif)}
                                      </small>
                                    </c:when>
                                    
                                    <%--  USUARI INTERN --%>
                                    <c:when test = "${empty firma.usuariExternNom}">
                                      ${firma.usuariEntitat.usuariPersona.nom}&nbsp;${firma.usuariEntitat.usuariPersona.llinatges}<br/>
                                      ${pfi:ofuscar(firma.usuariEntitat.usuariPersona.nif)}<br/>
                                    </c:when>
                                     
                                    <c:otherwise>
                                       ERROR UNKNOWN USER
                                    </c:otherwise>
                                  </c:choose>
                                  
                                  

                                  <table style="text-align: left">

                                    <tr>
                                    <td>
                                    
                                    <label class="checkbox inline" style="padding-top: 0px;">
                                      <input type="checkbox"
                                      ${firma.obligatori? 'checked="checked" ': ''}
                                      ${readOnly? 'disabled="disabled"' : ''} 
                                      onclick="ferFirmaObligatoria('${bloc.blocDeFirmesID}','${firma.firmaID}')" />
                                      <fmt:message key="firma.obligatori" />
                                    </label>
                                    <br/>
                                    
                                    <c:if test="${(fn:length(bloc.firmas) > 1) && readOnly == false}">
                                    <button class="btn btn-danger btn-sm"
                                      title="<fmt:message key="genapp.delete.item" ><fmt:param><fmt:message key="firma.firma"/></fmt:param></fmt:message>"
                                      onclick="eliminarFirma('${bloc.blocDeFirmesID}','${firma.firmaID}')">
                                      &nbsp;<i class="fas fa-trash fa-sm"></i>&nbsp;
                                    </button>
                                    </c:if>

                                    <c:if test="${readOnly == false}">

                                      <button type="button" class="btn btn-success btn-sm" onclick="javascript:afegirRevisorDeFirma('${firma.firmaID}','${firma.usuariEntitat.usuariEntitatID}')" title="<fmt:message key="firma.afegirrevisor"/>">
                                         &nbsp;<i class="fas fa-plus-circle"></i>&nbsp;                      
                                      </button>
                                      
                                      <%-- Motiu de firma--%>
                                      <c:if test="${empty firma.motiu}">
                                        <button type="button" class="btn btn-warning btn-sm" onclick="javascript:definirMotiuDeFirma('${firma.firmaID}')" title="<fmt:message key="firma.motiudefirma.definir"/>">
                                          &nbsp;<i class="fas fa-info-circle icon-white"></i>&nbsp;
                                        </button>
                                      </c:if>
                                      <c:if test="${not empty firma.motiu}">
                                        <button type="button" class="btn btn-success btn-sm" onclick="javascript:modificarMotiuDeFirma('${firma.firmaID}','${fn:escapeXml(firma.motiu)}')" title="<fmt:message key="firma.motiudefirma"/> ${fn:escapeXml(firma.motiu)}">
                                          &nbsp;<i class="fas fa-info-circle icon-white"></i>&nbsp;
                                        </button>
                                      </c:if>
                                      
                                      <%-- Mínim de Revisors de Firmes --%>
                                      <c:if test="${ not empty firma.revisorDeFirmas}">
                                        <br/>
                                       
                                        <%-- Calcul del menor valor admisible per MinimDeFirmes --%>
                                        <%
                                          pageContext.setAttribute("minimRevisors", FirmaUtils.minimRevisors(firma.getRevisorDeFirmas()));
                                        %>
                                        
                                        <div class="btn-group" style="padding-top: 2px;">
                                          <button
                                            class="btn btn-info btn-sm dropdown-toggle"
                                            data-toggle="dropdown"
                                            title="<fmt:message key="firma.minimDeRevisors"/>">
                                            <fmt:message key="firma.minimDeRevisors" />
                                            : ${firma.minimDeRevisors}<span class="caret"></span>
                                          </button>
      
                                          <ul class="dropdown-menu">
                                            <c:forEach var="i" begin="${minimRevisors}" end="${fn:length(firma.revisorDeFirmas)}">
                                              <li><a href="#"
                                                onclick="changeMinimDeRevisors('${bloc.blocDeFirmesID}', '${firma.firmaID}', '${i}')">${i}</a>
                                              </li>
                                            </c:forEach>
                                          </ul>
                                        </div>
                                      </c:if>
                                    </c:if>
                                    <c:if test="${readOnly == true}">
                                      <c:if test="${ not empty firma.revisorDeFirmas}">
                                        <small>
                                           <fmt:message key="firma.minimDeRevisors"/>: <b>${firma.minimDeRevisors}</b>
                                        </small>
                                      </c:if>
                                    </c:if>

                                    </td>
                                    </tr>

                                  </table>
                                  
                                  
                                  <c:forEach var="revisor" items="${firma.revisorDeFirmas}">
                                  <c:set var="background" value="${fluxDeFirmesForm.backgroundColorsOfRevisor[revisor.revisorDeFirmaID]}" />
                                  <c:if test="${not empty background}">
                                    <c:set var="background">background:#${background};</c:set>
                                  </c:if>
                                  <div class="radius" style="${background} ${backgroundimage} float:right; border: 2px solid #ff0000; margin: 4px; padding: 8px; text-align: left">
                                     
                                     <label class="checkbox inline" style="padding-left: 0px;padding-top: 0px;">
                                    <center><b><fmt:message key="revisorDeFirma.revisorDeFirma" /></b>
                                    
                                    </center>
                                    <i>${revisor.usuariEntitat.usuariPersona.nom}&nbsp${revisor.usuariEntitat.usuariPersona.llinatges}<br/>
                                    ${pfi:ofuscar(revisor.usuariEntitat.usuariPersona.nif)}</i>
                                    
                                    
                                    <br/>
                                    <label class="checkbox inline" style="padding-top: 0px;">
                                      <input type="checkbox"
                                      ${revisor.obligatori? 'checked="checked" ': ''}
                                      ${readOnly? 'disabled="disabled"' : ''} 
                                      onclick="ferRevisorObligatori('${bloc.blocDeFirmesID}','${firma.firmaID}','${revisor.revisorDeFirmaID}')" />
                                      <fmt:message key="firma.obligatori" />
                                    </label>
                                    
                                    <c:if test="${readOnly == false}">
                                    <br/>
                                    <button class="btn btn-danger btn-sm"
                                        title="<fmt:message key="genapp.delete.item" ><fmt:param><fmt:message key="revisorDeFirma.revisorDeFirma"/></fmt:param></fmt:message>"
                                        onclick="eliminarRevisor('${bloc.blocDeFirmesID}','${firma.firmaID}','${revisor.revisorDeFirmaID}')">
                                        &nbsp;<i class="fas fa-trash fa-sm"></i>&nbsp;
                                      </button>
                                    </c:if>
                                    </label>
                                  </div>
                                  </c:forEach>

                                </div>
                              </c:forEach>
                            </span>

                            </td>
                        </tr>
                      </table>

                    </div>
                  
                  </td>
              </tr>

              <%-- AFEGIR UN BLOC POSTERIOR --%>
              <c:if test="${readOnly == false}">
                <c:out value="${blocseparator}" escapeXml="false" />
                <tr>
                  <td colspan="2" align="center">

                   <button type="button" class="btn btn-primary btn-sm" onclick="javascript:afegirBlocSelectUser('${bloc.ordre + 5}')" title="<fmt:message key="blocdefirmes.nou"/>">
                        <i class="fas fa-plus-circle"></i>
                        &nbsp;
                        <fmt:message key="blocdefirmes.nou" />                        
                    </button>

                    </td>
                </tr>
              </c:if>
            </c:forEach>

            <%-- CASELLA DE FINAL  --%>
            <c:out value="${blocseparator}" escapeXml="false" />
            <tr>
              <td colspan="2" style="text-align: center;">
                  <button class="btn btn-info" type="button">
                    <fmt:message key="final" />
                  </button>
              </td>
            </tr>

          </table>
        </center>
        <!-- Final de no mostrar flux si es nova plantilla -->
      </c:if>

    </div>

  </div>

</form:form>

<%--  --------------------------------------------------- --%>
<%--  ---- MODAL PER LA CREACIO D'USUARIS EXTERNS ------- --%>
<%--  --------------------------------------------------- --%>
<%@ include file="/WEB-INF/jsp/flux/creacioUsuariExternModal.jsp"%>


<c:if test="${not fluxDeFirmesForm.nou && readOnly == false}">

  <div id="avis_true_false" class="modal hide fade" tabindex="-1" role="dialog">
  
   <div class="modal-dialog" role="document">
     <div class="modal-content">
  
    <div class="modal-header">
      <h4><fmt:message key="avis"/></h4>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p><fmt:message key="fluxDeFirmes.compartir.borrarusuaris"/></p>
    </div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary close"><fmt:message key="tancar"/></a>
    </div>
    
    </div>
    </div>
  </div>
  
  

  

  <%-- FORMULARI MODAL DE SELECCIO D'USUARIS  --%>
  <%-- REQUERIT:  Assignar un valor qualsevol com a valor inicial --%>
  <c:url var="theURL" value="${contexte}/afegirBlocDesDeModal"/>

  <%@ include file="/WEB-INF/jsp/common/seleccioUsuariModal.jsp"%>


  <%-- FORMULARI MODAL DE SELECCIO DE REVISORS D'UNA FIRMA  --%>
  <%-- REQUERIT:  Assignar un valor qualsevol com a valor inicial --%>
  <c:url var="theURL" value="${contexte}/afegirBlocDesDeModal"/>

  <c:set var="usuarimodalconfig" value="Revisor" />
  <c:set var="seleccioUsuariForm" value="${seleccioUsuariRevisorForm}" />
  <%@ include file="/WEB-INF/jsp/common/seleccioUsuariModal.jsp"%>


  <script type="text/javascript">
    
    
  function afegirBlocSelectUser(blocOrdre) {
      
      document.getElementById("seleccioUsuariForm").action='<c:url value="${contexte}/afegirBlocDesDeModal"/>';
      document.getElementById("param1").value = blocOrdre;
      document.getElementById("param2").value = blocOrdre;
      
      openSelectUserDialog();
      
      return false;
  }
  
  function afegirFirmaSelectUser(blocID) {
      
      document.getElementById("seleccioUsuariForm").action='<c:url value="${contexte}/afegirFirmaDesDeModal"/>';
      document.getElementById("param1").value = blocID;
      document.getElementById("param2").value = "";
      
      openSelectUserDialog();
      
      return false;
  }
  
  function afegirRevisorDeFirma(firmaID, destinatariID) {
    <%-- alert("FirmaID = " + firmaID); --%>
    document.getElementById("seleccioUsuariRevisorForm").action='<c:url value="${contexte}/afegirRevisorDesDeModal"/>';
    document.getElementById("param1").value = firmaID;
    document.getElementById("param2").value = destinatariID;
    openSelectUserRevisorDialog();
    return false;
  }
  
  
  function eliminarRevisor(blocID, firmaID, revisorID) {
    document.getElementById('blocID').value = blocID;
    document.getElementById('firmaID').value = firmaID;
    document.getElementById('revisorID').value = revisorID;
    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/eliminarRevisor" />";
    document.fluxDeFirmesForm.submit();
  }
  

  
  function definirMotiuDeFirma(firmaid) {
    var motiu = prompt('<fmt:message key="firma.motiudefirma.definir.dialog"/>');

    if (motiu == null || motiu == "") {
      <%-- // No feim res --%>
    } else {
      document.getElementById('motiu').value = motiu;
      document.getElementById('firmaID').value = firmaid;
      document.fluxDeFirmesForm.action = "<c:url value="${contexte}/definirmotiu" />";
      document.fluxDeFirmesForm.submit();
    } 
  }
  
  
  function modificarMotiuDeFirma(firmaid, valoractual) {
    var motiu = prompt('<fmt:message key="firma.motiudefirma.modificar.dialog"/>', valoractual);


    if (motiu == null) {
      <%-- No fer res s'ha espitjat CANCEL --%>
    } else if (motiu == "") {
      <%-- // Esborrar motiu --%>
      document.getElementById('firmaID').value = firmaid;
      document.fluxDeFirmesForm.action = "<c:url value="${contexte}/esborrarmotiu" />";
      document.fluxDeFirmesForm.submit();
    } else {
      document.getElementById('motiu').value = motiu;
      document.getElementById('firmaID').value = firmaid;
      document.fluxDeFirmesForm.action = "<c:url value="${contexte}/definirmotiu" />";
      document.fluxDeFirmesForm.submit();
    } 
  }
  
  
  
  function ferRevisorObligatori(blocID, firmaID, revisorID) {
    document.getElementById('blocID').value = blocID;
    document.getElementById('firmaID').value = firmaID;
    document.getElementById('revisorID').value = revisorID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/ferRevisorObligatori" />";
    document.fluxDeFirmesForm.submit();
  }


  function changeMinimDeRevisors(blocID, firmaID, minimDeRevisors) {
    document.getElementById('blocID').value = blocID;
    document.getElementById('firmaID').value = firmaID;
    <%-- Reutilitzam minimDeFirmes per minimDeRevisors--%>
    document.getElementById('minimDeFirmes').value = minimDeRevisors;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/changeMinimDeRevisors" />";
    document.fluxDeFirmesForm.submit();
    
  }

  function changeMinimDeFirmesNum(blocID, minimDeFirmes) {
	    	    
    document.getElementById('blocID').value = blocID;
    document.getElementById('minimDeFirmes').value = minimDeFirmes;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/canviMinimDeFirmes" />";
    document.fluxDeFirmesForm.submit();
  }


  function ferFirmaObligatoria(blocID,firmaID) {
	document.getElementById('blocID').value = blocID;
    document.getElementById('firmaID').value = firmaID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/ferFirmaObligatoria" />";
    document.fluxDeFirmesForm.submit();
  }

  
  function eliminarFirma(blocID, firmaID) {
    document.getElementById('blocID').value = blocID;
    document.getElementById('firmaID').value = firmaID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/eliminarFirma" />";
    document.fluxDeFirmesForm.submit();
  }

  function eliminarBloc(blocID) {
    document.getElementById('blocID').value = blocID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/eliminarBloc" />";
    document.fluxDeFirmesForm.submit();
  }
 
  
  </script>

  </c:if>

  <c:if test="${readOnly == false}">
  
  <script type="text/javascript">
  
  function onChangeCompartir(theComboBox) {

    
    <sec:authorize access="hasRole('ROLE_ADEN')">

    <%-- Només plantilles de usuari-entitat i de en edició --%> 
    <c:if test="${usuariEntitat == 'true' && !fluxDeFirmesForm.nou }" >
	    
    var value;
    value = theComboBox.options[theComboBox.selectedIndex].value;

    <c:if test="${empty fluxDeFirmesForm.plantillaFluxDeFirmes.compartir }" >

      if (value == 'true') { // tothom 
    	$('#avis_true_false').modal('show');
        return;
      }

      if (value == 'false') { // només jo
    	$('#avis_true_false').modal('show');
        return;
      }

    </c:if>
    

    <c:if test="${not empty fluxDeFirmesForm.plantillaFluxDeFirmes.compartir}" >

    if (value == 'true' || value == 'false') {
      // No fer res
    } else {      
      alert("<fmt:message key="fluxDeFirmes.compartir.ambllista"></fmt:message>")
    }
 
    </c:if>

    </c:if>
    </sec:authorize>
       
  }

  </script>
  </c:if>
