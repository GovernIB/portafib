
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="bitacolaForm" method="${(empty method)?'post':method}" 
 enctype="multipart/form-data">
  
  <%@include file="bitacolaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${bitacolaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="bitacolaFormCorePre.jsp" %>

  <%@include file="bitacolaFormCore.jsp" %>

  <%@include file="bitacolaFormCorePost.jsp" %>

  <%@include file="bitacolaFormButtons.jsp" %>

  <c:if test="${not empty bitacolaForm.sections}">
     <c:set var="__basename" value="bitacola" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${bitacolaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/bitacolaFormModificable.jsp" %>
  </c:if>

</form:form>
<script>
    <%-- Les pipelles no actives de traduccions buides no visualitzen l'avis de camp requerit. --%>
    <%-- Amb aquest codi es posen a primer pla --%>
    document.querySelectorAll('form').forEach(function(form) {
    <%-- 'invalid' no bombolleja: cal utilitzar la fase de captura --%>
    form.addEventListener('invalid', function(e) {
            var pane = e.target.closest('.tab-pane');
            if (pane && !pane.classList.contains('active')) {
                // Activa el tab corresponent al camp invàlid
                var link = document.querySelector('a[href="#' + pane.id + '"]');
                if (link) {
                    $(link).tab('show'); <%-- Bootstrap 4 (jQuery) --%>
                }
            }
        }, true);
    });
</script>


