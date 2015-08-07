

 <h5><fmt:message key="tipusDocument.tipusDocument" /> </h5>
 
 
 <table class="table table-condensed table-bordered" style="width:auto;"> 
 <tbody>
 
 
  
 <c:if test="${!colaboracioDelegacioForm.tipusReadOnly || (colaboracioDelegacioForm.tipusReadOnly && colaboracioDelegacioForm.tipus == 1) }" >
  <tr>
    <td>
       <label class="radio">
       <form:radiobutton path="tipus" id="tipus" value="1" onChange="radioChanged(this)" />
       <fmt:message key="tipusdocument.tots" />
       </label>
    </td>
  </tr>
  </c:if>
  <c:if test="${!colaboracioDelegacioForm.tipusReadOnly || (colaboracioDelegacioForm.tipusReadOnly && colaboracioDelegacioForm.tipus == 2) }" >
  <tr>
    <td>
      <label class="radio">
       <form:radiobutton path="tipus" id="tipus" value="2" onChange="radioChanged(this)" />
       <fmt:message key="tipusdocument.seleccio" />
       </label>

      <form:errors path="currentTipusDocument" cssClass="errorField alert alert-error" />

      <table border="0">
        <tr>
          <td>
              <b><small><fmt:message key="tipusdocument.seleccionats" /></small></b><br/>

              <form:select path="currentTipusDocument"  id="currentTipusDocument" cssClass="input-xlarge" multiple="true" size="4">
                <c:if test="${not empty colaboracioDelegacioForm.currentTipusDocument}">
                  <c:forEach items="${colaboracioDelegacioForm.allTipusDocumentInfo}" var="tipusDocInfo">
                    <c:set var="tipusDocID" value="${tipusDocInfo.key}" />
                    <c:if test="${gen:contains(colaboracioDelegacioForm.currentTipusDocument ,tipusDocID)}">
                      <form:option value="${tipusDocID}">${tipusDocInfo.value}</form:option>
                    </c:if>
                  </c:forEach>
                </c:if>
              </form:select>
              
              <%--
              <form:select path="currentTipusDocument"  id="currentTipusDocument" cssClass="input-xlarge" multiple="true" size="4">
                <c:forEach items="${colaboracioDelegacioForm.currentTipusDocument}" var="tipusDocID">
                <c:set var="tipusDocName" value="${colaboracioDelegacioForm.allTipusDocumentInfo[tipusDocID]}" />
                <form:option value="${tipusDocID}">${tipusDocName}</form:option>
                </c:forEach>
              </form:select>
              --%>
          </td>
          <c:if test="${!colaboracioDelegacioForm.tipusReadOnly}">
          <td>
            <button style="btn btn-primary" type="button" id="botoafegir" onclick="afegir()">&lt;&lt; </button><br/> 
            <button style="btn btn-danger" type="button" id="botoeliminar" onclick="eliminar()">&gt;&gt; </button>
          </td>
          <td>
              <b><small><fmt:message key="tipusdocument.disponibles" /></small></b><br/>
              
              <form:select path="availableTipusDocument" id="availableTipusDocument" cssClass="input-xlarge" multiple="true" size="4">
                <c:forEach items="${colaboracioDelegacioForm.allTipusDocumentInfo}" var="tipusDocInfo">
                  <c:set var="tipusDocID" value="${tipusDocInfo.key}" />
                  <c:if test="${gen:contains(colaboracioDelegacioForm.availableTipusDocument ,tipusDocID)}">
                    <form:option value="${tipusDocID}">${tipusDocInfo.value}</form:option>
                  </c:if>
                </c:forEach>
              </form:select>
              
               <%--
              <form:select path="availableTipusDocument" id="availableTipusDocument" cssClass="input-xlarge" multiple="true" size="4">
                <c:forEach items="${colaboracioDelegacioForm.availableTipusDocument}" var="tipusDocID">
                    <c:set var="tipusDocName" value="${colaboracioDelegacioForm.allTipusDocumentInfo[tipusDocID]}" />
                    <form:option value="${tipusDocID}">${tipusDocName}</form:option>
                </c:forEach>    
              </form:select>
              --%>
            </td>
          </c:if>
        </tr>
      </table>

    </td>
  </tr>
  </c:if>
</tbody>
</table>
  
  <script language="JavaScript" type="text/javascript">

    function preSubmit() {
    	var desti = document.getElementById("currentTipusDocument");
    	var tipus;
    	tipus  = document.getElementById("tipus");
        if (desti.options.length == 0) {
          // Seleccionem el tipus 1
          tipus.value = "1";
        } else {
          // Seleccionem el tipus 2
          tipus.value = "2";  

        }

        // Abans de fer commit seleccionen tots els elements de les dues llistes        
        for (var i = 0; i < desti.options.length; i++) { 
          desti.options[i].selected = true; 
        }

        var all = document.getElementById("availableTipusDocument");
        for (var i = 0; i < all.options.length; i++) { 
            all.options[i].selected = true; 
        }
    }

    function afegir() {
        moure(false);
    }

    function eliminar() {
        moure(true);
    }

    function moure(esBorrar) {
      var origen;
      var desti;

      if (esBorrar) {
    	  var origen = document.getElementById("currentTipusDocument");
          var desti =  document.getElementById("availableTipusDocument");
      } else {
    	  var origen = document.getElementById("availableTipusDocument");
          var desti =  document.getElementById("currentTipusDocument");
      }
      

      var index;
      index = origen.selectedIndex;
      if (index == -1) {
          // TODO traduir
          alert("Ha d'elegir un tipus de document.");
          return;
      }
   
      var selectValue = origen.options[index].value;
      var selectText = origen.options[index].text;

      desti.options[desti.options.length] = new Option(selectText, selectValue);

      origen.remove(index);
    }



    function getRadioButtonSelectedValue(ctrl) {
        for(i=0;i<ctrl.length;i++) {
            if(ctrl[i].checked) {
                return ctrl[i].value;
            }
        }
    }

    function radioChanged(radio) {
        
        var tipus = radio.value;

        switch(tipus)
        {   
            case '1':
               disableAll();
               var select;
               select = document.getElementById("currentTipusDocument");
            	var num =  select.length;
            	for (var i=0;i<num;i++)	{
                  select.selectedIndex = 0;
            	  eliminar();
            	}
            break;
            case '2':
                enableAll();                  
            break;            
            default:
              alert('Tipus no conegut ' + tipus)
        }
    }

    var elements = ['botoafegir', 'botoeliminar'];

    function enableAll() {
    	for (i=0;i<elements.length;i++){
            if (document.getElementById(elements[i])) {
              document.getElementById(elements[i]).disabled='';
            }
        }
    }

    function disableAll() {

        for (i=0;i<elements.length;i++){ 
          if (document.getElementById(elements[i])) {
            document.getElementById(elements[i]).disabled='disabled';
          }
        }
    }


    function desactivar(id) {
             
	    var r=confirm("<fmt:message key="avisdeshabilitarcolaboradordelegat"/>");
	    if (r==false) {
	      return;
	    }
	    	    
	    var reason = prompt("<fmt:message key="motiudesactivarcolaboraciodelegacio"/>","");
	    
	    if (reason!=null) {      
	      document.getElementById("motiu").value=reason;
	      document.motiuform.action = '<c:url value="${contexte}/desactivar" />/' + id;
	      document.motiuform.submit();
	    }
	  }

    <c:if test="${colaboracioDelegacioForm.tipus == 1}" >
      disableAll()
    </c:if>
      
 
    </script>
  
  
  <style type="text/css">
  
    .buttons_tmp { }
    .buttons_tmp .navbar-form  {display: inline; }
  </style>
  
  <div class="buttons_tmp" >
  <%@include file="../webdb/colaboracioDelegacioFormButtons.jsp" %>
  </div>