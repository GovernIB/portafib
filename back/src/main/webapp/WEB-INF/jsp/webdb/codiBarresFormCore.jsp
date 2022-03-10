<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CodiBarresFields" className="es.caib.portafib.model.fields.CodiBarresFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CodiBarresFields.CODIBARRESID)}">
        <tr id="codiBarres_codiBarresID_rowid">
          <td id="codiBarres_codiBarresID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CodiBarresFields.CODIBARRESID])?'codiBarres.codiBarresID':__theForm.labels[CodiBarresFields.CODIBARRESID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CodiBarresFields.CODIBARRESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CodiBarresFields.CODIBARRESID]}" ></i>
              </c:if>
            </td>
          <td id="codiBarres_codiBarresID_columnvalueid">
            <form:errors path="codiBarres.codiBarresID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CodiBarresFields.CODIBARRESID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CodiBarresFields.CODIBARRESID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="codiBarres.codiBarresID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CodiBarresFields.NOM)}">
        <tr id="codiBarres_nom_rowid">
          <td id="codiBarres_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CodiBarresFields.NOM])?'codiBarres.nom':__theForm.labels[CodiBarresFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CodiBarresFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CodiBarresFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="codiBarres_nom_columnvalueid">
            <form:errors path="codiBarres.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CodiBarresFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,CodiBarresFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="codiBarres.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CodiBarresFields.DESCRIPCIO)}">
        <tr id="codiBarres_descripcio_rowid">
          <td id="codiBarres_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CodiBarresFields.DESCRIPCIO])?'codiBarres.descripcio':__theForm.labels[CodiBarresFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[CodiBarresFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CodiBarresFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="codiBarres_descripcio_columnvalueid">
              <form:errors path="codiBarres.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CodiBarresFields.DESCRIPCIO)? 'true' : 'false'}" path="codiBarres.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('codiBarres.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('codiBarres.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('codiBarres.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
