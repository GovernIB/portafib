<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
<%@include file="../webdb/plantillaFluxDeFirmesFormTitle.jsp" %>

<sec:authorize access="hasRole('ROLE_ADEN')">
 <c:if test="${ empty plantillaFluxDeFirmesForm.plantillaFluxDeFirmes.compartir}">
<form:form modelAttribute="plantillaFluxDeFirmesForm" method="post"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${plantillaFluxDeFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <input type="hidden" id="userToDeleteFromPlantilla" name="userToDeleteFromPlantilla" value=""/>
  <input type="hidden" id="groupToDeleteFromPlantilla" name="groupToDeleteFromPlantilla" value=""/>

    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody> 
      <tr>
        <td>
          <label>
            <fmt:message key="fluxDeFirmes.nom" /> &nbsp;
          </label>
        </td>
        <td>
            <form:input readonly="true" cssClass="input-xxlarge"  maxlength="50" path="plantillaFluxDeFirmes.fluxDeFirmes.nom" />
        </td>
      </tr>    
    </tbody>
    </table>
  
  <div style="display: none">
  <%@include file="../webdb/plantillaFluxDeFirmesFormCorePre.jsp" %>
  <%@include file="../webdb/plantillaFluxDeFirmesFormCore.jsp" %>
  <%@include file="../webdb/plantillaFluxDeFirmesFormCorePost.jsp" %>
  </div>

    <div id="permisos_per_persona">


    <%--     PERMISOS PER  GRUPS  --%>

    <table>
    
      <tr>
        <td colspan="4">
            <center><b><fmt:message key="permisosplantilla.perusuari" /></b></center>
            <br/>
        </td>
      </tr>
       
      
      <tr>
    
       <td>
         <center><i><fmt:message key="permisosplantilla.listuser" /></i></center>
                
                
            <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
            <thead>
              <tr>
                <th><fmt:message key="usuariPersona.usuariPersona"></fmt:message> </th>
                <th><fmt:message key="genapp.actions" /> </th>
              </tr>
            </thead>
            <tbody>
              
              <c:forEach items="${plantillaFluxDeFirmesForm.plantillaFluxDeFirmes.permisUsuariPlantillas}" var="_permis">
              <tr>
                  <td>${pfi:getNom(_permis.usuariEntitat.usuariPersona)}</td>
                  <td>
                     <a class="btn btn-danger" href="#myModal"
                     onclick="openModalSubmit(deleteUserFromPlantilla('${_permis.usuariEntitatID}'),'show','plantillaFluxDeFirmesForm');"
                     title='<fmt:message key="genapp.delete" />'>
                     <i class="icon-trash icon-white"></i>
                     </a>
                   </td>
              </tr>
              </c:forEach>
              
            </tbody>
          </table>
       
       </td>
       
       <td style="width:16px;">&nbsp;</td>
       
       <td style="width:16px;border-left:1px solid;">&nbsp;</td>

       <td align="center" valign="middle">
         
         <i><fmt:message key="permisosplantilla.adduser"></fmt:message> </i><br/>

             <button type="button" class="btn btn-primary" onclick="javascript:openSelectUserDialog();" title="<fmt:message key="permisosplantilla.adduser"></fmt:message>" >
                <i class="icon-plus-sign icon-white"></i>
                <fmt:message key="permisosplantilla.adduser"></fmt:message>
             </button>

       </td>

      </tr>

      <tr>
          <td colspan="4">
              <hr />
              <br/>
          </td>
      </tr>
    
 
      <%--  PERMISOS PER  GRUPS  --%>

      <tr>
        <td colspan="4">
            <center><b><fmt:message key="permisosplantilla.pergrup" /></b></center>
            <br/>
        </td>
      </tr>
       
      
      <tr>
    
       <td>
         <center><i><fmt:message key="permisosplantilla.listgrup" /></i></center>
                
                
            <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
            <thead>
              <tr>
                <th><fmt:message key="grupEntitat.grupEntitat"></fmt:message> </th>
                <th><fmt:message key="genapp.actions" /> </th>
              </tr>
            </thead>
            <tbody>
              
              <c:forEach items="${plantillaFluxDeFirmesForm.plantillaFluxDeFirmes.permisGrupPlantillas}" var="_permis">
              <tr>
                  <td>${_permis.grupEntitat.nom}</td>
                  <td>
                     <a class="btn btn-danger" href="#myModal"
                     onclick="openModalSubmit(deleteGrupFromPlantilla('${_permis.grupEntitatID}'),'show','plantillaFluxDeFirmesForm');"
                     title='<fmt:message key="genapp.delete" />'>
                     <i class="icon-trash icon-white"></i>
                     </a>
                   </td>
              </tr>
              </c:forEach>
              
            </tbody>
          </table>
       
       </td>
       
       <td style="width:16px;">&nbsp;</td>
       
       <td style="width:16px;border-left:1px solid;">&nbsp;</td>

       <td valign="top">

           <c:if test="${not empty grups}">
           <center><i><fmt:message key="permisosplantilla.addgroup"></fmt:message> </i></center>
           <table>
            <tr>
              <td>
                &nbsp;<fmt:message key="grupEntitat.grupEntitat.plural"></fmt:message>:&nbsp;
                <select name='groupToAdd' id="groupToAdd">
                    <c:forEach items="${grups}" var="grup">
                        <option value="${grup.key}" >${grup.value}</option>
                    </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
             <td align="center">
             <button type="button" class="btn btn-primary" onclick="addGroupToPlantilla()" title="<fmt:message key="afegir"></fmt:message>" >
                <i class="icon-plus-sign icon-white"></i>
                <fmt:message key="afegir" />
             </button>
             &nbsp;
             </td>
            </tr>
          </table>
          </c:if>
        
       </td>

      </tr>
    
    </table>
    <hr />
    
    </div> <%-- Final Permisos per Grup --%>
    
    
    
    
    <%@include file="../webdb/plantillaFluxDeFirmesFormButtons.jsp" %>
      
     

 <script type="text/javascript">



  function addUserToPlantilla() {
     var nif = document.getElementById("userToAdd");
     var nifvalue = nif.value;
     if (nifvalue == '') {
         alert('<fmt:message key="genapp.validation.required"><fmt:param>NIF</fmt:param></fmt:message>');
         return;
     }

     var form;
     form = document.getElementById("plantillaFluxDeFirmesForm");
     
     form.action = "<c:url value="${plantillaFluxDeFirmesForm.contexte}/allowUserToPlantilla/"/>";
     form.submit();
  }


  function deleteUserFromPlantilla(userToDelete) {
	var theInput = document.getElementById("userToDeleteFromPlantilla");
	theInput.value=userToDelete;
	return '<c:url value="${contexte}/deleteUserFromPlantilla/"/>';
  }


  <c:if test="${not empty grups}">

  function addGroupToPlantilla() {

     var form;
     form = document.getElementById("plantillaFluxDeFirmesForm");
     
     form.action = "<c:url value="${plantillaFluxDeFirmesForm.contexte}/allowGroupToPlantilla/"/>";
     form.submit();
  }

  </c:if>

  function deleteGrupFromPlantilla(userToDelete) {
    var theInput = document.getElementById("groupToDeleteFromPlantilla");
    theInput.value=userToDelete;
    return '<c:url value="${contexte}/deleteGroupFromPlantilla/"/>';
  }

    

  </script>


</form:form>


<c:url var="theURL" value="${plantillaFluxDeFirmesForm.contexte}/allowUserToPlantilla"/>

<%@ include file="/WEB-INF/jsp/common/seleccioUsuariModal.jsp"%>


</c:if>
</sec:authorize>
