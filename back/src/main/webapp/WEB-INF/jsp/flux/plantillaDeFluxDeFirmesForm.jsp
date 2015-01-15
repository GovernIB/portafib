<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<style>
.radius {
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
}
</style>

<c:set var="contexte" value="${fluxDeFirmesForm.contexte}" />

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
          <td><form:errors path="usuariEntitatPrimeraFirma"
              cssClass="errorField alert alert-error" /> <form:select
              cssClass="input-xxlarge" path="usuariEntitatPrimeraFirma">
              <c:forEach items="${fluxDeFirmesForm.listOfUsuariEntitatFavorit}"
                var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
              </c:forEach>
            </form:select></td>
        </tr>
      </c:if>

        <%@include file="../webdb/fluxDeFirmesFormCorePost.jsp" %>

        <%@include file="../webdb/fluxDeFirmesFormButtons.jsp" %>

        <br/>
  
        <%-- Final de if de onlyFlux --%>
      </c:if>


      <!-- No mostram el flux si estam en NOU  -->
      <c:if test="${!fluxDeFirmesForm.nou}">
        <center>
          <c:if test="${readOnly == false || onlyFlux == false}">            
            <hr />
            <p class="text-error">
              <fmt:message key="avis.edicioflux" />
            </p>

            <br>
          </c:if>

          <table cellpadding="0" cellspacing="0" style="padding:0px; margin: 0px">

            <%-- CASELLA DE INICI  --%>
            <tr style="padding:0px; margin: 0px">
              <td colspan="2" style="padding:0px; margin: 0px">
                <center>
                  <button class="btn" type="button">
                    <fmt:message key="inici" />
                  </button>
                </center>
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
               <tr style="text-align: center;">
                <td colspan="2" >

                    <div class="btn-group" style="text-align: left">
                      <button class="btn btn-primary btn-mini dropdown-toggle"
                        data-toggle="dropdown" title="<fmt:message key="blocdefirmes.nou"/>">
                        <i class="icon-plus-sign icon-white"></i>&nbsp;
                        <fmt:message key="blocdefirmes.nou" />
                        &nbsp;<span class="caret"></span>
                      </button>
                      <c:set var="lastItem" value="" />
                      <ul class="dropdown-menu">
                        <c:forEach items="${fluxDeFirmesForm.listOfUsuariEntitatFavorit}"
                          var="tmp">
                          <c:if
                            test="${(fn:startsWith(lastItem, '(*)') == true) && fn:startsWith(tmp.value, '(*)') == false}">
                            <li class="divider"></li>
                          </c:if>
                          <c:set var="lastItem" value="${tmp.value}" />
                          <li><a href="#" onclick="afegirBloc('0','${tmp.key}')">${tmp.value}</a>
                          </li>
                        </c:forEach>
                      </ul>
                    </div>

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

                    <div class="radius"
                      style="${background} max-width:600px; border: 2px solid #0000ff; margin: 0px; padding: 10px; display: inline-block">
                      <c:if test="${pfi:isDesenvolupament()}">
                       ${bloc.blocDeFirmesID}
                      </c:if>
                      <table width="100%">

                        <tr>

                          <%-- ACCIONS SOBRE EL BLOC --%>
                          <c:if test="${readOnly == true}">
                            <c:if test="${fn:length(bloc.firmas) != bloc.minimDeFirmes}">
                              <small><fmt:message key="blocDeFirmes.minimDeFirmes" />: ${bloc.minimDeFirmes}</small>
                            </c:if>
                          </c:if>
                          <c:if test="${readOnly == false}">
                            <td>
                              <div class="radius"
                                style="float: right; border: 2px solid #0000ff; margin: 4px; padding: 8px;">
                                <center>
                                  <div class="btn-group" style="text-align: left">
                                    <button
                                      class="btn btn-success btn-mini dropdown-toggle"
                                      data-toggle="dropdown"
                                      title="<fmt:message key="firma.nova"/>">
                                      <i class=" icon-plus-sign icon-white"></i>&nbsp;
                                      <fmt:message key="firma.nova" />
                                      &nbsp;<span class="caret"></span>
                                    </button>
                                    <c:set var="lastItem" value="" />
                                    <ul class="dropdown-menu">
                                      <c:forEach
                                        items="${fluxDeFirmesForm.listOfUsuariEntitatFavorit}"
                                        var="tmp">
                                        <c:if
                                          test="${(fn:startsWith(lastItem, '(*)') == true) && fn:startsWith(tmp.value, '(*)') == false}">
                                          <li class="divider"></li>
                                        </c:if>
                                        <c:set var="lastItem" value="${tmp.value}" />
                                        <li><a href="#"
                                          onclick="afegirFirma('${bloc.blocDeFirmesID}','${tmp.key}')">${tmp.value}</a>
                                        </li>
                                      </c:forEach>
                                    </ul>
                                  </div>
                                  <!-- /btn-group -->

                                  <br />
                                  <%-- Calcul del menor valor admisible per MinimDeFirmes --%>
                                  
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
                                      class="btn btn-primary btn-mini dropdown-toggle"
                                      data-toggle="dropdown"
                                      title="<fmt:message key="blocDeFirmes.minimDeFirmes"/>">
                                      <fmt:message key="blocDeFirmes.minimDeFirmes" />
                                      : ${bloc.minimDeFirmes}<span class="caret"></span>
                                    </button>

                                    <ul class="dropdown-menu">
                                      <c:forEach var="i" begin="${minimposible}"
                                        end="${fn:length(bloc.firmas)}">
                                        <li><a href="#"
                                          onclick="changeMinimDeFirmesNum('${bloc.blocDeFirmesID}', '${i}')">${i}</a>
                                        </li>
                                      </c:forEach>
                                    </ul>
                                  </div>
                                  <!-- /btn-group -->

                                  <br />

                                  <c:if test="${fn:length(fluxDeFirmesForm.fluxDeFirmes.blocDeFirmess) > 1}">
                                    <div style="padding-top: 2px;">
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

                                </center>
                              </div>
                             </td>
                          </c:if>

                          <%-- LLISTAT DE FIRMES DEL BLOC --%>
                          <td width="100%">
                            <center>
                              <c:forEach items="${bloc.firmas}" var="firma">

                                <c:set var="background"
                                  value="${fluxDeFirmesForm.backgroundColorsOfFirma[firma.firmaID]}" />
                                <c:if test="${not empty background}">
                                  <c:set var="background">background:#${background};</c:set>
                                </c:if>
                                
                                <c:if test="${firma.usuariEntitat.actiu == false}">
                                  <c:set var="backgroundimage">background-image: url(<c:url value='/img/userdisabled.png'/>);background-repeat: no-repeat;background-position: center; </c:set>                                  
                                </c:if>

                                <div class="radius"
                                  style="${background} ${backgroundimage} float:left; border: 2px solid #00ff00; margin: 4px; padding: 8px; text-align: left">
                                  <c:if test="${pfi:isDesenvolupament()}">
                                  ${firma.firmaID}
                                  </c:if>

                                  <c:if test="${not empty firma.usuariEntitat.carrec}">
                                    <b>${firma.usuariEntitat.carrec}</b>
                                    <br />
                                    <small>
                                      ${firma.usuariEntitat.usuariPersona.nom}&nbsp;${firma.usuariEntitat.usuariPersona.llinatges}
                                       <br>
                                    </small>
                                  </c:if>
                                  <c:if test="${empty firma.usuariEntitat.carrec}">
                                      ${firma.usuariEntitat.usuariPersona.nom}&nbsp;${firma.usuariEntitat.usuariPersona.llinatges}<br/>
                                      ${firma.usuariEntitat.usuariPersona.nif}<br/>
                                  </c:if>

                                  <table align="left">
                                    <tr>

                                    <c:if test="${(fn:length(bloc.firmas) > 1) && readOnly == false}">
                                    <td>
                                    <button class="btn btn-danger btn-mini"
                                      title="<fmt:message key="eliminar.firma"/>"
                                      onclick="eliminarFirma('${bloc.blocDeFirmesID}','${firma.firmaID}')">
                                      <fmt:message key="genapp.delete.item" >
                                      <fmt:param><fmt:message key="firma"/></fmt:param>
                                      </fmt:message>
                                    </button>
                                    </td>
                                    <td>&nbsp;</td>
                                    </c:if>
                                    
                                    <td>                       
                                    <label class="checkbox inline">
                                      <input type="checkbox"
                                      ${firma.obligatori? 'checked="checked" ': ''}
                                      ${readOnly? 'disabled="disabled"' : ''} 
                                      onclick="ferFirmaObligatoria('${bloc.blocDeFirmesID}','${firma.firmaID}')" />
                                        <fmt:message key="firma.obligatori" />
                                    </label>
                                    </td>

                                    </tr>
                                  </table>

                                </div>
                              </c:forEach>

                            </center></td>
                        </tr>
                      </table>

                    </div>
                  
                  </td>
              </tr>

              <%-- AFEGIR UN BLOC POSTERIOR --%>
              <c:if test="${readOnly == false}">
                <c:out value="${blocseparator}" escapeXml="false" />
                <tr>
                  <td colspan="2">
                    <center>
                      <div class="btn-group" style="text-align: left">
                        <button class="btn btn-primary btn-mini dropdown-toggle"
                          data-toggle="dropdown" title="<fmt:message key="blocdefirmes.nou"/>">
                          <i class="icon-plus-sign icon-white"></i>&nbsp;
                          <fmt:message key="blocdefirmes.nou" />
                          &nbsp;<span class="caret"></span>
                        </button>
                        <c:set var="lastItem" value="" />
                        <ul class="dropdown-menu">
                          <c:forEach
                            items="${fluxDeFirmesForm.listOfUsuariEntitatFavorit}"
                            var="tmp">
                            <c:if
                              test="${(fn:startsWith(lastItem, '(*)') == true) && fn:startsWith(tmp.value, '(*)') == false}">
                              <li class="divider"></li>
                            </c:if>
                            <c:set var="lastItem" value="${tmp.value}" />
                            <li><a href="#"
                              onclick="afegirBloc('${bloc.ordre + 5}','${tmp.key}')">${tmp.value}</a>
                            </li>
                          </c:forEach>
                        </ul>
                      </div>

                    </center></td>
                </tr>
              </c:if>
            </c:forEach>

            <%-- CASELLA DE FINAL  --%>
            <c:out value="${blocseparator}" escapeXml="false" />
            <tr>
              <td colspan="2">
                <center>
                  <button class="btn" type="button">
                    <fmt:message key="final" />
                  </button>
                </center>
              </td>
            </tr>

          </table>
        </center>
        <!-- Final de no mostrar flux si es nova plantilla -->
      </c:if>

    </div>

  </div>


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


  



  <c:if test="${readOnly == false}">
    <script type="text/javascript">


  function afegirFirma(blocID, usuariEntitatID) { 

	document.getElementById('blocID').value = blocID;
	document.getElementById('usuariEntitatID').value = usuariEntitatID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/afegirFirma" />";
	document.fluxDeFirmesForm.submit();
  }


  function afegirBloc(blocOrdre, usuariEntitatID) { 

	document.getElementById('blocOrdre').value = blocOrdre;
	document.getElementById('usuariEntitatID').value = usuariEntitatID;

    document.fluxDeFirmesForm.action = "<c:url value="${contexte}/afegirBloc" />";
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


</form:form>


