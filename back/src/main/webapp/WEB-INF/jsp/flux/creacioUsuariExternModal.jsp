<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  
  <%--  --------------------------------------------------- --%>
  <%--  ----------------- USUARIS EXTERNS ----------------- --%>
  <%--  --------------------------------------------------- --%>
  
<!-- Modal Per demanar NIF de  USUARI EXTERN tabindex="-1" role="dialog" aria-labelledby="consultaNifUsuariExternLabel" aria-hidden="true"-->

<div class="modal hide fade" id="consultaNifUsuariExtern" tabindex="-1" role="dialog" >

 <div class="modal-dialog" role="document">
 <div class="modal-content">
  <form:form action="#" method="post" id="consultaNifUsuariExternForm">
    <div class="modal-header">      
      <h4>
        <fmt:message key="firmausuariextern.consultanif.titol"/>
      </h4>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="modal-body">
          <div class="form-group">
            <label for="consultanif" style="display: inline;"><fmt:message key="usuariPersona.nif"/>: </label>
            <input id="consultanif" name="consultanif" type="text" class="input" >
          </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" class="close" data-dismiss="modal"><fmt:message key="tancar"/></button>
        <button type="button" class="btn btn-primary" onclick="consultaNifUsuariExternSubmit()"><fmt:message key="continuar"/></button> 
    </div>    
  </form:form>
  </div>
  </div>
</div>
  
  <script type="text/javascript">

     function consultaNifUsuariExternSubmit() {
       //document.getElementById('consultaNifUsuariExternForm').submit();
       var nif = document.getElementById('consultanif').value;
       
       nif = nif.toUpperCase();
       document.getElementById('consultanif').value = nif;
       
       var URL="<c:url value="${contexte}/consultaNifUsuariExtern"/>?nif="+ nif;
       $.getJSON(URL, function(info) {
         
           var msg;
           if (info.id) {
              msg = "id:" + info.id + "\n"
               + "nif:" + info.nif + "\n"
               + "email:" +info.email+ "\n"
               + "nom:" + info.nom + "\n"
               + "llinatges:" + info.llinatges + "\n";
              
              setValue("crearfirma_usuarientitatid", info.id);
              setValue("crearfirma_nom", info.nom);
              setValue("crearfirma_llinatges", info.llinatges);
              setValue("crearfirma_idiomaID", info.idioma);
              setValue("crearfirma_email", info.email);
              
              document.getElementById("crearfirma_nif").readOnly = true;
              msg = "XYZ ZZZ  HI HA USUARI ENTITAT";
              
           } else {
             
             
             if (info.email) {
               
               msg = "XYZ ZZZ  HI HA USUARI PERSONA";
               
               setValue("crearfirma_nom", info.nom);
               setValue("crearfirma_llinatges", info.llinatges);
               setValue("crearfirma_email", info.email);
               setValue("crearfirma_idiomaID", info.idioma);
               
               document.getElementById("crearfirma_nif").readOnly = true;
               
               
             } else {
               msg = "XYZ ZZZ  NO EXISTEIX AQUEST NIF";
               setValue("crearfirma_idiomaID", '${lang}');
             }
             
           }
           // XYZ ZZZ
           console.log(msg);
           
           setValue("crearfirma_nif", nif );
           
           <c:if test="${not fluxDeFirmesForm.nou}">
           
           var blocID = document.getElementById("param1").value;
           console.log("XYZ ZZZ blocID = " + blocID);
           
           setValue("crearfirma_firmaid", '');
           // Si estam en un bloc que ja existeix
           setValue("crearfirma_blocid", blocID);
           // Si cream un nou bloc 
           
           console.log("crearfirma_blocOrdre => ]" + document.getElementById("param2").value + "[");
           setValue("crearfirma_blocOrdre", document.getElementById("param2").value);
           
           </c:if>

           $('#crearFirmaUsuariExtern').modal('show');

           setTimeout(closeConsultaNifUsuariExternDialog, 750);

        });
     }
     
     
     function setValue(id, value) {
       
       document.getElementById(id).value = value;
       
     }
     
     function closeConsultaNifUsuariExternDialog() {
       $('#consultaNifUsuariExtern').modal('hide');
     }

  </script>


<!-- Modal Per crear firma d'un USUARI EXTERN tabindex="-1" role="dialog" aria-labelledby="crearFirmaUsuariExternLabel" aria-hidden="true"-->

<div class="modal fade hide" id="crearFirmaUsuariExtern" tabindex="-1" role="dialog" >

 <div class="modal-dialog" role="document">
 <div class="modal-content">

   <form action="<c:url value="${contexte}/afegirFirmaUsuariExtern"/>" method="POST" id="crearFirmaUsuariExternForm" >
    <div class="modal-header">
      <h4>
        <fmt:message key="firmausuariextern.creafirma.titol"/>
      </h4>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="modal-body">
    
          <input id="crearfirma_usuarientitatid" name="crearfirma_usuarientitatid" type="hidden" >
          
    <c:if test="${fluxDeFirmesForm.nou}">
          <input id="nomes_crear_usuari_extern" name="nomes_crear_usuari_extern" value="true" type="hidden" >
    </c:if>

    <c:if test="${not fluxDeFirmesForm.nou}">
          <input id="crearfirma_blocid" name="crearfirma_blocid" type="hidden" >
          <input id="crearfirma_firmaid" name="crearfirma_firmaid" type="hidden" >
          <input id="crearfirma_blocOrdre" name="crearfirma_blocOrdre" type="hidden" >
    </c:if>
          <div class="form-group">
            <label for="crearfirma_nif" style="display: inline;"><fmt:message key="usuariPersona.nif"/>: </label>
            <input id="crearfirma_nif" name="crearfirma_nif" type="text" class="input" >
          </div>
          
          <div class="form-group">
            <label for="crearfirma_nom" style="display: inline;"><fmt:message key="usuariPersona.nom"/>: </label>
            <input id="crearfirma_nom" name="crearfirma_nom" type="text" class="input" >
          </div>
          
          <div class="form-group">
            <label for="crearfirma_llinatges" style="display: inline;"><fmt:message key="usuariPersona.llinatges"/>: </label>
            <input id="crearfirma_llinatges" name="crearfirma_llinatges" type="text" class="input" >
          </div>
          
          <div class="form-group">
            <label for="crearfirma_email" style="display: inline;"><fmt:message key="usuariPersona.email"/>: </label>
            <input id="crearfirma_email" name="crearfirma_email" type="text" class="input" >
          </div>
          <!--  XYZ ZZZ  COMBO BOX -->
          <div class="form-group">
            <label for="crearfirma_idiomaID" style="display: inline;"><fmt:message key="usuariPersona.idiomaID"/>: </label>
            <select id="crearfirma_idiomaID" name="crearfirma_idiomaID"  class="input" >
               <%-- XYZ ZZZ  Fer-ho dinamic --%>
               <option value="ca">Catal&agrave;</option>
               <option value="es">Castellano</option>
               <%-- <option value="volvo">Volvo</option> --%>
            </select>
          </div>       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="tancar"/></button>
        <button type="button" class="btn btn-primary" onclick="crearFirmaUsuariExternSubmit()"><fmt:message key="continuar"/></button> 
      </div>
  </form>
  
  </div>
  </div>
</div>


  <script type="text/javascript">

  function crearFirmaUsuariExternSubmit() {

      // XYZ ZZZ Validar camps via JAVASCRIPT
      var nif = $("#crearfirma_nif").val();
      var nom = $("#crearfirma_nom").val();
      var llinatges = $("#crearfirma_llinatges").val();
      var correu = $("#crearfirma_email").val();

      var campsOk = true;

      if (nif.length == 0) {
          campsOk &&= false;
      }
      if (nom.length == 0) {
          campsOk &&= false;
      }
      if (llinatges.length == 0) {
          campsOk &&= false;
      }
      if (correu.length == 0) {
          campsOk &&= false;
      }

      //      if ((nif.length == 0)||(nom.length == 0) ||(llinatges.length == 0) ||(correu.length == 0)) {
      //      if (nif.length * nom.length * llinatges.length * correu.length == 0) {
      if (!campsOk) {
          alert("<fmt:message key='firmausuariextern.formulari.incomplet'/>");
      } else {
          document.getElementById('crearFirmaUsuariExternForm').submit();
      }
  }
  </script>

  