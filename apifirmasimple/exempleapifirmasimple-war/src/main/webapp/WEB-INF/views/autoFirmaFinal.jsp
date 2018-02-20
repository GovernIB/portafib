<%@ include file="/WEB-INF/views/include.jsp"%>

<%@ include file="/WEB-INF/views/html_header.jsp"%>

<div style="margin:40px;">
<br>
<br>
<br>

    <h3 class="tabs_involved"><fmt:message key="autofirma.final.msg1" /></h3>
    <fmt:message key="autofirma.final.msg2" />
    <table class="table table-bordered" >
        <tr>
            <td>ID</td>
            <td>Dades</td>
            <td>Original</td>
            <td>Resultat</td>
        </tr>

        <c:forEach var="entry" items="${peticions}">
        
            <c:set var="result" value="${entry.value.resultat}" ></c:set>

            <tr>
                <td><c:out value="${entry.key}" /></td>
                <td>
                  SignID: ${result.signID} <br/>
                  Status Number: ${result.status} <br/>
                  Status String:

                  <c:choose>
                     <c:when test = "${result.status == StatusFirma.STATUS_INITIALIZING}">
                        INICIALITZANT
                     </c:when>
                     <c:when test = "${result.status == StatusFirma.STATUS_IN_PROGRESS}">
                        EN PROGRES
                     </c:when>
                     <c:when test = "${result.status == StatusFirma.STATUS_FINAL_OK}">
                        FINAL_OK
                     </c:when>
                     <c:when test = "${result.status == StatusFirma.STATUS_FINAL_ERROR}">
                        ERROR
                     </c:when>
                     <c:when test = "${result.status == StatusFirma.STATUS_CANCELLED}">
                        CANCEL·LAT
                     </c:when>
                     <c:otherwise>
                        Estat Desconegut
                     </c:otherwise>
                  </c:choose>
                  <br/>
                  Nom Fitxer: ${result.signedFile.nom} <br/>
                  Mime Fitxer: ${result.signedFile.mime} <br/>
                  Len Fitxer: ${fn:length(result.signedFile.data)} <br/>
                </td>
                
                <td><a
                    href="<c:url value="/common/autofirma/download/original/${entry.key}" />"
                    target="_blank" class="btn btn-primary" style="color: white;"> <fmt:message
                            key="descarregardocumentoriginal" />
                </a> &nbsp;</td>


                <td>
                <c:if test="${result.status == StatusFirma.STATUS_FINAL_OK}">
                 <a
                    href="<c:url value="/common/autofirma/download/signed/${entry.key}" />"
                    target="_blank" class="btn btn-primary" style="color: white;"> <fmt:message
                            key="descarregardocumentfirmat" />
                </a>
                 </c:if>
                <c:if test="${result.status != StatusFirma.STATUS_FINAL_OK}">
                   
                   <b>${result.errorMessage}</b><br/>
                   <i>
                   ${result.errorStackTrace}
                   </i>

                </c:if>
                
                 &nbsp;
                </td>
            </tr>


            <br>
        </c:forEach>

    </table>
    
    <br/>
   
    <a href="<c:url value="/" />" class="btn"><fmt:message key="tornar"/></a>
   
</div>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>