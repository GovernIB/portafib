<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
          <c:if test="${__theFilterForm.editButtonVisible || __theFilterForm.deleteButtonVisible || not empty __theFilterForm.additionalButtonsForEachItem || not empty __theFilterForm.additionalButtonsByPK}">
          <td>
            <div class="btn-group" data-toggle="buttons-checkbox">
            <c:if test="${__theFilterForm.editButtonVisible}">
            <a class="btn" href="#" 
              onclick="goTo('<c:url value="${contexte}/${custodiaInfo.custodiaInfoID}/edit"/>')"
              title="<fmt:message key="genapp.edit"/>">
              <i class="icon-pencil"></i>
            </a>
            </c:if>
            <c:if test="${__theFilterForm.deleteButtonVisible}">
            <a class="btn btn-danger" href="#myModal"
               onclick="openModal('<c:url value="${contexte}/${custodiaInfo.custodiaInfoID}/delete"/>','show');"
               title="<fmt:message key="genapp.delete"/>">
               <i class="icon-trash icon-white"></i>
            </a>
            </c:if>
            <c:set var="bracket" value="{0}"/>
            <c:set var="pk" value="${custodiaInfo.custodiaInfoID}"/>
            <c:forEach var="button" items="${__theFilterForm.additionalButtonsForEachItem}">
            <c:set var="thelink" value="${fn:replace(button.link,bracket, pk)}" />
            <c:if test="${!fn:startsWith(thelink,'javascript:')}">
            <c:url var="thelink" value="${thelink}"/>
            <c:set var="thelink" value="goTo('${thelink}')"/>
            </c:if>
            <a class="btn ${button.type} " href="#" 
              onclick="${thelink}" 
              title="<fmt:message key="${button.codeText}"/>">
              <i class="${button.icon}"></i>
            </a>
            </c:forEach>


            <c:if test="${not empty __theFilterForm.additionalButtonsByPK}">
              <c:if test="${not empty __theFilterForm.additionalButtonsByPK[pk]}">
                <c:set var="button" value="${__theFilterForm.additionalButtonsByPK[pk]}"/>
            <c:set var="thelink" value="${fn:replace(button.link,bracket, pk)}" />
            <c:if test="${!fn:startsWith(thelink,'javascript:')}">
            <c:url var="thelink" value="${thelink}"/>
            <c:set var="thelink" value="goTo('${thelink}')"/>
            </c:if>
            <a class="btn ${button.type} " href="#" 
              onclick="${thelink}" 
              title="<fmt:message key="${button.codeText}"/>">
              <i class="${button.icon}"></i>
            </a>
               </c:if>

            </c:if>

            </div>
           </td>
           </c:if>
