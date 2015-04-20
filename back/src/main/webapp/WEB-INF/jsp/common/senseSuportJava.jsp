<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<br>
<br>

<%--
<br>
<fmt:message key="sensesuportjava.intro"/><br>
<fmt:message key="sensesuportjava.msg1"/><br>
<fmt:message key="sensesuportjava.msg2"/><br/>
 --%>
   <h3 class="tabs_involved">
      <fmt:message key="sensesuportjava.titol"/>
  </h3>
<fmt:message key="sensesuportjava.intro"/>

<br/>
<br/>
(1) <fmt:message key="sensesuportjava.msg1"/>
<br/>
<br/>
(2) <fmt:message key="sensesuportjava.msg2"/>
<br/>
<br/>


<c:if test="${enable_npapi}">
(3) <fmt:message key="sensesuportjava.msg3"/><br/>
</c:if>
<br>

