<%@ page import="es.caib.portafib.utils.Configuracio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp" %>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />

<div class="navbar">
  <div class="navbar-inner">
    <div class="left">
      <a href="#" class="link" onClick="goBack()">
        <i class="icon icon-back"></i>
        <span> </span>
       </a>
    </div>
    <div class="center">
    	Firmant Petició
  	</div>
    <div class="right">
        <a href="#" class="link open-panel"><i class="icon f7-icons">bars</i></a>
    </div>
  </div>
</div>

<div class="pages navbar-through toolbar-through">
    <!-- Page, "data-page" contains page name -->
    <div data-page="firmaPeticio" class="page">
      <!-- Scrollable page content -->
      <div class="page-content">
		<div class="content-block">
			<!-- CONTENT -->
	  		<div class="row">
			    <div class="col-100">
				  <iframe src="${urlToSelectPluginPage}" frameborder='0' width="100%" height="400px"  id="myiframe" scrolling="auto">
				  	<p>NO IFRAME</p>
				  </iframe>
			    </div>
			</div>
		</div>
     	<!-- END CONTENT -->
    </div>
  </div>
</div>
