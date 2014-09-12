<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFavoritFields" className="es.caib.portafib.model.fields.UsuariEntitatFavoritFields"/>

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
      
      
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFavoritFields.ID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariEntitatFavorit.ID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="IDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="IDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFavoritFields.ORIGENID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariEntitatFavorit.origenID" var="origenID" />
              <fmt:message key="genapp.form.searchby" var="cercaperorigenID" >                
                 <fmt:param value="${origenID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${origenID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperorigenID}" path="origenID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFavoritFields.FAVORITID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariEntitatFavorit.favoritID" var="favoritID" />
              <fmt:message key="genapp.form.searchby" var="cercaperfavoritID" >                
                 <fmt:param value="${favoritID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${favoritID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfavoritID}" path="favoritID" />
            </div>


        </c:if>

      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
