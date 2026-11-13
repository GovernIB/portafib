
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pluginFirmaWebPerUsuariAplicacioForm" method="${(empty method)?'post':method}" 
 enctype="multipart/form-data">
  
  <%@include file="pluginFirmaWebPerUsuariAplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pluginFirmaWebPerUsuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCorePre.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCore.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCorePost.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormButtons.jsp" %>

  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioForm.sections}">
     <c:set var="__basename" value="pluginFirmaWebPerUsuariAplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pluginFirmaWebPerUsuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginFirmaWebPerUsuariAplicacioFormModificable.jsp" %>
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


