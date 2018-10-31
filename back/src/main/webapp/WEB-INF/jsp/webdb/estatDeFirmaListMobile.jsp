<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatDeFirmaFields" className="es.caib.portafib.model.fields.EstatDeFirmaFields"/>

  <c:set var="contexte" value="${estatDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="estatDeFirma" />
  <c:set var="__theFilterForm" value="${estatDeFirmaFilterForm}" />
  <c:if test="${empty estatDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatDeFirma.estatDeFirma"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatDeFirma.estatDeFirma"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>

<div class="navbar">
  <div class="navbar-inner">
    <div class="left">
      <a href="${requestReferer}" class="link">
        <i class="icon icon-back"></i>
        <span> </span>
       </a>
    </div>
    <div class="center">
    	<c:if test="${not empty __theFilterForm.titleCode}">
		      <fmt:message key="${__theFilterForm.titleCode}">
		        <fmt:param value="${__theFilterForm.titleParam}" />
		      </fmt:message>
		  </c:if>
		  <c:if test="${empty __theFilterForm.titleCode}">
		    <fmt:message key="genapp.listtitle">
		      <fmt:param value="${entitynameplural}"/>
		    </fmt:message>
		  </c:if>
  	</div>
    <div class="right">
        <a href="#" class="link open-panel"><i class="icon f7-icons">bars</i></a>
    </div>
  </div>
</div>

<div class="pages navbar-through toolbar-through">
    <!-- Page, "data-page" contains page name -->
    <div data-page="estatDeFirmaList" class="page">
      <!-- Scrollable page content -->
      <div class="page-content">
      
 	<c:if test="${empty estatDeFirmaItems}">
     <%@include file="estatDeFirmaListEmpty.jsp" %>

  </c:if>
 
      <c:choose>
      	<c:when test="${empty estatDeFirmaItems}">
      		<%@include file="estatDeFirmaListEmpty.jsp" %>
      	</c:when>
      	<c:otherwise>
      	
      
      <form:form name="estatDeFirma" cssClass="form-search"  modelAttribute="estatDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">
        
	    <%-- HIDDEN PARAMS: ORDER BY --%> 
		<form:hidden id="orderBy" path="orderBy"/> 
		<form:hidden id="orderAsc" path="orderAsc"/>
		
		<form:hidden path="nou" value="false"/>
      
        <div class="data-table data-table-init">
            <table>
                <thead>
	                <tr>
	                    <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
							<c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
								<th class="label-cell">
									${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
								</th>
							</c:if>
						</c:forEach>
						
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.ESTATDEFIRMAID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.ESTATDEFIRMAID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.FIRMAID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.USUARIENTITATID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.USUARIENTITATID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAINICI)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DATAINICI)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DATAFI)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.COLABORACIODELEGACIOID)}</th>
						</c:if>
						<c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
							<th class="label-cell">${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DESCRIPCIO)}</th>
						</c:if>
						
						<c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
							<c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
								<th class="label-cell">
									${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
								</th>
							</c:if>
						</c:forEach>
	                </tr>
                </thead>
                <tbody class="mobile-list">
                	<c:forEach var="estatDeFirma" items="${estatDeFirmaItems}">
                	
                		<c:set var="bracket" value="{0}"/>
                		<c:set var="pk" value="${estatDeFirma.estatDeFirmaID}"/>
                		<c:set var="thehref" value="#"/>
                		<c:forEach var="button" items="${__theFilterForm.additionalButtonsByPK[pk]}">
                			<c:if test="${button.codeText == 'vistacompleta'}">
			                  	<c:url var="thehref" value="${fn:replace(button.link,bracket, pk)}"/>
			                </c:if>
			            </c:forEach>
                	
                		<c:set var="prioritat" value="${peticionsByEstat[estatDeFirma.estatDeFirmaID].prioritatID}" />
                		<c:choose>
                			<c:when test="${prioritat <= 2}">
								<c:set var="prioritatStyle" value ="bg-lightgreen" />
							</c:when>
							<c:when test="${prioritat >= 7}">
								<c:set var="prioritatStyle" value ="bg-lightred" />
							</c:when>
							<c:otherwise>
								<c:set var="prioritatStyle" value ="bg-lightamber" />
							</c:otherwise>
						</c:choose>
						<tr id="estatDeFirma_rowid_${estatDeFirma.estatDeFirmaID}" data-href="${thehref}" class="tapable">
						
							<!-- selecció múltiple -->
		                	<c:if test="${__theFilterForm.visibleMultipleSelection}">
						      <td style="display:none;">
						       <form:checkbox path="selectedItems" value="${estatDeFirma.estatDeFirmaID}"/>
						       &nbsp;
						      </td>
						    </c:if>
		                	<!-- ----------------- -->
						
							<!--  /** Additional Fields */  -->
							 <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" varStatus = "status">
							 <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
							   <c:choose>
								   <c:when test="${status.first}">
								   	<td class="first-child ${prioritatStyle}">
								   </c:when>
								   <c:otherwise>
								   	<td>
								   </c:otherwise>
							   </c:choose>
							   	  <c:if test="${status.first}">
							   	  	<div class="margins">
							   	  </c:if>
							      <c:if test="${not empty __entry.value.valueMap }">
							        <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[estatDeFirma.estatDeFirmaID]}" />
							      </c:if>
							      <c:if test="${not empty __entry.value.valueField }">
							        <c:set var="__tmp" value="${pageScope}" />
							        <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
							        <c:forEach var="__tros" items="${__trosos}">
							           <c:set var="__tmp" value="${__tmp[__tros]}" />
							        </c:forEach>
							        <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
							      </c:if>
							      <c:if test="${status.first}">
							   	  	</div>
							   	  </c:if>
							   </td>
							   </c:if>
							   </c:forEach>
							
							
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.ESTATDEFIRMAID)}">
							   <td>
							   ${estatDeFirma.estatDeFirmaID}
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
							   <td>
							   <c:set var="tmp">${estatDeFirma.firmaID}</c:set>
							   <c:if test="${not empty tmp}">
							   ${__theFilterForm.mapOfFirmaForFirmaID[tmp]}
							   </c:if>
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.USUARIENTITATID)}">
							   <td>
							   <c:set var="tmp">${estatDeFirma.usuariEntitatID}</c:set>
							   <c:if test="${not empty tmp}">
							   ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
							   </c:if>
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAINICI)}">
							   <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${estatDeFirma.dataInici}" /></td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
							   <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${estatDeFirma.dataFi}" /></td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
							   <td>
							   <c:set var="tmp">${estatDeFirma.tipusEstatDeFirmaInicialID}</c:set>
							   <c:if test="${not empty tmp}">
							   ${__theFilterForm.mapOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID[tmp]}
							   </c:if>
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}">
							   <td>
							   <c:set var="tmp">${estatDeFirma.tipusEstatDeFirmaFinalID}</c:set>
							   <c:if test="${not empty tmp}">
							   ${__theFilterForm.mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID[tmp]}
							   </c:if>
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
							   <td>
							   <c:set var="tmp">${estatDeFirma.colaboracioDelegacioID}</c:set>
							   <c:if test="${not empty tmp}">
							   ${__theFilterForm.mapOfColaboracioDelegacioForColaboracioDelegacioID[tmp]}
							   </c:if>
							   </td>
							 </c:if>
							 <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
							   <td>
							   ${estatDeFirma.descripcio}
							   </td>
							 </c:if>
							
							
							 <!--  /** Additional Fields */  -->
							 <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
								 <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
								   <td>
								      <c:if test="${not empty __entry.value.valueMap }">
								        <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[estatDeFirma.estatDeFirmaID]}" />
								      </c:if>
								      <c:if test="${not empty __entry.value.valueField }">
								        <c:set var="__tmp" value="${pageScope}" />
								        <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
								        <c:forEach var="__tros" items="${__trosos}">
								           <c:set var="__tmp" value="${__tmp[__tros]}" />
								        </c:forEach>
								        <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
								      </c:if>
								   </td>
								 </c:if>
							 </c:forEach>
						</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
        </form:form>
        </c:otherwise>
      </c:choose>
    </div>
    
    <c:if test="${estatDeFirmaFilterForm.entityNameCode != 'solicituddefirma.llistat.pendent'}">
    	<c:set var="displayClass" value="none" />
	</c:if>
    
    <div class="toolbar second-toolbar" style="display: ${displayClass}">
	    <div class="toolbar-inner">
	        <button class="button button-round color-orange sel-mul" style="width: 135px;">Selecció Múltiple</button>
	        <button class="button button-fill color-blue ctrl-mul marcaTotes" style="width: 60px;" onClick="selectRows()">Totes</button>
	        <button class="button button-fill color-grey ctrl-mul marcaCap" style="width: 60px;" onClick="deselectRows()" style="display: none;">Cap</button>
	        <button class="button button-fill color-green ctrl-mul" onClick="firmarseleccionats()">Firmar</button>
	        <button class="button button-fill color-red ctrl-mul" onClick="rebutjarseleccionats()">Rebutjar</button>
	    </div>
	</div>
	
	<script type="text/javascript">
		localStorage['firmarSeleccionatsUrl'] = '<c:url value="${contexte}/firmarseleccionats"/>';
		localStorage['rebutjarSeleccionatsUrl'] = '<c:url value="${contexte}/rebutjarseleccionats"/>';
	</script>
	
  </div>
</div>