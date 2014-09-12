<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaUsuariEntitatFields" className="es.caib.portafib.model.fields.PeticioDeFirmaUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID])?'peticioDeFirmaUsuariEntitat.peticioDeFirmaID':__theForm.labels[PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirmaUsuariEntitat.peticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}" >
          <form:hidden path="peticioDeFirmaUsuariEntitat.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirmaUsuariEntitat.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}" >
          <form:select id="peticioDeFirmaUsuariEntitat_peticioDeFirmaID" cssClass="input-xxlarge" path="peticioDeFirmaUsuariEntitat.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID])?'peticioDeFirmaUsuariEntitat.usuariEntitatID':__theForm.labels[PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirmaUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="peticioDeFirmaUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirmaUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}" >
          <form:select id="peticioDeFirmaUsuariEntitat_usuariEntitatID" cssClass="input-xxlarge" path="peticioDeFirmaUsuariEntitat.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID])?'peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID':__theForm.labels[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}" >
          <form:hidden path="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID,__theForm.listOfMitjaDeComunicacioForMitjaDeComunicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}" >
          <form:select id="peticioDeFirmaUsuariEntitat_mitjaDeComunicacioID" cssClass="input-xxlarge" path="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfMitjaDeComunicacioForMitjaDeComunicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA])?'peticioDeFirmaUsuariEntitat.mitjaDeComunicacioAdreza':__theForm.labels[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioAdreza" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="peticioDeFirmaUsuariEntitat.mitjaDeComunicacioAdreza" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaUsuariEntitatFields.AVISWEB])?'peticioDeFirmaUsuariEntitat.avisWeb':__theForm.labels[PeticioDeFirmaUsuariEntitatFields.AVISWEB]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaUsuariEntitatFields.AVISWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaUsuariEntitatFields.AVISWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}" >
              <form:select cssClass="input-medium"  path="peticioDeFirmaUsuariEntitat.avisWeb">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.peticioDeFirmaUsuariEntitat.avisWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
