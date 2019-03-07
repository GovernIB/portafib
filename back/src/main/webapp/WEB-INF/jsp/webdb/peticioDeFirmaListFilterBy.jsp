<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="well formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="pull-right">

           <a class="pull-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="icon-remove"></i></a>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-primary pull-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.PETICIODEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.peticioDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="peticioDeFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="peticioDeFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.TITOL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.titol" var="titol" />
              <fmt:message key="genapp.form.searchby" var="cercapertitol" >                
                 <fmt:param value="${titol}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${titol}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertitol}" path="titol" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.MOTIU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.motiu" var="motiu" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiu" >                
                 <fmt:param value="${motiu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiu}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiu}" path="motiu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.tipusDocumentID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusDocumentIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusDocumentIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.descripcioTipusDocument" var="descripcioTipusDocument" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcioTipusDocument" >                
                 <fmt:param value="${descripcioTipusDocument}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcioTipusDocument}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcioTipusDocument}" path="descripcioTipusDocument" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.posicioTaulaFirmesID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="posicioTaulaFirmesIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="posicioTaulaFirmesIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.DATASOLICITUD)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.dataSolicitud" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataSolicitudDesde" class="input-append">
                <form:input cssClass="input-large" path="dataSolicitudDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataSolicitudDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataSolicitudFins" class="input-append">
                <form:input cssClass="input-large" path="dataSolicitudFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataSolicitudFins').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.DATAFINAL)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.dataFinal" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataFinalDesde" class="input-append">
                <form:input cssClass="input-large" path="dataFinalDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFinalDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataFinalFins" class="input-append">
                <form:input cssClass="input-large" path="dataFinalFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFinalFins').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.DATACADUCITAT)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.dataCaducitat" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataCaducitatDesde" class="input-append">
                <form:input cssClass="input-large" path="dataCaducitatDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCaducitatDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataCaducitatFins" class="input-append">
                <form:input cssClass="input-large" path="dataCaducitatFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCaducitatFins').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.tipusOperacioFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusOperacioFirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusOperacioFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.TIPUSFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.tipusFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.algorismeDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="algorismeDeFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="algorismeDeFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.tipusEstatPeticioDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusEstatPeticioDeFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusEstatPeticioDeFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.MOTIUDEREBUIG)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.motiuDeRebuig" var="motiuDeRebuig" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiuDeRebuig" >                
                 <fmt:param value="${motiuDeRebuig}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiuDeRebuig}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiuDeRebuig}" path="motiuDeRebuig" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.IDIOMAID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.idiomaID" var="idiomaID" />
              <fmt:message key="genapp.form.searchby" var="cercaperidiomaID" >                
                 <fmt:param value="${idiomaID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${idiomaID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperidiomaID}" path="idiomaID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.PRIORITATID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.prioritatID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="prioritatIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="prioritatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.FLUXDEFIRMESID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.fluxDeFirmesID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="fluxDeFirmesIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="fluxDeFirmesIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.solicitantUsuariAplicacioID" var="solicitantUsuariAplicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantUsuariAplicacioID" >                
                 <fmt:param value="${solicitantUsuariAplicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantUsuariAplicacioID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantUsuariAplicacioID}" path="solicitantUsuariAplicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.REMITENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.remitentNom" var="remitentNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperremitentNom" >                
                 <fmt:param value="${remitentNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${remitentNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperremitentNom}" path="remitentNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.remitentDescripcio" var="remitentDescripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperremitentDescripcio" >                
                 <fmt:param value="${remitentDescripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${remitentDescripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperremitentDescripcio}" path="remitentDescripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.EXPEDIENTCODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.expedientCodi" var="expedientCodi" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpedientCodi" >                
                 <fmt:param value="${expedientCodi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expedientCodi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpedientCodi}" path="expedientCodi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.EXPEDIENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.expedientNom" var="expedientNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpedientNom" >                
                 <fmt:param value="${expedientNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expedientNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpedientNom}" path="expedientNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.EXPEDIENTURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.expedientUrl" var="expedientUrl" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpedientUrl" >                
                 <fmt:param value="${expedientUrl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expedientUrl}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpedientUrl}" path="expedientUrl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.PROCEDIMENTCODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.procedimentCodi" var="procedimentCodi" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentCodi" >                
                 <fmt:param value="${procedimentCodi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentCodi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentCodi}" path="procedimentCodi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.PROCEDIMENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.procedimentNom" var="procedimentNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentNom" >                
                 <fmt:param value="${procedimentNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentNom}" path="procedimentNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.INFORMACIOADDICIONAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.informacioAddicional" var="informacioAddicional" />
              <fmt:message key="genapp.form.searchby" var="cercaperinformacioAddicional" >                
                 <fmt:param value="${informacioAddicional}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${informacioAddicional}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinformacioAddicional}" path="informacioAddicional" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.informacioAddicionalAvaluable" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="informacioAddicionalAvaluableDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="informacioAddicionalAvaluableFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.CUSTODIAINFOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.custodiaInfoID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.solicitantUsuariEntitat1ID" var="solicitantUsuariEntitat1ID" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantUsuariEntitat1ID" >                
                 <fmt:param value="${solicitantUsuariEntitat1ID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantUsuariEntitat1ID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantUsuariEntitat1ID}" path="solicitantUsuariEntitat1ID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.solicitantUsuariEntitat2ID" var="solicitantUsuariEntitat2ID" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantUsuariEntitat2ID" >                
                 <fmt:param value="${solicitantUsuariEntitat2ID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantUsuariEntitat2ID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantUsuariEntitat2ID}" path="solicitantUsuariEntitat2ID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirma.solicitantUsuariEntitat3ID" var="solicitantUsuariEntitat3ID" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantUsuariEntitat3ID" >                
                 <fmt:param value="${solicitantUsuariEntitat3ID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantUsuariEntitat3ID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantUsuariEntitat3ID}" path="solicitantUsuariEntitat3ID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.AVISWEB)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.avisWeb" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="avisWebDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="avisWebFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirma.segellatDeTemps" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="segellatDeTempsDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="segellatDeTempsFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
