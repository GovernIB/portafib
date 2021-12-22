<%@page import="org.fundaciobit.genapp.common.web.html.HtmlCSS"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
 
<form:form modelAttribute="autoFirmaForm" method="post"
  enctype="multipart/form-data">

<%=HtmlCSS.TITOL_BEGIN %><fmt:message key="autofirma" /><%=HtmlCSS.TITOL_END %>

  <div class="module_content">
    <div class="tab_container">
    
    <table class="tdformlabel table-sm table table-bordered table-striped marTop10  " > 
    <tbody>      

        <tr>
          <td><label><fmt:message key="peticioDeFirma.titol" /> &nbsp;(*)</label></td>
            <td>
            <form:errors path="titol" cssClass="errorField alert alert-danger" />
            <form:input cssClass="form-control" maxlength="255" path="titol" />
           </td>
         </tr>
        
        <tr>
          <td><label><fmt:message key="peticioDeFirma.descripcio" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" cssClass="form-control"  path="descripcio"  />
           </td>
         </tr>
         
        <tr>
          <td><label><fmt:message key="peticioDeFirma.motiu" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="motiu" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" cssClass="form-control"  path="motiu"  />
           </td>
         </tr>
         
         <tr>
          <td><label><fmt:message key="peticioDeFirma.posicioTaulaFirmesID" /> &nbsp;(*)</label></td>
            <td>
          <form:errors path="posicioTaulaFirmesID" cssClass="errorField alert alert-danger" />
          <form:select path="posicioTaulaFirmesID" cssClass="form-control" >
          <%-- Si el camp es nulable llavors una entrada buida --%>
            <c:forEach items="${autoFirmaForm.listOfPosicioTaulaFirmes}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
           </td>
         </tr>

        <tr>
          <td><label><fmt:message key="peticioDeFirma.fitxerAFirmarID" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="fitxerAFirmarID" cssClass="errorField alert alert-danger" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-group">
                    <div class="uneditable-input span4">
                      <i class="far fa-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input path="fitxerAFirmarID" type="file" />
                    </span>                    
                </div>
              </div>
           </td>
         </tr>
         <tr>
          <td>
            <label>
              <fmt:message key="autofirma.idiomadocument" /> &nbsp;(*)
              </label>
            </td>
            <td>
              <form:select path="idioma"  cssClass="form-control"> 
                <form:option value="es">Castellano</form:option>
                <form:option value="ca">Català</form:option>
              </form:select>
            </td>
         </tr>
         <tr>
            <td><label><fmt:message key="peticioDeFirma.segellatDeTemps" /> &nbsp;</label></td>
            <td> 
                <c:choose >
                    <c:when test="${autoFirmaForm.segellDeTempsReadOnly}">
                       <fmt:message key="genapp.checkbox.${autoFirmaForm.segellDeTemps}" />
                       <form:hidden path="segellDeTemps" />
                    </c:when>
                    <c:when test="${not autoFirmaForm.segellDeTempsReadOnly}">
                       <form:checkbox path="segellDeTemps"   />
                    </c:when>
                </c:choose>
            </td>
         </tr>


        <c:forEach var="i" begin="1" end="4" step="1" >
        <tr>
          <td><label><fmt:message key="adjunt" /> ${i}</label></td>
            <td>
              <form:errors path="adjunt${i}" cssClass="errorField alert alert-danger" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-group">
                    <div class="uneditable-input span4">
                      <i class="far fa-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input path="adjunt${i}" type="file" />
                    </span>  
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>                
                </div>
              </div>
           </td>
         </tr>
         </c:forEach>
     </tbody>
    </table>

    </div>

  </div>

  <br/>
  <div class="navbar-form float-right">
    <input id="submitbutton" type="submit" class="btn btn-primary" value="<fmt:message key="firmar"/>">
   </div>
 
   <form:hidden id="id" path="id" />
   
   <form:hidden id="baseUrlFull" path="baseUrlFull" />
  
</form:form>
<script>

  var baseUrl = document.getElementById("baseUrlFull");
  baseUrl.value = window.location.href;

</script>
