
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="grupEntitatUsuariEntitatForm" method="${(empty method)?'post':method}" 
 enctype="multipart/form-data">
  
  <%@include file="grupEntitatUsuariEntitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${grupEntitatUsuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupEntitatUsuariEntitatFormCorePre.jsp" %>

  <%@include file="grupEntitatUsuariEntitatFormCore.jsp" %>

  <%@include file="grupEntitatUsuariEntitatFormCorePost.jsp" %>

  <%@include file="grupEntitatUsuariEntitatFormButtons.jsp" %>

  <c:if test="${not empty grupEntitatUsuariEntitatForm.sections}">
     <c:set var="__basename" value="grupEntitatUsuariEntitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${grupEntitatUsuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupEntitatUsuariEntitatFormModificable.jsp" %>
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


