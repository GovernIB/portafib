
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="usuariAplicacioConfiguracioForm" method="${(empty method)?'post':method}" 
 enctype="multipart/form-data">
  
  <%@include file="usuariAplicacioConfiguracioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${usuariAplicacioConfiguracioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariAplicacioConfiguracioFormCorePre.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormCore.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormCorePost.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormButtons.jsp" %>

  <c:if test="${not empty usuariAplicacioConfiguracioForm.sections}">
     <c:set var="__basename" value="usuariAplicacioConfiguracio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariAplicacioConfiguracioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariAplicacioConfiguracioFormModificable.jsp" %>
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


