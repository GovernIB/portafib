<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@page import="es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController"%>

</form>

<div>
<form action="<%=request.getContextPath() + GestioTipusDocumentAdminController.CONTEXTWEB + GestioTipusDocumentAdminController.CONTEXTWEB_MOURE_TIPUS_DOC%>"
        method="post" name="canviTipusIDForm" id="canviTipusIDForm">
        
    <input type="hidden" id="tipusidoriginal" name="tipusidoriginal" value="">    

    <!-- Modal -->
    <div class="modal fade" id="aplicacionsModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="tipusdocument.modallabel"></fmt:message> </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                        <div class="form-group">
                            <label for="usuariAplicacioID"><fmt:message key="tipusdocument.appoentitat"></fmt:message></label><br/>
                            <select class="form-control" id="usuariAplicacioID" name="usuariAplicacioID">
                                <c:forEach var="app" items="${aplicacions}">
                                    <option value="${app.key}">${app.value}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="tipusid"><fmt:message key="tipusdocument.newid"></fmt:message></label>
                            <input type="number" class="form-control" id="tipusid" name="tipusid" value="${nouTipusID}" >
                        </div>

                        <%--
                                            <table>
                        <tr>
                            <td><fmt:message key="tipusdocument.appoentitat"></fmt:message></td>
                            <td>
                                <select class="input-large" id="usuariAplicacioID" name="usuariAplicacioID">
                                    <c:forEach var="app" items="${aplicacions}">
                                        <option value="${app.key}">${app.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message key="tipusdocument.newid"></fmt:message></td>
                            <td><input type="number" class="form-control" id="tipusid" name=tipusid></td>
                        </tr>
                    </table>
                    --%>
                </div>
                <div class="modal-footer">
                    <div align="center">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button id="continuar" type="submit" class="btn btn-primary" title="<fmt:message key="continuar" />">
                            <%--  <i class="fas fa-plus-circle icon-white"></i> --%>
                            <fmt:message key="continuar" />
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>



<script>

   function changeType(type) {
       document.getElementById("tipusidoriginal").value = type;    
       $('#aplicacionsModal').modal({ show: true });
   }

</script>