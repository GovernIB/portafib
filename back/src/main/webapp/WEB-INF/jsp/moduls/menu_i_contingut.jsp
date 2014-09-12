<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="menu" />
<tiles:importAttribute name="contingut" />

<!--  INICI MENU -->
<div class="mainMenu span3">
  <div class="thumbnail">
    <tiles:insertAttribute name="menu">
    </tiles:insertAttribute>
  </div>
</div>

<!--  CONTINGUT  -->
<div class="span9">

  <!--  Missatges  -->
  <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

  <!-- Contingut de la pagina -->
  <tiles:insertAttribute name="contingut">
  </tiles:insertAttribute>

  <!-- FINAL DIV CONTINGUT -->
</div>

<div class="clearfix"></div>