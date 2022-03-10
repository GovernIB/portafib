<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FirmaFields" className="es.caib.portafib.model.fields.FirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.DESTINATARIID)}">
        <tr id="firma_destinatariID_rowid">
          <td id="firma_destinatariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.DESTINATARIID])?'firma.destinatariID':__theForm.labels[FirmaFields.DESTINATARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.DESTINATARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.DESTINATARIID]}" ></i>
              </c:if>
            </td>
          <td id="firma_destinatariID_columnvalueid">
          <form:errors path="firma.destinatariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.DESTINATARIID)}" >
          <form:hidden path="firma.destinatariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.firma.destinatariID,__theForm.listOfUsuariEntitatForDestinatariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.DESTINATARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="firma_destinatariID"  onchange="if(typeof onChangeDestinatariID == 'function') {  onChangeDestinatariID(this); };"  cssClass="form-control col-md-9-optional" path="firma.destinatariID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForDestinatariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.BLOCDEFIRMAID)}">
        <tr id="firma_blocDeFirmaID_rowid">
          <td id="firma_blocDeFirmaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.BLOCDEFIRMAID])?'firma.blocDeFirmaID':__theForm.labels[FirmaFields.BLOCDEFIRMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.BLOCDEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.BLOCDEFIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="firma_blocDeFirmaID_columnvalueid">
          <form:errors path="firma.blocDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.BLOCDEFIRMAID)}" >
          <form:hidden path="firma.blocDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.firma.blocDeFirmaID,__theForm.listOfBlocDeFirmesForBlocDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.BLOCDEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="firma_blocDeFirmaID"  onchange="if(typeof onChangeBlocDeFirmaID == 'function') {  onChangeBlocDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="firma.blocDeFirmaID">
            <c:forEach items="${__theForm.listOfBlocDeFirmesForBlocDeFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.OBLIGATORI)}">
        <tr id="firma_obligatori_rowid">
          <td id="firma_obligatori_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.OBLIGATORI])?'firma.obligatori':__theForm.labels[FirmaFields.OBLIGATORI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.OBLIGATORI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.OBLIGATORI]}" ></i>
              </c:if>
            </td>
          <td id="firma_obligatori_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)}" >
              <form:errors path="firma.obligatori" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)? 'false' : 'true'}" path="firma.obligatori" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.obligatori}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.FITXERFIRMATID)}">
        <tr id="firma_fitxerFirmatID_rowid">
          <td id="firma_fitxerFirmatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.FITXERFIRMATID])?'firma.fitxerFirmatID':__theForm.labels[FirmaFields.FITXERFIRMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.FITXERFIRMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.FITXERFIRMATID]}" ></i>
              </c:if>
            </td>
          <td id="firma_fitxerFirmatID_columnvalueid">
              <form:errors path="firma.fitxerFirmatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.firma.fitxerFirmat)}"/>">${__theForm.firma.fitxerFirmat.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)? ' uneditable-input' : ''}"   path="fitxerFirmatID" type="file" />
                  <label class="custom-file-label" for="fitxerFirmatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.firma.fitxerFirmat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.firma.fitxerFirmat)}"/>">${__theForm.firma.fitxerFirmat.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerFirmatIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerFirmatID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerFirmatID').on('change', function(){
						var ruta = $('#fitxerFirmatID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerFirmatID-custom-file-label').css('display','block');
						$('#fitxerFirmatID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NUMFIRMADOCUMENT)}">
        <tr id="firma_numFirmaDocument_rowid">
          <td id="firma_numFirmaDocument_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NUMFIRMADOCUMENT])?'firma.numFirmaDocument':__theForm.labels[FirmaFields.NUMFIRMADOCUMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.NUMFIRMADOCUMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.NUMFIRMADOCUMENT]}" ></i>
              </c:if>
            </td>
          <td id="firma_numFirmaDocument_columnvalueid">
            <form:errors path="firma.numFirmaDocument" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMFIRMADOCUMENT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMFIRMADOCUMENT)? ' uneditable-input' : ''}"  style=""  path="firma.numFirmaDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAPAGINA)}">
        <tr id="firma_caixaPagina_rowid">
          <td id="firma_caixaPagina_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAPAGINA])?'firma.caixaPagina':__theForm.labels[FirmaFields.CAIXAPAGINA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAPAGINA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CAIXAPAGINA]}" ></i>
              </c:if>
            </td>
          <td id="firma_caixaPagina_columnvalueid">
            <form:errors path="firma.caixaPagina" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAPAGINA)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAPAGINA)? ' uneditable-input' : ''}"  style=""  path="firma.caixaPagina"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAX)}">
        <tr id="firma_caixaX_rowid">
          <td id="firma_caixaX_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAX])?'firma.caixaX':__theForm.labels[FirmaFields.CAIXAX]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAX]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CAIXAX]}" ></i>
              </c:if>
            </td>
          <td id="firma_caixaX_columnvalueid">
            <form:errors path="firma.caixaX" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAX)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAX)? ' uneditable-input' : ''}"  style=""  path="firma.caixaX"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAY)}">
        <tr id="firma_caixaY_rowid">
          <td id="firma_caixaY_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAY])?'firma.caixaY':__theForm.labels[FirmaFields.CAIXAY]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAY]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CAIXAY]}" ></i>
              </c:if>
            </td>
          <td id="firma_caixaY_columnvalueid">
            <form:errors path="firma.caixaY" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAY)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAY)? ' uneditable-input' : ''}"  style=""  path="firma.caixaY"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAAMPLE)}">
        <tr id="firma_caixaAmple_rowid">
          <td id="firma_caixaAmple_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAAMPLE])?'firma.caixaAmple':__theForm.labels[FirmaFields.CAIXAAMPLE]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAAMPLE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CAIXAAMPLE]}" ></i>
              </c:if>
            </td>
          <td id="firma_caixaAmple_columnvalueid">
            <form:errors path="firma.caixaAmple" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAAMPLE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAAMPLE)? ' uneditable-input' : ''}"  style=""  path="firma.caixaAmple"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAALT)}">
        <tr id="firma_caixaAlt_rowid">
          <td id="firma_caixaAlt_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAALT])?'firma.caixaAlt':__theForm.labels[FirmaFields.CAIXAALT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAALT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CAIXAALT]}" ></i>
              </c:if>
            </td>
          <td id="firma_caixaAlt_columnvalueid">
            <form:errors path="firma.caixaAlt" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAALT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAALT)? ' uneditable-input' : ''}"  style=""  path="firma.caixaAlt"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NUMEROSERIECERTIFICAT)}">
        <tr id="firma_numeroSerieCertificat_rowid">
          <td id="firma_numeroSerieCertificat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NUMEROSERIECERTIFICAT])?'firma.numeroSerieCertificat':__theForm.labels[FirmaFields.NUMEROSERIECERTIFICAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.NUMEROSERIECERTIFICAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.NUMEROSERIECERTIFICAT]}" ></i>
              </c:if>
            </td>
          <td id="firma_numeroSerieCertificat_columnvalueid">
            <form:errors path="firma.numeroSerieCertificat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMEROSERIECERTIFICAT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMEROSERIECERTIFICAT)? ' uneditable-input' : ''}"  style=""  path="firma.numeroSerieCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.EMISSORCERTIFICAT)}">
        <tr id="firma_emissorCertificat_rowid">
          <td id="firma_emissorCertificat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.EMISSORCERTIFICAT])?'firma.emissorCertificat':__theForm.labels[FirmaFields.EMISSORCERTIFICAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.EMISSORCERTIFICAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.EMISSORCERTIFICAT]}" ></i>
              </c:if>
            </td>
          <td id="firma_emissorCertificat_columnvalueid">
            <form:errors path="firma.emissorCertificat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.EMISSORCERTIFICAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.EMISSORCERTIFICAT)? ' uneditable-input' : ''}"  style="" maxlength="1000" path="firma.emissorCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NOMCERTIFICAT)}">
        <tr id="firma_nomCertificat_rowid">
          <td id="firma_nomCertificat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NOMCERTIFICAT])?'firma.nomCertificat':__theForm.labels[FirmaFields.NOMCERTIFICAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.NOMCERTIFICAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.NOMCERTIFICAT]}" ></i>
              </c:if>
            </td>
          <td id="firma_nomCertificat_columnvalueid">
            <form:errors path="firma.nomCertificat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NOMCERTIFICAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.NOMCERTIFICAT)? ' uneditable-input' : ''}"  style="" maxlength="1000" path="firma.nomCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <tr id="firma_tipusEstatDeFirmaFinalID_rowid">
          <td id="firma_tipusEstatDeFirmaFinalID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.TIPUSESTATDEFIRMAFINALID])?'firma.tipusEstatDeFirmaFinalID':__theForm.labels[FirmaFields.TIPUSESTATDEFIRMAFINALID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
            </td>
          <td id="firma_tipusEstatDeFirmaFinalID_columnvalueid">
          <form:errors path="firma.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:hidden path="firma.tipusEstatDeFirmaFinalID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.firma.tipusEstatDeFirmaFinalID,__theForm.listOfValuesForTipusEstatDeFirmaFinalID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="firma_tipusEstatDeFirmaFinalID"  onchange="if(typeof onChangeTipusEstatDeFirmaFinalID == 'function') {  onChangeTipusEstatDeFirmaFinalID(this); };"  cssClass="form-control col-md-9-optional" path="firma.tipusEstatDeFirmaFinalID">
            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaFinalID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.firma.tipusEstatDeFirmaFinalID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.firma.tipusEstatDeFirmaFinalID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MOSTRARRUBRICA)}">
        <tr id="firma_mostrarRubrica_rowid">
          <td id="firma_mostrarRubrica_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MOSTRARRUBRICA])?'firma.mostrarRubrica':__theForm.labels[FirmaFields.MOSTRARRUBRICA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.MOSTRARRUBRICA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.MOSTRARRUBRICA]}" ></i>
              </c:if>
            </td>
          <td id="firma_mostrarRubrica_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)}" >
              <form:errors path="firma.mostrarRubrica" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)? 'false' : 'true'}" path="firma.mostrarRubrica" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.mostrarRubrica}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MOTIU)}">
        <tr id="firma_motiu_rowid">
          <td id="firma_motiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MOTIU])?'firma.motiu':__theForm.labels[FirmaFields.MOTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.MOTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.MOTIU]}" ></i>
              </c:if>
            </td>
          <td id="firma_motiu_columnvalueid">
            <form:errors path="firma.motiu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MOTIU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.MOTIU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="firma.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MINIMDEREVISORS)}">
        <tr id="firma_minimDeRevisors_rowid">
          <td id="firma_minimDeRevisors_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MINIMDEREVISORS])?'firma.minimDeRevisors':__theForm.labels[FirmaFields.MINIMDEREVISORS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.MINIMDEREVISORS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.MINIMDEREVISORS]}" ></i>
              </c:if>
            </td>
          <td id="firma_minimDeRevisors_columnvalueid">
            <form:errors path="firma.minimDeRevisors" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MINIMDEREVISORS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.MINIMDEREVISORS)? ' uneditable-input' : ''}"  style=""  path="firma.minimDeRevisors"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <tr id="firma_checkAdministrationIdOfSigner_rowid">
          <td id="firma_checkAdministrationIdOfSigner_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER])?'firma.checkAdministrationIdOfSigner':__theForm.labels[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}" ></i>
              </c:if>
            </td>
          <td id="firma_checkAdministrationIdOfSigner_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckAdministrationIdOfSigner == 'function') {  onChangeCheckAdministrationIdOfSigner(this); };"  path="firma.checkAdministrationIdOfSigner">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkAdministrationIdOfSigner}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <tr id="firma_checkDocumentModifications_rowid">
          <td id="firma_checkDocumentModifications_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKDOCUMENTMODIFICATIONS])?'firma.checkDocumentModifications':__theForm.labels[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}" ></i>
              </c:if>
            </td>
          <td id="firma_checkDocumentModifications_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckDocumentModifications == 'function') {  onChangeCheckDocumentModifications(this); };"  path="firma.checkDocumentModifications">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkDocumentModifications}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKVALIDATIONSIGNATURE)}">
        <tr id="firma_checkValidationSignature_rowid">
          <td id="firma_checkValidationSignature_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKVALIDATIONSIGNATURE])?'firma.checkValidationSignature':__theForm.labels[FirmaFields.CHECKVALIDATIONSIGNATURE]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKVALIDATIONSIGNATURE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.CHECKVALIDATIONSIGNATURE]}" ></i>
              </c:if>
            </td>
          <td id="firma_checkValidationSignature_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKVALIDATIONSIGNATURE)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckValidationSignature == 'function') {  onChangeCheckValidationSignature(this); };"  path="firma.checkValidationSignature">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKVALIDATIONSIGNATURE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkValidationSignature}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.PERFILDEFIRMA)}">
        <tr id="firma_perfilDeFirma_rowid">
          <td id="firma_perfilDeFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.PERFILDEFIRMA])?'firma.perfilDeFirma':__theForm.labels[FirmaFields.PERFILDEFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.PERFILDEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.PERFILDEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="firma_perfilDeFirma_columnvalueid">
            <form:errors path="firma.perfilDeFirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.PERFILDEFIRMA)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.PERFILDEFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="50" path="firma.perfilDeFirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNNOM)}">
        <tr id="firma_usuariExternNom_rowid">
          <td id="firma_usuariExternNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNNOM])?'firma.usuariExternNom':__theForm.labels[FirmaFields.USUARIEXTERNNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNNOM]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternNom_columnvalueid">
            <form:errors path="firma.usuariExternNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="firma.usuariExternNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNLLINATGES)}">
        <tr id="firma_usuariExternLlinatges_rowid">
          <td id="firma_usuariExternLlinatges_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNLLINATGES])?'firma.usuariExternLlinatges':__theForm.labels[FirmaFields.USUARIEXTERNLLINATGES]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNLLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNLLINATGES]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternLlinatges_columnvalueid">
            <form:errors path="firma.usuariExternLlinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNLLINATGES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNLLINATGES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="firma.usuariExternLlinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNEMAIL)}">
        <tr id="firma_usuariExternEmail_rowid">
          <td id="firma_usuariExternEmail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNEMAIL])?'firma.usuariExternEmail':__theForm.labels[FirmaFields.USUARIEXTERNEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternEmail_columnvalueid">
            <form:errors path="firma.usuariExternEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="firma.usuariExternEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNIDIOMA)}">
        <tr id="firma_usuariExternIdioma_rowid">
          <td id="firma_usuariExternIdioma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNIDIOMA])?'firma.usuariExternIdioma':__theForm.labels[FirmaFields.USUARIEXTERNIDIOMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNIDIOMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNIDIOMA]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternIdioma_columnvalueid">
            <form:errors path="firma.usuariExternIdioma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNIDIOMA)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNIDIOMA)? ' uneditable-input' : ''}"  style="" maxlength="2" path="firma.usuariExternIdioma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNTOKEN)}">
        <tr id="firma_usuariExternToken_rowid">
          <td id="firma_usuariExternToken_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNTOKEN])?'firma.usuariExternToken':__theForm.labels[FirmaFields.USUARIEXTERNTOKEN]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNTOKEN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNTOKEN]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternToken_columnvalueid">
            <form:errors path="firma.usuariExternToken" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNTOKEN)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNTOKEN)? ' uneditable-input' : ''}"  style="" maxlength="255" path="firma.usuariExternToken"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}">
        <tr id="firma_usuariExternNivellSeguretat_rowid">
          <td id="firma_usuariExternNivellSeguretat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNNIVELLSEGURETAT])?'firma.usuariExternNivellSeguretat':__theForm.labels[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}" ></i>
              </c:if>
            </td>
          <td id="firma_usuariExternNivellSeguretat_columnvalueid">
          <form:errors path="firma.usuariExternNivellSeguretat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}" >
          <form:hidden path="firma.usuariExternNivellSeguretat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.firma.usuariExternNivellSeguretat,__theForm.listOfValuesForUsuariExternNivellSeguretat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="firma_usuariExternNivellSeguretat"  onchange="if(typeof onChangeUsuariExternNivellSeguretat == 'function') {  onChangeUsuariExternNivellSeguretat(this); };"  cssClass="form-control col-md-9-optional" path="firma.usuariExternNivellSeguretat">
            <c:forEach items="${__theForm.listOfValuesForUsuariExternNivellSeguretat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.firma.usuariExternNivellSeguretat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.firma.usuariExternNivellSeguretat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
