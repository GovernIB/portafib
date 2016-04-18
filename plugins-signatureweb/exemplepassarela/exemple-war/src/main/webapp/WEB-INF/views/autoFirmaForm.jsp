
<%@ include file="/WEB-INF/views/html_header.jsp"%>

<h3 class="tabs_involved">
  &nbsp;&nbsp;&nbsp;<fmt:message key="autofirma" />
</h3>
  
<form:form modelAttribute="autoFirmaForm" method="post"  enctype="multipart/form-data">
 
  <div style="margin:20px 20px 20px 20px;" style="width:auto;">

  <div class="module_content" style="width:auto;">
    <div class="tab_container" style="width:auto;">
    
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="width:auto;" > 
    <tbody>      
<%--
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
              <form:input cssClass="input-xxlarge"  path="descripcio"  />
           </td>
         </tr>
--%>
        <tr>
          <td><label><fmt:message key="peticioDeFirma.motiu" /> &nbsp;(*)</label></td>
            <td>
              <form:errors path="motiu" cssClass="errorField alert alert-error" />
              <form:input cssClass="input-xxlarge"  path="motiu"  />
           </td>
         </tr>

         <tr>
          <td><label>NIF</label></td>
            <td>
              <form:errors path="motiu" cssClass="errorField alert alert-error" />
              <form:input cssClass="input-xxlarge"  path="nif"  />
           </td>
         </tr>

         <tr>
          <td><label>Username</label></td>
            <td>
              <form:errors path="username" cssClass="errorField alert alert-error" />
              <form:input  cssClass="input-xxlarge"  path="username"  />
           </td>
         </tr>

          <tr>
          <td><label>Email</label></td>
            <td>
              <form:errors path="email" cssClass="errorField alert alert-error" />
              <form:input  cssClass="input-xxlarge"  path="email"  />
           </td>
         </tr>

          <tr>
          <td><label>Location</label></td>
            <td>
              <form:errors path="location" cssClass="errorField alert alert-error" />
              <form:input  cssClass="input-xxlarge"  path="location"  />
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
              <form:input path="fitxerAFirmarID" type="file" />
           </td>
         </tr>
         <tr>
            <td><label><fmt:message key="peticioDeFirma.segellatDeTemps" /> &nbsp;</label></td>
            <td> 
                <form:checkbox path="segellDeTemps" />
            </td>
         </tr>

     </tbody>
    </table>
    
    
    
    <h4 class="tabs_involved">
      &nbsp;&nbsp;&nbsp;Estampaci&oacute; Codi Segur Verificaci&oacute;
    </h4>
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="width:auto;" > 
    <tbody>    
    <tr>
          <td>
            <label>
              Usar Estampaci&oacute;</label>
            </td>
            <td>
          <form:checkbox path="cvsActivat" /></td>
        </tr>
        <tr>
          <td>
            <label>
              P&agrave;gines &nbsp;(*)
              </label>
            </td>
            <td>
            <form:errors path="cvsPagines" cssClass="errorField alert alert-error" />
            <form:input  cssClass="input-xxlarge"  path="cvsPagines"  />
            <br>
            <small>Valors possibles s&ocute;n: <br/>
                 buit = no mostrar<br/> 
                 * = totes les p&agrave;gines<br/>
                 0 = primera p&agrave;gina (taula de firmes) <br/>
                 -1 = darrera p&agrave;gina (taula de firmes) <br/>
                 -1, 0, 1, 3, 4-5, 8- = format Imprimir (sense taula de firmes)
                 </small>
            </td>
        </tr>
        <tr>
          <td>
            <label>
              Missatge &nbsp;(*)
              </label>
            </td>
            <td>
              <form:errors path="cvsMissatge" cssClass="errorField alert alert-error" />
              <form:textarea rows="3"  cssClass="input-xxlarge"  path="cvsMissatge"  />  
           </td>
        </tr>
        <tr>
          <td>
            <label>
              Posici&oacute; missatge/codi de barres &nbsp;(*)
              </label>
            </td>
            <td>
           <select id="cvsPosicio" name="cvsPosicio" class="input-xxlarge" >
                <option value="2">Abaix</option>
                <option value="1">Adalt</option>
                <option value="4">Dreta</option>
                <option value="3" selected="selected" >Esquerra</option>
                <option value="0">No estampar</option>
           </select></td>
        </tr>
        <tr>
          <td>
            <label>
              Tipus de Codi de Barres &nbsp;(*)
              </label>
            </td>
            <td>
            <%-- TODO Ho ha d'anar a cercar dels plugins Mètode  getSupportedBarCodeTypes() !!!!! --%>
          <select id="cvsTipusCodiBarres" name="cvsTipusCodiBarres" class="input-xxlarge" >
             <option value="BarCode128">BarCode128</option>
             <option value="Pdf417" selected="selected">Pdf417</option>
             <option value="QrCode">QrCode</option>
           </select>
         </td>
        </tr>
        <tr>
          <td>
            <label>
              Text de Codi de Barres &nbsp;(*)
              </label>
            </td>
            <td>
            <form:errors path="cvsCodiBarresText" cssClass="errorField alert alert-error" />
            <form:input  cssClass="input-xxlarge"  path="cvsCodiBarresText"   />
            </td>
        </tr>
     </tbody>
    </table>
    

    <center>
    <input id="submitbutton" type="submit" class="btn btn-primary" onclick="firmar()" value="<fmt:message key="firmar"/>">
    </center>

    </div>

  </div>

 
   
   <form:hidden id="jnlp" path="jnlp" />
   <form:hidden id="id" path="id" />
   
   </div>
  
</form:form>


<script src="<c:url value="/js/deployJava.js"/>"></script>


<script type="text/javascript">

    var jnlp;
    jnlp = document.getElementById("jnlp");
    if (deployJava.isPluginInstalled()) {
      jnlp.value = 'false';
    } else {
      jnlp.value = 'true';
    }
    
</script>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>
