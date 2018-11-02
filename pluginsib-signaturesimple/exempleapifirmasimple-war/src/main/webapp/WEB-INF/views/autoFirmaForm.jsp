<%@ include file="/WEB-INF/views/html_header.jsp"
%><%@page language="java" pageEncoding="UTF-8"
%><%@page language="java" contentType="text/html;charset=UTF-8" 
%>

<h3 class="tabs_involved">
    &nbsp;&nbsp;&nbsp;
    <fmt:message key="exemplefirmasimple" />
</h3>

<form:form modelAttribute="autoFirmaForm" method="post" enctype="multipart/form-data">

    <div style="margin: 20px 20px 20px 20px;" style="width:auto;">

        <div class="module_content" style="width: auto;">
            <div class="tab_container" style="width: auto;">

                <table
                    class="tdformlabel table-condensed table table-bordered table-striped marTop10"
                    style="width: auto;">
                    <tbody>
                        <tr>
                            <td><label><fmt:message
                                        key="peticioDeFirma.motiu" /> &nbsp;(*)</label></td>
                            <td><form:errors path="motiu"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    cssClass="input-xxlarge" path="motiu" /></td>
                        </tr>

                        <tr>
                            <td><label>NIF</label></td>
                            <td><form:errors path="motiu"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    cssClass="input-xxlarge" path="nif" /></td>
                        </tr>

                        <tr>
                            <td><label>Username</label></td>
                            <td><form:errors path="username"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    cssClass="input-xxlarge" path="username" /></td>
                        </tr>

                        <tr>
                            <td><label>Email</label></td>
                            <td><form:errors path="email"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    cssClass="input-xxlarge" path="email" /></td>
                        </tr>

                        <tr>
                            <td><label>Location</label></td>
                            <td><form:errors path="location"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    cssClass="input-xxlarge" path="location" /></td>
                        </tr>

                        <tr>
                            <td><label>Perfils de Firma en Servidor</label></td>
                            <td><form:errors path="profileServer"
                                    cssClass="errorField alert alert-error" />
                                <form:select path="profileServer">
                                  <c:forEach items="${profilesServer}" var="profile"> 
                                  <form:option value="${profile.name}">${profile.name} - ${profile.description}</form:option>
                                  </c:forEach>
                                </form:select>
                             </td>
                        </tr>

                        <tr>
                            <td><label>Idioma UI &nbsp;(*)</label></td>
                            <td><form:errors path="langUI"
                                    cssClass="errorField alert alert-error" /> <form:select
                                    path="langUI">
                                    <form:option value="ca" selected="true">Catal&agrave;</form:option>
                                    <form:option value="es">Castell&agrave;</form:option>
                                </form:select></td>
                        </tr>

                        <tr>
                            <td><label>Idioma Doc. &nbsp;(*)</label></td>
                            <td><form:errors path="langDoc"
                                    cssClass="errorField alert alert-error" /> <form:select
                                    path="langDoc">
                                    <form:option value="ca" selected="true">Catal&agrave;</form:option>
                                    <form:option value="es">Castell&agrave;</form:option>
                                </form:select></td>
                        </tr>
                        
                        <tr>
                            <td><label><fmt:message
                                        key="visualitzacio" /> &nbsp;(*)</label></td>
                            <td><form:errors path="visualitzacio"
                                    cssClass="errorField alert alert-error" />
                             
                             <form:radiobutton path="visualitzacio" value="fullview"  />FullView <br/>
                             <form:radiobutton path="visualitzacio" value="iframe" />IFrame    

                             </td>
                        </tr>

                        <tr>
                            <td><label><fmt:message
                                        key="peticioDeFirma.fitxerAFirmarID" /> &nbsp;(*)</label></td>
                            <td><form:errors path="fitxerAFirmarID"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    path="fitxerAFirmarID" type="file" /></td>
                        </tr>
                        
                        <tr>
                            <td><label><fmt:message
                                        key="peticioDeFirma.fitxerAFirmarID2" /></label></td>
                            <td><form:errors path="fitxerAFirmarID2"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    path="fitxerAFirmarID2" type="file" /></td>
                        </tr>
                        
                        <tr>
                            <td><label><fmt:message
                                        key="peticioDeFirma.fitxerAFirmarID3" /></label></td>
                            <td><form:errors path="fitxerAFirmarID3"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    path="fitxerAFirmarID3" type="file" /></td>
                        </tr>
                        
                        <tr>
                            <td><label><fmt:message
                                        key="peticioDeFirma.fitxerAFirmarID4" /></label></td>
                            <td><form:errors path="fitxerAFirmarID4"
                                    cssClass="errorField alert alert-error" /> <form:input
                                    path="fitxerAFirmarID4" type="file" /></td>
                        </tr>
                        

                    </tbody>
                </table>

                <div style="text-align:center; width:100%">

                    <input id="submitbutton2" name="firmarviaweb"
                        type="submit" class="btn btn-primary"
                        value="<fmt:message key="firmaweb"/>">
                    &nbsp;&nbsp;&nbsp; 
                    <input id="submitbutton" name="firmarenservidor" type="submit"
                        class="btn btn-warning"
                        value="<fmt:message key="firmaservidor"/>"> 

                </div>


            </div>
            <%-- Final DIV OPCIONS --%>

        </div>

    </div>

</form:form>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>
