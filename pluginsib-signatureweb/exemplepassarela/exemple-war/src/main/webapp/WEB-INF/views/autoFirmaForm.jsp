<%@page import="org.fundaciobit.plugins.signature.api.FileInfoSignature"
%><%@page language="java" pageEncoding="UTF-8"
%><%@page language="java" contentType="text/html;charset=UTF-8" 
%><%@ include file="/WEB-INF/views/html_header.jsp"%>

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
          <td>
            <label> Filtre Certificats &nbsp;</label>
            </td>
            <td>
              <form:errors path="filtreCertificats" cssClass="errorField alert alert-error" />
              <form:textarea rows="4"  cssClass="input-xxlarge"  path="filtreCertificats"  />  
           </td>
         </tr>
         
         <tr>
          <td><label>Idioma UI &nbsp;(*)</label></td>
            <td>
          <form:errors path="langUI" cssClass="errorField alert alert-error" />
          <form:select path="langUI">
            <form:option value="ca" selected="true" >Catal&agrave;</form:option>
            <form:option value="es" >Castell&agrave;</form:option>
          </form:select>
           </td>
         </tr>
         
         <tr>
          <td><label>Idioma Doc. &nbsp;(*)</label></td>
            <td>
          <form:errors path="langDoc" cssClass="errorField alert alert-error" />
          <form:select path="langDoc">
            <form:option value="ca" selected="true" >Catal&agrave;</form:option>
            <form:option value="es" >Castell&agrave;</form:option>
          </form:select>
           </td>
         </tr>
         
         
         <tr>
          <td><label>Tipus Firma &nbsp;(*)</label></td>
            <td>
          <form:errors path="signType" cssClass="errorField alert alert-error" />
          <form:select path="signType" id="signType" onchange="canviaTipus(this)">
            <option value="<%=FileInfoSignature.SIGN_TYPE_PADES %>" selected="true" >PADES</option>
            <option value="<%=FileInfoSignature.SIGN_TYPE_XADES %>" >XADES</option>
            <option value="<%=FileInfoSignature.SIGN_TYPE_CADES %>" >CADES</option>
            <option value="<%=FileInfoSignature.SIGN_TYPE_SMIME %>" >SMIME</option>
          </form:select>
           </td>
         </tr>
         
         <!--  NOMES PER XADES / CADES -->
         <tr style="display:none;" id="signModeTR">
          <td><label>Mode Firma &nbsp;(*)</label></td>
            <td>
          <form:errors path="signMode" cssClass="errorField alert alert-error" />
          <form:select path="signMode" cssClass="input-xxlarge"  >
            <!-- 
              implicit La firma resultante incluirá internamente una copia de los datos
              firmados. El uso de este valor podría generar firmas de gran tamaño.
            -->
            <option value="<%=FileInfoSignature.SIGN_MODE_IMPLICIT %>" selected="true" >Attached (firma inclou dades originals)</option>
            <!--        
              explicit La firma resultante no incluirá los datos firmados. Si no se
              indica el parámetro mode se configura automáticamente este
              comportamiento.
            -->
            <option value="<%=FileInfoSignature.SIGN_MODE_EXPLICIT %>" >Detached (firma NO inclou dades originals)</option>
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

        

     </tbody>
    </table>
    
    <center>
      <input id="submitbutton" name="firmarenservidor" type="submit" class="btn btn-warning" <%-- onclick="firmar()" --%> value="<fmt:message key="firmaservidor"/>">
      &nbsp;&nbsp;&nbsp;
      <input id="submitbutton2" name="firmarviaweb" type="submit" class="btn btn-primary" <%-- onclick="firmar()" --%> value="<fmt:message key="firmaweb"/>">
      
      <input id="linkbutton" name="caracteristiquesplugin" type="button" class="btn btn-info" onclick="window.location='<c:url value="/common/plugins/web"/>';" value="<fmt:message key="plugins.caracteristiques.titol"/>">
    </center>
    

    <h4 class="tabs_involved">
      &nbsp;&nbsp;OPCIONS GENERALS
    </h4>
    
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="width:auto;" > 
    <tbody>

         <tr>
            <td><label><fmt:message key="peticioDeFirma.segellatDeTemps" /> &nbsp;</label></td>
            <td> 
                <form:checkbox path="segellDeTemps" />
            </td>
         </tr>

    </tbody>
    
    </table>
    
    
    <div id="opcionspades">
    
    <h4 class="tabs_involved">
      &nbsp;&nbsp;OPCIONS PADES
    </h4>
    
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="width:auto;" > 
    <tbody>
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
          <td>
            <label>
              Usar Estampaci&oacute; CSV</label>
            </td>
            <td>
               <form:checkbox path="cvsActivat" />
          </td>
          </tr>
         
     </tbody>
    
    </table>
    
    
    <h5 class="tabs_involved">
      &nbsp;&nbsp; Opcions d'Estampaci&oacute; CSV
    </h5>
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="width:auto;" > 
    <tbody>    
    
        <tr>
          <td>
            <label>
              P&agrave;gines &nbsp;(*)
              </label>
            </td>
            <td>
                <table>
                <tr>
                    <td>
                        <form:errors path="cvsPagines" cssClass="errorField alert alert-error" />
                        <form:input  cssClass="input-large"  path="cvsPagines"  />
                    </td>
                    <td>                    
                        <small>Valors possibles s&oacute;n: <br/>
                             buit = no mostrar<br/> 
                             * = totes les p&agrave;gines<br/>
                             0 = primera p&agrave;gina (taula de firmes) <br/>
                             -1 = darrera p&agrave;gina (taula de firmes) <br/>
                             -1, 0, 1, 3, 4-5, 8- = format Imprimir (sense taula de firmes)
                         </small>
                    </td>
                </tr>
                </table>
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
      <input id="submitbutton" name="firmarenservidor" type="submit" class="btn btn-warning" <%-- onclick="firmar()" --%> value="<fmt:message key="firmaservidor"/>">
      &nbsp;&nbsp;&nbsp;
      <input id="submitbutton" name="firmarviaweb" type="submit" class="btn btn-primary" <%-- onclick="firmar()" --%> value="<fmt:message key="firmaweb"/>">
    </center>
    
    </div> <%-- Final DIV OPCIONS --%>

    </div>

  </div>

   <form:hidden id="id" path="id" />
   
   </div>
  
</form:form>

<script type="text/javascript">

   function canviaTipus(selectItem) {
       var val = $( "#signType" ).val();
       if (val == '<%=FileInfoSignature.SIGN_TYPE_PADES %>') {
           $( "#signModeTR").hide();
           $( "#opcionspades").show();
       } else {
           if (val == '<%=FileInfoSignature.SIGN_TYPE_SMIME %>') {
               $( "#signModeTR").hide();
               $( "#opcionspades").hide();
           } else {
               $( "#signModeTR").show();
               $( "#opcionspades").hide();
           }
       }
   }

</script>


<%@ include file="/WEB-INF/views/html_footer.jsp"%>
