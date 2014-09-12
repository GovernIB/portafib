<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaUsuariEntitatFields" className="es.caib.portafib.model.fields.PeticioDeFirmaUsuariEntitatFields"/>

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
      
      
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirmaUsuariEntitat.peticioDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="peticioDeFirmaIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="peticioDeFirmaIDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirmaUsuariEntitat.usuariEntitatID" var="usuariEntitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariEntitatID" >                
                 <fmt:param value="${usuariEntitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariEntitatID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariEntitatID}" path="usuariEntitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID" var="mitjaDeComunicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercapermitjaDeComunicacioID" >                
                 <fmt:param value="${mitjaDeComunicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${mitjaDeComunicacioID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermitjaDeComunicacioID}" path="mitjaDeComunicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioAdreza" var="mitjaDeComunicacioAdreza" />
              <fmt:message key="genapp.form.searchby" var="cercapermitjaDeComunicacioAdreza" >                
                 <fmt:param value="${mitjaDeComunicacioAdreza}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${mitjaDeComunicacioAdreza}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermitjaDeComunicacioAdreza}" path="mitjaDeComunicacioAdreza" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticioDeFirmaUsuariEntitat.avisWeb" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="avisWebDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="avisWebFins" />
              
            </div>


        </c:if>

      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
