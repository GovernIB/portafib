<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FirmaFields" className="es.caib.portafib.model.fields.FirmaFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#" title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline'; return false;"> <i aria-hidden="true" class="far fa-window-close"></i><span class="sr-only"><fmt:message key="genapp.form.hidefilter"/></span></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.FIRMAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_firmaID" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.firmaID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmaIDDesde"  aria-labelledby="firma_firmaID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="firmaIDFins"  aria-labelledby="firma_firmaID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.DESTINATARIID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="destinatariID" style="display: inline;">
              <fmt:message key="firma.destinatariID" var="destinatariID" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatariID" >                
                 <fmt:param value="${destinatariID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatariID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatariID}" path="destinatariID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.BLOCDEFIRMAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_blocDeFirmaID" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.blocDeFirmaID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="blocDeFirmaIDDesde"  aria-labelledby="firma_blocDeFirmaID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="blocDeFirmaIDFins"  aria-labelledby="firma_blocDeFirmaID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.OBLIGATORI)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_obligatori" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.obligatori" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="obligatoriDesde"  aria-labelledby="firma_obligatori" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="obligatoriFins"  aria-labelledby="firma_obligatori" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.NUMFIRMADOCUMENT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_numFirmaDocument" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.numFirmaDocument" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="numFirmaDocumentDesde"  aria-labelledby="firma_numFirmaDocument" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="numFirmaDocumentFins"  aria-labelledby="firma_numFirmaDocument" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.CAIXAPAGINA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_caixaPagina" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.caixaPagina" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="caixaPaginaDesde"  aria-labelledby="firma_caixaPagina" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="caixaPaginaFins"  aria-labelledby="firma_caixaPagina" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.CAIXAX)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_caixaX" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.caixaX" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="caixaXDesde"  aria-labelledby="firma_caixaX" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="caixaXFins"  aria-labelledby="firma_caixaX" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.CAIXAY)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_caixaY" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.caixaY" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="caixaYDesde"  aria-labelledby="firma_caixaY" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="caixaYFins"  aria-labelledby="firma_caixaY" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.CAIXAAMPLE)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_caixaAmple" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.caixaAmple" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="caixaAmpleDesde"  aria-labelledby="firma_caixaAmple" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="caixaAmpleFins"  aria-labelledby="firma_caixaAmple" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.CAIXAALT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_caixaAlt" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.caixaAlt" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="caixaAltDesde"  aria-labelledby="firma_caixaAlt" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="caixaAltFins"  aria-labelledby="firma_caixaAlt" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.NUMEROSERIECERTIFICAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_numeroSerieCertificat" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.numeroSerieCertificat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="numeroSerieCertificatDesde"  aria-labelledby="firma_numeroSerieCertificat" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="numeroSerieCertificatFins"  aria-labelledby="firma_numeroSerieCertificat" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.EMISSORCERTIFICAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="emissorCertificat" style="display: inline;">
              <fmt:message key="firma.emissorCertificat" var="emissorCertificat" />
              <fmt:message key="genapp.form.searchby" var="cercaperemissorCertificat" >                
                 <fmt:param value="${emissorCertificat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${emissorCertificat}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperemissorCertificat}" path="emissorCertificat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.NOMCERTIFICAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="nomCertificat" style="display: inline;">
              <fmt:message key="firma.nomCertificat" var="nomCertificat" />
              <fmt:message key="genapp.form.searchby" var="cercapernomCertificat" >                
                 <fmt:param value="${nomCertificat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nomCertificat}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernomCertificat}" path="nomCertificat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="firma_tipusEstatDeFirmaFinalID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="firma.tipusEstatDeFirmaFinalID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="firma_tipusEstatDeFirmaFinalID_select" path="tipusEstatDeFirmaFinalIDSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipusEstatDeFirmaFinalID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusEstatDeFirmaFinalIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#firma_tipusEstatDeFirmaFinalID_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "firma_tipusEstatDeFirmaFinalID_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "firma_tipusEstatDeFirmaFinalID_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.MOSTRARRUBRICA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_mostrarRubrica" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.mostrarRubrica" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="mostrarRubricaDesde"  aria-labelledby="firma_mostrarRubrica" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="mostrarRubricaFins"  aria-labelledby="firma_mostrarRubrica" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.MOTIU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="motiu" style="display: inline;">
              <fmt:message key="firma.motiu" var="motiu" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiu" >                
                 <fmt:param value="${motiu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiu}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiu}" path="motiu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.MINIMDEREVISORS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_minimDeRevisors" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.minimDeRevisors" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="minimDeRevisorsDesde"  aria-labelledby="firma_minimDeRevisors" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="minimDeRevisorsFins"  aria-labelledby="firma_minimDeRevisors" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.PERFILDEFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="perfilDeFirma" style="display: inline;">
              <fmt:message key="firma.perfilDeFirma" var="perfilDeFirma" />
              <fmt:message key="genapp.form.searchby" var="cercaperperfilDeFirma" >                
                 <fmt:param value="${perfilDeFirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${perfilDeFirma}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperperfilDeFirma}" path="perfilDeFirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariExternNom" style="display: inline;">
              <fmt:message key="firma.usuariExternNom" var="usuariExternNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariExternNom" >                
                 <fmt:param value="${usuariExternNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariExternNom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariExternNom}" path="usuariExternNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNLLINATGES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariExternLlinatges" style="display: inline;">
              <fmt:message key="firma.usuariExternLlinatges" var="usuariExternLlinatges" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariExternLlinatges" >                
                 <fmt:param value="${usuariExternLlinatges}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariExternLlinatges}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariExternLlinatges}" path="usuariExternLlinatges" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariExternEmail" style="display: inline;">
              <fmt:message key="firma.usuariExternEmail" var="usuariExternEmail" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariExternEmail" >                
                 <fmt:param value="${usuariExternEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariExternEmail}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariExternEmail}" path="usuariExternEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNIDIOMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariExternIdioma" style="display: inline;">
              <fmt:message key="firma.usuariExternIdioma" var="usuariExternIdioma" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariExternIdioma" >                
                 <fmt:param value="${usuariExternIdioma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariExternIdioma}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariExternIdioma}" path="usuariExternIdioma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNTOKEN)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariExternToken" style="display: inline;">
              <fmt:message key="firma.usuariExternToken" var="usuariExternToken" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariExternToken" >                
                 <fmt:param value="${usuariExternToken}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariExternToken}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariExternToken}" path="usuariExternToken" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="firma_usuariExternNivellSeguretat_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="firma.usuariExternNivellSeguretat" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="firma_usuariExternNivellSeguretat_select" path="usuariExternNivellSeguretatSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForUsuariExternNivellSeguretat}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.usuariExternNivellSeguretatSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#firma_usuariExternNivellSeguretat_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "firma_usuariExternNivellSeguretat_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "firma_usuariExternNivellSeguretat_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,FirmaFields.SIGNATUREPLUGINID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="firma_signaturePluginId" style="display: inline;">
              <span class="add-on"><fmt:message key="firma.signaturePluginId" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="signaturePluginIdDesde"  aria-labelledby="firma_signaturePluginId" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="signaturePluginIdFins"  aria-labelledby="firma_signaturePluginId" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
