<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.portafib.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ENTITATID)}">
        <tr id="entitat_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ENTITATID])?'entitat.entitatID':__theForm.labels[EntitatFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.entitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ENTITATID)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.ENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.entitatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOM)}">
        <tr id="entitat_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOM])?'entitat.nom':__theForm.labels[EntitatFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
        <tr id="entitat_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.DESCRIPCIO])?'entitat.descripcio':__theForm.labels[EntitatFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="entitat.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ACTIVA)}">
        <tr id="entitat_activa_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ACTIVA])?'entitat.activa':__theForm.labels[EntitatFields.ACTIVA]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ACTIVA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
              <form:errors path="entitat.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)? 'false' : 'true'}" path="entitat.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.WEB)}">
        <tr id="entitat_web_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.WEB])?'entitat.web':__theForm.labels[EntitatFields.WEB]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.WEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.WEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.web" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.WEB)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.WEB)? ' uneditable-input' : ''}"  style="" maxlength="250" path="entitat.web"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.FAVICONID)}">
        <tr id="entitat_faviconID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.FAVICONID])?'entitat.faviconID':__theForm.labels[EntitatFields.FAVICONID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.FAVICONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.FAVICONID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.faviconID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(faviconID.faviconID)}"/>">${faviconID.faviconID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)? ' uneditable-input' : ''}"   path="faviconID" type="file" />
                  <label class="custom-file-label" for="faviconID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.favicon}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.entitat.favicon)}"/>">${__theForm.entitat.favicon.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="faviconID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#faviconID').on('change', function(){
						var ruta = $('#faviconID').val(); 
						var rutaArray = ruta.split('\\');
						$('#faviconID-custom-file-label').css('display','block');
						$('#faviconID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOWEBID)}">
        <tr id="entitat_logoWebID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOWEBID])?'entitat.logoWebID':__theForm.labels[EntitatFields.LOGOWEBID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOWEBID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoWebID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoWebID.logoWebID)}"/>">${logoWebID.logoWebID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)? ' uneditable-input' : ''}"   path="logoWebID" type="file" />
                  <label class="custom-file-label" for="logoWebID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoWeb}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.entitat.logoWeb)}"/>">${__theForm.entitat.logoWeb.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoWebID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoWebID').on('change', function(){
						var ruta = $('#logoWebID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoWebID-custom-file-label').css('display','block');
						$('#logoWebID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
        <tr id="entitat_logoWebPeuID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOWEBPEUID])?'entitat.logoWebPeuID':__theForm.labels[EntitatFields.LOGOWEBPEUID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOWEBPEUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOWEBPEUID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoWebPeuID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoWebPeuID.logoWebPeuID)}"/>">${logoWebPeuID.logoWebPeuID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)? ' uneditable-input' : ''}"   path="logoWebPeuID" type="file" />
                  <label class="custom-file-label" for="logoWebPeuID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoWebPeu}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.entitat.logoWebPeu)}"/>">${__theForm.entitat.logoWebPeu.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoWebPeuID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoWebPeuID').on('change', function(){
						var ruta = $('#logoWebPeuID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoWebPeuID-custom-file-label').css('display','block');
						$('#logoWebPeuID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
        <tr id="entitat_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOSEGELLID])?'entitat.logoSegellID':__theForm.labels[EntitatFields.LOGOSEGELLID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOSEGELLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoSegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoSegellID.logoSegellID)}"/>">${logoSegellID.logoSegellID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)? ' uneditable-input' : ''}"   path="logoSegellID" type="file" />
                  <label class="custom-file-label" for="logoSegellID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoSegell}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.entitat.logoSegell)}"/>">${__theForm.entitat.logoSegell.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoSegellID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoSegellID').on('change', function(){
						var ruta = $('#logoSegellID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoSegellID-custom-file-label').css('display','block');
						$('#logoSegellID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ADREZAHTML)}">
        <tr id="entitat_adrezaHtml_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ADREZAHTML])?'entitat.adrezaHtml':__theForm.labels[EntitatFields.ADREZAHTML]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.ADREZAHTML]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ADREZAHTML]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.adrezaHtml" cssClass="errorField alert alert-danger" />
              <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,EntitatFields.ADREZAHTML)? 'mceEditorReadOnly':'mceEditor'}"  path="entitat.adrezaHtml"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.FILTRECERTIFICATS)}">
        <tr id="entitat_filtreCertificats_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.FILTRECERTIFICATS])?'entitat.filtreCertificats':__theForm.labels[EntitatFields.FILTRECERTIFICATS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.FILTRECERTIFICATS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.FILTRECERTIFICATS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.filtreCertificats" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.FILTRECERTIFICATS)? 'true' : 'false'}" path="entitat.filtreCertificats"  />
      <div id="dropdownMenuButton_filtreCertificats" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_filtreCertificats" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.filtreCertificats'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.filtreCertificats'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.filtreCertificats'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_filtreCertificats').on('click', function(){
					var valor = ($('#dropdownMenuContainer_filtreCertificats').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_filtreCertificats').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PDFAUTORITZACIODELEGACIOID)}">
        <tr id="entitat_pdfAutoritzacioDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PDFAUTORITZACIODELEGACIOID])?'entitat.pdfAutoritzacioDelegacioID':__theForm.labels[EntitatFields.PDFAUTORITZACIODELEGACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.PDFAUTORITZACIODELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PDFAUTORITZACIODELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.pdfAutoritzacioDelegacioID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PDFAUTORITZACIODELEGACIOID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(pdfAutoritzacioDelegacioID.pdfAutoritzacioDelegacioID)}"/>">${pdfAutoritzacioDelegacioID.pdfAutoritzacioDelegacioID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PDFAUTORITZACIODELEGACIOID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.PDFAUTORITZACIODELEGACIOID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.PDFAUTORITZACIODELEGACIOID)? ' uneditable-input' : ''}"   path="pdfAutoritzacioDelegacioID" type="file" />
                  <label class="custom-file-label" for="pdfAutoritzacioDelegacioID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.pdfAutoritzacioDelegacio}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.entitat.pdfAutoritzacioDelegacio)}"/>">${__theForm.entitat.pdfAutoritzacioDelegacio.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="pdfAutoritzacioDelegacioID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#pdfAutoritzacioDelegacioID').on('change', function(){
						var ruta = $('#pdfAutoritzacioDelegacioID').val(); 
						var rutaArray = ruta.split('\\');
						$('#pdfAutoritzacioDelegacioID-custom-file-label').css('display','block');
						$('#pdfAutoritzacioDelegacioID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <tr id="entitat_suportTelefon_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTTELEFON])?'entitat.suportTelefon':__theForm.labels[EntitatFields.SUPORTTELEFON]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTTELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTTELEFON]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportTelefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.suportTelefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <tr id="entitat_suportWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTWEB])?'entitat.suportWeb':__theForm.labels[EntitatFields.SUPORTWEB]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportWeb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? ' uneditable-input' : ''}"  style="" maxlength="250" path="entitat.suportWeb"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <tr id="entitat_suportEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTEMAIL])?'entitat.suportEmail':__theForm.labels[EntitatFields.SUPORTEMAIL]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="entitat.suportEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.USUARIAPLICACIOID)}">
        <tr id="entitat_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.USUARIAPLICACIOID])?'entitat.usuariAplicacioID':__theForm.labels[EntitatFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.USUARIAPLICACIOID)}" >
          <form:hidden path="entitat.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.usuariAplicacioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.usuariAplicacioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.MAXUPLOADSIZE)}">
        <tr id="entitat_maxUploadSize_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.MAXUPLOADSIZE])?'entitat.maxUploadSize':__theForm.labels[EntitatFields.MAXUPLOADSIZE]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.MAXUPLOADSIZE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.MAXUPLOADSIZE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.maxUploadSize" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXUPLOADSIZE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXUPLOADSIZE)? ' uneditable-input' : ''}"  style=""  path="entitat.maxUploadSize"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.MAXSIZEFITXERADAPTAT)}">
        <tr id="entitat_maxSizeFitxerAdaptat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.MAXSIZEFITXERADAPTAT])?'entitat.maxSizeFitxerAdaptat':__theForm.labels[EntitatFields.MAXSIZEFITXERADAPTAT]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.MAXSIZEFITXERADAPTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.MAXSIZEFITXERADAPTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.maxSizeFitxerAdaptat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXSIZEFITXERADAPTAT)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXSIZEFITXERADAPTAT)? ' uneditable-input' : ''}"  style=""  path="entitat.maxSizeFitxerAdaptat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
        <tr id="entitat_maxFilesToSignAtSameTime_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.MAXFILESTOSIGNATSAMETIME])?'entitat.maxFilesToSignAtSameTime':__theForm.labels[EntitatFields.MAXFILESTOSIGNATSAMETIME]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.MAXFILESTOSIGNATSAMETIME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.MAXFILESTOSIGNATSAMETIME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.maxFilesToSignAtSameTime" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXFILESTOSIGNATSAMETIME)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.MAXFILESTOSIGNATSAMETIME)? ' uneditable-input' : ''}"  style=""  path="entitat.maxFilesToSignAtSameTime"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.USPOLITICADEFIRMA)}">
        <tr id="entitat_usPoliticaDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.USPOLITICADEFIRMA])?'entitat.usPoliticaDeFirma':__theForm.labels[EntitatFields.USPOLITICADEFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.USPOLITICADEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.USPOLITICADEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.usPoliticaDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.USPOLITICADEFIRMA)}" >
          <form:hidden path="entitat.usPoliticaDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.usPoliticaDeFirma,__theForm.listOfValuesForUsPoliticaDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.USPOLITICADEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_usPoliticaDeFirma"  onchange="if(typeof onChangeUsPoliticaDeFirma == 'function') {  onChangeUsPoliticaDeFirma(this); };"  cssClass="form-control col-md-9-optional" path="entitat.usPoliticaDeFirma">
            <c:forEach items="${__theForm.listOfValuesForUsPoliticaDeFirma}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLICYIDENTIFIER)}">
        <tr id="entitat_policyIdentifier_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLICYIDENTIFIER])?'entitat.policyIdentifier':__theForm.labels[EntitatFields.POLICYIDENTIFIER]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.POLICYIDENTIFIER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLICYIDENTIFIER]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.policyIdentifier" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYIDENTIFIER)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYIDENTIFIER)? ' uneditable-input' : ''}"  style="" maxlength="100" path="entitat.policyIdentifier"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASH)}">
        <tr id="entitat_policyIdentifierHash_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLICYIDENTIFIERHASH])?'entitat.policyIdentifierHash':__theForm.labels[EntitatFields.POLICYIDENTIFIERHASH]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.POLICYIDENTIFIERHASH]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLICYIDENTIFIERHASH]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.policyIdentifierHash" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYIDENTIFIERHASH)? 'true' : 'false'}" path="entitat.policyIdentifierHash"  />
      <div id="dropdownMenuButton_policyIdentifierHash" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_policyIdentifierHash" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.policyIdentifierHash'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.policyIdentifierHash'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.policyIdentifierHash'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_policyIdentifierHash').on('click', function(){
					var valor = ($('#dropdownMenuContainer_policyIdentifierHash').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_policyIdentifierHash').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <tr id="entitat_policyIdentifierHashAlgorithm_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLICYIDENTIFIERHASHALGORITHM])?'entitat.policyIdentifierHashAlgorithm':__theForm.labels[EntitatFields.POLICYIDENTIFIERHASHALGORITHM]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.POLICYIDENTIFIERHASHALGORITHM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLICYIDENTIFIERHASHALGORITHM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.policyIdentifierHashAlgorithm" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.policyIdentifierHashAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLICYURLDOCUMENT)}">
        <tr id="entitat_policyUrlDocument_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLICYURLDOCUMENT])?'entitat.policyUrlDocument':__theForm.labels[EntitatFields.POLICYURLDOCUMENT]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.POLICYURLDOCUMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLICYURLDOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYURLDOCUMENT)}">

             <c:if test="${ not empty __theForm.entitat.policyUrlDocument}">
               <a href="${__theForm.entitat.policyUrlDocument}" target="_blank">${__theForm.entitat.policyUrlDocument}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYURLDOCUMENT))}">

            <form:errors path="entitat.policyUrlDocument" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYURLDOCUMENT)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLICYURLDOCUMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.policyUrlDocument"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
        <tr id="entitat_motiuDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.MOTIUDELEGACIOID])?'entitat.motiuDelegacioID':__theForm.labels[EntitatFields.MOTIUDELEGACIOID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.MOTIUDELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.MOTIUDELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="entitat.motiuDelegacio" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}">
               <form:errors path="entitat.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="entitat.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.MOTIUDELEGACIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EntitatFields.MOTIUDELEGACIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.FIRMATPERFORMATID)}">
        <tr id="entitat_firmatPerFormatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.FIRMATPERFORMATID])?'entitat.firmatPerFormatID':__theForm.labels[EntitatFields.FIRMATPERFORMATID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.FIRMATPERFORMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.FIRMATPERFORMATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="entitat.firmatPerFormat" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}">
               <form:errors path="entitat.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="entitat.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,EntitatFields.FIRMATPERFORMATID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EntitatFields.FIRMATPERFORMATID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ALGORISMEDEFIRMAID)}">
        <tr id="entitat_algorismeDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ALGORISMEDEFIRMAID])?'entitat.algorismeDeFirmaID':__theForm.labels[EntitatFields.ALGORISMEDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.ALGORISMEDEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.algorismeDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="entitat.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.algorismeDeFirmaID,__theForm.listOfValuesForAlgorismeDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ALGORISMEDEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_algorismeDeFirmaID"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.algorismeDeFirmaID">
            <c:forEach items="${__theForm.listOfValuesForAlgorismeDeFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLITICACUSTODIA)}">
        <tr id="entitat_politicaCustodia_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLITICACUSTODIA])?'entitat.politicaCustodia':__theForm.labels[EntitatFields.POLITICACUSTODIA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.POLITICACUSTODIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLITICACUSTODIA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.politicaCustodia" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICACUSTODIA)}" >
          <form:hidden path="entitat.politicaCustodia"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.politicaCustodia,__theForm.listOfValuesForPoliticaCustodia)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICACUSTODIA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_politicaCustodia"  onchange="if(typeof onChangePoliticaCustodia == 'function') {  onChangePoliticaCustodia(this); };"  cssClass="form-control col-md-9-optional" path="entitat.politicaCustodia">
            <c:forEach items="${__theForm.listOfValuesForPoliticaCustodia}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CUSTODIAINFOID)}">
        <tr id="entitat_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CUSTODIAINFOID])?'entitat.custodiaInfoID':__theForm.labels[EntitatFields.CUSTODIAINFOID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.CUSTODIAINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CUSTODIAINFOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.custodiaInfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.CUSTODIAINFOID)}" >
          <form:hidden path="entitat.custodiaInfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.CUSTODIAINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.custodiaInfoID">
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.custodiaInfoID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.custodiaInfoID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLITICATAULAFIRMES)}">
        <tr id="entitat_politicaTaulaFirmes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLITICATAULAFIRMES])?'entitat.politicaTaulaFirmes':__theForm.labels[EntitatFields.POLITICATAULAFIRMES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.POLITICATAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLITICATAULAFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.politicaTaulaFirmes" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICATAULAFIRMES)}" >
          <form:hidden path="entitat.politicaTaulaFirmes"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.politicaTaulaFirmes,__theForm.listOfValuesForPoliticaTaulaFirmes)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICATAULAFIRMES)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_politicaTaulaFirmes"  onchange="if(typeof onChangePoliticaTaulaFirmes == 'function') {  onChangePoliticaTaulaFirmes(this); };"  cssClass="form-control col-md-9-optional" path="entitat.politicaTaulaFirmes">
            <c:forEach items="${__theForm.listOfValuesForPoliticaTaulaFirmes}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POSICIOTAULAFIRMES)}">
        <tr id="entitat_posicioTaulaFirmes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POSICIOTAULAFIRMES])?'entitat.posicioTaulaFirmes':__theForm.labels[EntitatFields.POSICIOTAULAFIRMES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.POSICIOTAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POSICIOTAULAFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.posicioTaulaFirmes" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.POSICIOTAULAFIRMES)}" >
          <form:hidden path="entitat.posicioTaulaFirmes"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.posicioTaulaFirmes,__theForm.listOfValuesForPosicioTaulaFirmes)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.POSICIOTAULAFIRMES)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_posicioTaulaFirmes"  onchange="if(typeof onChangePosicioTaulaFirmes == 'function') {  onChangePosicioTaulaFirmes(this); };"  cssClass="form-control col-md-9-optional" path="entitat.posicioTaulaFirmes">
            <c:forEach items="${__theForm.listOfValuesForPosicioTaulaFirmes}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PROPIETATSTAULAFIRMES)}">
        <tr id="entitat_propietatsTaulaFirmes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PROPIETATSTAULAFIRMES])?'entitat.propietatsTaulaFirmes':__theForm.labels[EntitatFields.PROPIETATSTAULAFIRMES]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PROPIETATSTAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PROPIETATSTAULAFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.propietatsTaulaFirmes" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.PROPIETATSTAULAFIRMES)? 'true' : 'false'}" path="entitat.propietatsTaulaFirmes"  />
      <div id="dropdownMenuButton_propietatsTaulaFirmes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propietatsTaulaFirmes" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatsTaulaFirmes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatsTaulaFirmes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatsTaulaFirmes'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propietatsTaulaFirmes').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propietatsTaulaFirmes').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propietatsTaulaFirmes').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.POLITICASEGELLATDETEMPS)}">
        <tr id="entitat_politicaSegellatDeTemps_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.POLITICASEGELLATDETEMPS])?'entitat.politicaSegellatDeTemps':__theForm.labels[EntitatFields.POLITICASEGELLATDETEMPS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.POLITICASEGELLATDETEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.POLITICASEGELLATDETEMPS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.politicaSegellatDeTemps" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICASEGELLATDETEMPS)}" >
          <form:hidden path="entitat.politicaSegellatDeTemps"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.politicaSegellatDeTemps,__theForm.listOfValuesForPoliticaSegellatDeTemps)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.POLITICASEGELLATDETEMPS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_politicaSegellatDeTemps"  onchange="if(typeof onChangePoliticaSegellatDeTemps == 'function') {  onChangePoliticaSegellatDeTemps(this); };"  cssClass="form-control col-md-9-optional" path="entitat.politicaSegellatDeTemps">
            <c:forEach items="${__theForm.listOfValuesForPoliticaSegellatDeTemps}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PLUGINSEGELLTEMPSID)}">
        <tr id="entitat_pluginSegellTempsID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PLUGINSEGELLTEMPSID])?'entitat.pluginSegellTempsID':__theForm.labels[EntitatFields.PLUGINSEGELLTEMPSID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PLUGINSEGELLTEMPSID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PLUGINSEGELLTEMPSID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.pluginSegellTempsID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINSEGELLTEMPSID)}" >
          <form:hidden path="entitat.pluginSegellTempsID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.pluginSegellTempsID,__theForm.listOfPluginForPluginSegellTempsID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINSEGELLTEMPSID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_pluginSegellTempsID"  onchange="if(typeof onChangePluginSegellTempsID == 'function') {  onChangePluginSegellTempsID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.pluginSegellTempsID">
            <c:forEach items="${__theForm.listOfPluginForPluginSegellTempsID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.pluginSegellTempsID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.pluginSegellTempsID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PLUGINRUBRICAID)}">
        <tr id="entitat_pluginRubricaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PLUGINRUBRICAID])?'entitat.pluginRubricaID':__theForm.labels[EntitatFields.PLUGINRUBRICAID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PLUGINRUBRICAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PLUGINRUBRICAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.pluginRubricaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINRUBRICAID)}" >
          <form:hidden path="entitat.pluginRubricaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.pluginRubricaID,__theForm.listOfPluginForPluginRubricaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINRUBRICAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_pluginRubricaID"  onchange="if(typeof onChangePluginRubricaID == 'function') {  onChangePluginRubricaID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.pluginRubricaID">
            <c:forEach items="${__theForm.listOfPluginForPluginRubricaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.pluginRubricaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.pluginRubricaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.VALIDARFIRMA)}">
        <tr id="entitat_validarfirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.VALIDARFIRMA])?'entitat.validarfirma':__theForm.labels[EntitatFields.VALIDARFIRMA]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.VALIDARFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.VALIDARFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.VALIDARFIRMA)}" >
              <form:errors path="entitat.validarfirma" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.VALIDARFIRMA)? 'false' : 'true'}" path="entitat.validarfirma" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.VALIDARFIRMA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.validarfirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.COMPROVARNIFFIRMA)}">
        <tr id="entitat_comprovarNifFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.COMPROVARNIFFIRMA])?'entitat.comprovarNifFirma':__theForm.labels[EntitatFields.COMPROVARNIFFIRMA]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.COMPROVARNIFFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.COMPROVARNIFFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.COMPROVARNIFFIRMA)}" >
              <form:errors path="entitat.comprovarNifFirma" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.COMPROVARNIFFIRMA)? 'false' : 'true'}" path="entitat.comprovarNifFirma" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.COMPROVARNIFFIRMA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.comprovarNifFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
        <tr id="entitat_checkCanviatDocFirmat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CHECKCANVIATDOCFIRMAT])?'entitat.checkCanviatDocFirmat':__theForm.labels[EntitatFields.CHECKCANVIATDOCFIRMAT]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:errors path="entitat.checkCanviatDocFirmat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)? 'false' : 'true'}" path="entitat.checkCanviatDocFirmat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.checkCanviatDocFirmat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PLUGINVALIDAFIRMESID)}">
        <tr id="entitat_pluginValidaFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PLUGINVALIDAFIRMESID])?'entitat.pluginValidaFirmesID':__theForm.labels[EntitatFields.PLUGINVALIDAFIRMESID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PLUGINVALIDAFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PLUGINVALIDAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.pluginValidaFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINVALIDAFIRMESID)}" >
          <form:hidden path="entitat.pluginValidaFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.pluginValidaFirmesID,__theForm.listOfPluginForPluginValidaFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINVALIDAFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_pluginValidaFirmesID"  onchange="if(typeof onChangePluginValidaFirmesID == 'function') {  onChangePluginValidaFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.pluginValidaFirmesID">
            <c:forEach items="${__theForm.listOfPluginForPluginValidaFirmesID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.pluginValidaFirmesID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.pluginValidaFirmesID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PLUGINVALIDACERTIFICATID)}">
        <tr id="entitat_pluginValidaCertificatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PLUGINVALIDACERTIFICATID])?'entitat.pluginValidaCertificatID':__theForm.labels[EntitatFields.PLUGINVALIDACERTIFICATID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PLUGINVALIDACERTIFICATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PLUGINVALIDACERTIFICATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.pluginValidaCertificatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINVALIDACERTIFICATID)}" >
          <form:hidden path="entitat.pluginValidaCertificatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.pluginValidaCertificatID,__theForm.listOfPluginForPluginValidaCertificatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINVALIDACERTIFICATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_pluginValidaCertificatID"  onchange="if(typeof onChangePluginValidaCertificatID == 'function') {  onChangePluginValidaCertificatID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.pluginValidaCertificatID">
            <c:forEach items="${__theForm.listOfPluginForPluginValidaCertificatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.pluginValidaCertificatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.pluginValidaCertificatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
