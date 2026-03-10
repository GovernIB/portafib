<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<div class="clear"></div>
<div class="spacer"></div>
<%-- 
Llistat de propietats d'un plugin de SignatureWeb.
Les columnes representen la classe org.fundaciobit.pluginsib.signature.api.PropertyInfo.

L'objecte "propietats" és una llista d'objectes PropertyInfo

Es mostraran en aquest JSP en format <table> amb les columnes iguals que els camps de la classe PropertyInfo
 --%>


<h2>Propietats Disponibles de '${nom}'</h2>
<br>
<c:choose>
  <c:when test="${empty propietats}">
    <div class="alert alert-info">
      No hi ha propietats disponibles.
    </div>
  </c:when>
  <c:otherwise>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th>Clau</th>
          <th style="width:30%; word-wrap:break-word; overflow-wrap:break-word;">Descripció</th>
          <th>Opcional</th>
          <th>Valor per defecte</th>
          <th>Patró</th>
          <th>Valors disponibles</th>
          <th>Exemples</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="prop" items="${propietats}">
          <tr>
            <td><code><c:out value="${prop.key}" /></code></td>
            <td ><c:out value="${prop.description}" /></td>
            <td>
              <c:choose>
                <c:when test="${prop.optional}">
                  <span class="label label-info">Sí</span>
                </c:when>
                <c:otherwise>
                  <span class="label label-danger">No</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <c:if test="${not empty prop.defaultValue}">
                <code><c:out value="${prop.defaultValue}" /></code>
              </c:if>
            </td>
            <td>
              <c:if test="${not empty prop.pattern}">
                <code><c:out value="${prop.pattern}" /></code>
              </c:if>
            </td>
            <td>
              <c:if test="${not empty prop.listOfAvailableValues}">
                <ul class="list-unstyled" style="margin-bottom:0;">
                  <c:forEach var="val" items="${prop.listOfAvailableValues}">
                    <li><code><c:out value="${val}" /></code></li>
                  </c:forEach>
                </ul>
              </c:if>
            </td>
            <td>
              <c:if test="${not empty prop.examples}">
                <ul class="list-unstyled" style="margin-bottom:0;">
                  <c:forEach var="ex" items="${prop.examples}">
                    <li><code><c:out value="${ex}" /></code></li>
                  </c:forEach>
                </ul>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
  </c:otherwise>
</c:choose>

<%-- Afegir boto per tornar a la pàgina dels llistat de plugins (la url esta en un "model" anomenat "tornar".
Implementar amb botons de bootstrap --%>
<div class="text-center">
  <a href="<c:url value="${tornar}"/>" class="btn btn-primary">
    <i class="fa fa-arrow-left"></i> <fmt:message key="tornar" />
  </a>

  

