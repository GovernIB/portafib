<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
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
  method="${method}" enctype="multipart/form-data">

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

        <br/>
  
        <%-- Final de if de onlyFlux --%>
      </c:if>


      <!-- No mostram el flux si estam en NOU  -->
      <c:if test="${!fluxDeFirmesForm.nou}">
      
        <%-- XYZ ZZZ Si estam amb flux rest llavors mostrar boto de OK i Cancelar  --%>
        
        <c:if test="${isPlantillaRest && not readOnly}">
        <center>
        <a href="<c:url value="${contexte}/finalRestOK"/>" class="btn btn-large btn-primary disabled"> <fmt:message key="genapp.save" /> </a>
        <a href="<c:url value="${contexte}/finalRestCanceled"/>" class="btn btn-large btn-primary disabled"><fmt:message key="genapp.cancel" /></a>
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


          <%-- IMPORTANT: No eliminar cellpadding ni cellspacing ja que sino apareixen espais entre files. --%>
          <table cellpadding="0" cellspacing="0" style="padding:0px 0px 0px 0px; margin: 0px 0px 0px 0px; border-collapse: separate; border-spacing: 0px;">

            <%-- CASELLA DE INICI  --%>
            <tr style="padding:0px; margin: 0px;" align="center">
              <td colspan="2" style="padding:0px; margin: 0px">
                  <button class="btn" type="button">
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
                    <button type="button" class="btn btn-primary btn-mini" onclick="javascript:afegirBlocSelectUser('0')" title="<fmt:message key="blocdefirmes.nou"/>">
                        <i class="icon-plus-sign icon-white"></i>
                        &nbsp;
                        <fmt:message key="blocdefirmes.nou" />                        
                    </button>
                </td>
              </tr>
            </c:if>

            <c:forEach items="${fluxDeFirmesForm.fluxDeFirmes.blocDeFirmess}" var="bloc">

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

                                  <button type="button" class="btn btn-success btn-mini" onclick="javascript:afegirFirmaSelectUser('${bloc.blocDeFirmesID}')" title="<fmt:message key="firma.nova"/>">
                                    <i class="icon-plus-sign icon-white"></i> <fmt:message key="firma.nova" />                        
                                  </button>
                                  

                                  
                                  <%-- <br /> Calcul del menor valor admisible per MinimDeFirmes --%>
                                  
                                  <c:set var="minimposible" value="0"/>
                                  <c:set var="tenoobligatories" value="0"/>
                                  <c:forEach items="${bloc.firmas}" var="firma">
                                    <c:if test="${not firma.obligatori}">
                                      <c:set var="tenoobligatories" value="1"/>
                                    </c:if>
                                    <c:if test="${firma.obligatori}">
                                      <c:set var="minimposible" value="${minimposible + 1}"/>
                                    </c:if>
                                  </c:forEach>
                                  
                                  <c:set var="minimposible" value="${minimposible + tenoobligatories}"/>
                                  
                                  <div class="btn-group" style="padding-top: 2px;">
                                    <button
                                      class="btn btn-info btn-mini dropdown-toggle"
                                      data-toggle="dropdown"
                                      title="<fmt:message key="blocDeFirmes.minimDeFirmes"/>">
                                      <fmt:message key="blocDeFirmes.minimDeFirmes" />
                                      : ${bloc.minimDeFirmes}<span class="caret"></span>
                                    </button>

                                    <ul class="dropdown-menu">
                                      <c:forEach var="i" begin="${minimposible}"  end="${fn:length(bloc.firmas)}">
                                        <li><a href="#"
                                          onclick="changeMinimDeFirmesNum('${bloc.blocDeFirmesID}', '${i}')">${i}</a>
                                        </li>
                                      </c:forEach>
                                    </ul>
                                  </div>
                                  

                                  <c:if test="${fn:length(fluxDeFirmesForm.fluxDeFirmes.blocDeFirmess) > 1}">
                                    <div style="padding-top: 2px;display: inline-block;">
                                      <button class="btn btn-danger btn-mini"
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
                                        ${firma.usuariEntitat.usuariPersona.nom}&nbsp;
                                        ${firma.usuariEntitat.usuariPersona.llinatges}
                                        <br>
                                      </small>
                                    </c:when>
                                     
                                    <%--  USUARI EXTERN --%>
                                    <c:when test = "${not empty firma.usuariExternNom}">
                                      <i>${firma.usuariExternNom}&nbsp;${firma.usuariExternLlinatges}</i><br/>
                                      <small>
                                        <i class="icon-envelope"></i>${firma.usuariExternEmail}<br/>
                                        ${firma.usuariEntitat.usuariPersona.nif}
                                      </small>
                                    </c:when>
                                    
                                    <%--  USUARI INTERN --%>
                                    <c:when test = "${empty firma.usuariExternNom}">
                                      ${firma.usuariEntitat.usuariPersona.nom}&nbsp;${firma.usuariEntitat.usuariPersona.llinatges}<br/>
                                      ${firma.usuariEntitat.usuariPersona.nif}<br/>
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
                                    <button class="btn btn-danger btn-mini"
                                      title="<fmt:message key="genapp.delete.item" ><fmt:param><fmt:message key="firma.firma"/></fmt:param></fmt:message>"
                                      onclick="eliminarFirma('${bloc.blocDeFirmesID}','${firma.firmaID}')">
                                      &nbsp;<i class="icon-trash icon-white"></i>&nbsp;
                                    </button>
                                    </c:if>

                                    <c:if test="${readOnly == false}">

                                      <button type="button" class="btn btn-success btn-mini" onclick="javascript:afegirRevisorDeFirma('${firma.firmaID}')" title="<fmt:message key="firma.afegirrevisor"/>">
                                         &nbsp;<i class="icon-plus-sign icon-white"></i>&nbsp;                      
                                      </button>
                                      
                                      <%-- Motiu de firma--%>
                                      <c:if test="${empty firma.motiu}">
                                        <button type="button" class="btn btn-warning btn-mini" onclick="javascript:definirMotiuDeFirma('${firma.firmaID}')" title="<fmt:message key="firma.motiudefirma.definir"/>">
                                          &nbsp;<i class="icon-bullhorn icon-white"></i>&nbsp;
                                        </button>
                                      </c:if>
                                      <c:if test="${not empty firma.motiu}">
                                        <button type="button" class="btn btn-success btn-mini" onclick="javascript:modificarMotiuDeFirma('${firma.firmaID}','${fn:escapeXml(firma.motiu)}')" title="<fmt:message key="firma.motiudefirma"/> ${fn:escapeXml(firma.motiu)}">
                                          &nbsp;<i class="icon-bullhorn icon-white"></i>&nbsp;
                                        </button>
                                      </c:if>
                                      
                                      <%-- Mínim de Revisors de Firmes --%>
                                      <c:if test="${ not empty firma.revisorDeFirmas}">
                                        <br/>
                                       
                                        <c:set var="minimposible" value="0"/>
                                        <c:set var="tenoobligatories" value="0"/>
                                        <c:forEach var="revisor" items="${firma.revisorDeFirmas}">
                                          <c:if test="${not revisor.obligatori}">
                                            <c:set var="tenoobligatories" value="1"/>
                                          </c:if>
                                          <c:if test="${revisor.obligatori}">
                                            <c:set var="minimposible" value="${minimposible + 1}"/>
                                          </c:if>
                                        </c:forEach>
                                  
                                        <c:set var="minimposible" value="${minimposible + tenoobligatories}"/>
                                        
                                        
                                        <div class="btn-group" style="padding-top: 2px;">
                                          <button
                                            class="btn btn-info btn-mini dropdown-toggle"
                                            data-toggle="dropdown"
                                            title="<fmt:message key="firma.minimDeRevisors"/>">
                                            <fmt:message key="firma.minimDeRevisors" />
                                            : ${firma.minimDeRevisors}<span class="caret"></span>
                                          </button>
      
                                          <ul class="dropdown-menu">
                                            <c:forEach var="i" begin="${minimposible}" end="${fn:length(firma.revisorDeFirmas)}">
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
                                  <%-- XYZ ZZZ FALTA BACKGROUND segons estat --%>
                                  <div class="radius" style="${background} ${backgroundimage} float:right; border: 2px solid #ff0000; margin: 4px; padding: 8px; text-align: left">
                                     
                                     <label class="checkbox inline" style="padding-left: 0px;padding-top: 0px;">
                                    <center><b><fmt:message key="revisorDeFirma.revisorDeFirma" /></b>
                                    
                                    </center>
                                    <i>${revisor.usuariEntitat.usuariPersona.nom}&nbsp${revisor.usuariEntitat.usuariPersona.llinatges}<br/>
                                    ${revisor.usuariEntitat.usuariPersona.nif}</i>
                                    
                                    
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
                                    <button class="btn btn-danger btn-mini"
                                        title="<fmt:message key="genapp.delete.item" ><fmt:param><fmt:message key="revisorDeFirma.revisorDeFirma"/></fmt:param></fmt:message>"
                                        onclick="eliminarRevisor('${bloc.blocDeFirmesID}','${firma.firmaID}','${revisor.revisorDeFirmaID}')">
                                        &nbsp;<i class="icon-trash icon-white"></i>&nbsp;
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

                   <button type="button" class="btn btn-primary btn-mini" onclick="javascript:afegirBlocSelectUser('${bloc.ordre + 5}')" title="<fmt:message key="blocdefirmes.nou"/>">
                        <i class="icon-plus-sign icon-white"></i>
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
                  <button class="btn" type="button">
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


<c:if test="${not fluxDeFirmesForm.nou && readOnly == false}">

  <div id="avis_true_false" class="modal hide fade">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="avis"></fmt:message></h3>
    </div>
    <div class="modal-body">
      <p><fmt:message key="fluxDeFirmes.compartir.borrarusuaris"></fmt:message></p>
    </div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary close"><fmt:message key="tancar"></fmt:message></a>
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
      
      openSelectUserDialog();
      
      return false;
  }
  
  function afegirFirmaSelectUser(blocID) {
      
      document.getElementById("seleccioUsuariForm").action='<c:url value="${contexte}/afegirFirmaDesDeModal"/>';
      document.getElementById("param1").value = blocID;
      
      openSelectUserDialog();
      
      return false;
  }
  
  function afegirRevisorDeFirma(firmaID) {
    <%-- alert("FirmaID = " + firmaID); --%>
    document.getElementById("seleccioUsuariRevisorForm").action='<c:url value="${contexte}/afegirRevisorDesDeModal"/>';
    document.getElementById("param1").value = firmaID;
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
    var motiu = prompt("<fmt:message key="firma.motiudefirma.definir.dialog"></fmt:message>");

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
    var motiu = prompt("<fmt:message key="firma.motiudefirma.modificar.dialog"></fmt:message>", valoractual);


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
