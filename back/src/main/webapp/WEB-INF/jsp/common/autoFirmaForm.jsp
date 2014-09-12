
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
<form:form modelAttribute="autoFirmaForm" method="${method}"
  enctype="multipart/form-data">
 
  
  <h3 class="tabs_involved">
      <fmt:message key="autofirma" />
  </h3>

  <div class="module_content">
    <div class="tab_container">
    
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

        <tr>
          <td><label><fmt:message key="peticioDeFirma.titol" /> &nbsp;(*)</label></td>
            <td>
            <form:errors path="titol" cssClass="errorField alert alert-error" />
            <form:input cssClass="input" maxlength="255" path="titol" />
           </td>
         </tr>
        
        <tr>
          <td><label><fmt:message key="peticioDeFirma.descripcio" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" cssClass="input-xxlarge"  path="descripcio"  />
           </td>
         </tr>
         
        <tr>
          <td><label><fmt:message key="peticioDeFirma.motiu" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="motiu" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" cssClass="input-xxlarge"  path="motiu"  />
           </td>
         </tr>
         
         <tr>
          <td><label><fmt:message key="peticioDeFirma.posicioTaulaFirmesID" /> &nbsp;(*)</label></td>
            <td>
          <form:errors path="posicioTaulaFirmesID" cssClass="errorField alert alert-error" />
          <form:select path="posicioTaulaFirmesID">
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
              <form:errors path="fitxerAFirmarID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                    <div class="uneditable-input span4">
                      <i class="icon-file fileupload-exists"></i>
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

        <c:forEach var="i" begin="1" end="4" step="1" >
        <tr>
          <td><label><fmt:message key="adjunt" /> ${i}</label></td>
            <td>
              <form:errors path="adjunt${i}" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                    <div class="uneditable-input span4">
                      <i class="icon-file fileupload-exists"></i>
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
  <div class="navbar-form pull-right">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="firmar"/>">
   </div>
  
</form:form>


