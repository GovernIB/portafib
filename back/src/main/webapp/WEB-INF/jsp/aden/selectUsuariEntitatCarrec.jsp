<%@page import="org.fundaciobit.genapp.common.web.html.HtmlCSS"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form modelAttribute="seleccioCarrecForm" method="post" name="seleccioCarrecForm">
<%=HtmlCSS.TITOL_BEGIN %> <fmt:message key="${seleccioCarrecForm.titol}" /> <%=HtmlCSS.TITOL_END %>

<!-- Usuari-Entitat Càrrec-->
<table class="table table-sm table-bordered" style="width:auto;">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="formselectionby.label" /> &nbsp;(*)</label>
      </td>
      <td>
       <%--      =========  INICI SELECCIO USUARI FIELD =============  --%>
       
       <c:set var="seleccioUsuariForm" value="${seleccioCarrecForm}" />
        
        <%@ include file="/WEB-INF/jsp/common/seleccioUsuariField.jsp"%>
        
        <%--      =========  INICI SELECCIO USUARI FIELD  =============  --%>
      </td>
  </tr>
  <tr>
    <td>
       <label><fmt:message key="carrec"/></label>
    </td>
    <td>
       <form:errors path="carrec" cssClass="errorField alert alert-danger" />
       <form:input cssClass="input-xlarge" path="carrec" />
    </td>
  </tr>
  <tr>
    <td>
       <label><fmt:message key="carrec.id"/>:</label>
    </td>
    <td>
        <form:errors path="idCarrec" cssClass="errorField alert alert-danger" />
        <div class="input-prepend">
          <span class="add-on">${seleccioCarrecForm.entitatID}_</span>
          <form:input cssClass="input-xlarge" path="idCarrec"/>
        </div>
        <br>
    </td>
  </tr>
</tbody>
</table>  

<button class="btn btn-primary" type="button" onclick="preSubmit()" ><fmt:message key="acceptar"/></button>
<button class="btn btn-secondary" type="button" onclick="goTo('<c:url value="${seleccioCarrecForm.cancelUrl}"/>')"><fmt:message key="genapp.cancel"/></button>

<script type="text/javascript">

        function preSubmit() {
            var id = $('#id').val();
            // alert(" PRESUBMIT Valor de ID ]" + id + "[")
            
            if (id) {
              document.getElementById("seleccioCarrecForm").submit();
            } else {
              alert("<fmt:message key="formselectionby.error.emptyid"/>");
            }
        
        }


      function getRadioButtonSelectedValue(ctrl)
      {
          for(i=0;i<ctrl.length;i++)
              if(ctrl[i].checked) return ctrl[i].value;
      }

      function radioChanged() {
    	  disableAll();
          document.getElementById("id").disabled='';
          document.getElementById("carrec").disabled='';
          document.getElementById("idCarrec").disabled='';
      }

      function disableAll() {
    	  var elements = ['id','carrec', 'idCarrec'];

    	  for (i=0;i<elements.length;i++){
    		document.getElementById(elements[i]).disabled='disabled';
    	  }
      }

      radioChanged();

</script>



</form:form>
