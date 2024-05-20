<%@page import="org.fundaciobit.genapp.common.web.html.HtmlCSS"
%><%@page import="es.caib.portafib.commons.utils.Configuracio"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<form:form modelAttribute="seleccioUsuariForm" method="post" name="seleccioUsuariForm">

<%=HtmlCSS.TITOL_BEGIN %><fmt:message key="${seleccioUsuariForm.titol}" /><%=HtmlCSS.TITOL_END %>

<%=HtmlCSS.SUBTITOL_BEGIN %>
<c:set var="subtitleTranslated" value="${fn:startsWith(seleccioUsuariForm.subtitol,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(seleccioUsuariForm.subtitol, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${seleccioUsuariForm.subtitol}" />
</c:if>
<%=HtmlCSS.SUBTITOL_END %>

<table class="tdformlabel table-sm table table-bordered table-striped marTop10" style="margin-bottom: 0px;">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="formselectionby.label" /> &nbsp;(*)</label>
      </td>
      <td>
        
        <%--      =========  INICI SELECCIO USUARI FIELD =============  --%>
        
        <%@ include file="/WEB-INF/jsp/common/seleccioUsuariField.jsp"%>
        
        <%--      =========  INICI SELECCIO USUARI FIELD  =============  --%>
         
      </td>
  </tr>
</tbody>
</table>


<div class="navbar-form pagination-centered" style="margin-top: 15px;">
  <button class="btn btn-primary" type="button" onclick="preSubmit()" ><fmt:message key="genapp.continue"/></button>
  <button class="btn btn-secondary" type="button" onclick="goTo('<c:url value="${seleccioUsuariForm.cancelUrl}"/>')">
    <fmt:message key="genapp.cancel"/>
  </button>
</div>

</form:form>


<script type="text/javascript">

function preSubmit() {
    var id = $('#id').val();
    // alert(" PRESUBMIT Valor de ID ]" + id + "[")
    
    if (id) {
      document.getElementById("seleccioUsuariForm").submit();
    } else {
      alert("<fmt:message key="formselectionby.error.emptyid"/>");
    }

}

</script>


