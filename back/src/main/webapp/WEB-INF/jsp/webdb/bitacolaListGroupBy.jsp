<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  

  <%-- HIDDEN PARAMS: GROUP BY --%>
  <form:hidden path="visibleGroupBy"/>
  <form:hidden id="agruparPerCamp" path="groupBy"/> 
  <form:hidden id="agruparPerValor"  path="groupValue"/>

<%-- AGRUPAR PER - FUNCIONS --%>   
<script type="text/javascript">
  $("body").on("nodeselect.tree.data-api", "[role=leaf]", function (e) {

      var parentstr = "" + e.node.parentage[e.node.parentage.length - 1];
      document.getElementById('agruparPerCamp').value = parentstr;
      document.getElementById('agruparPerValor').value = e.node.value;
      document.bitacola.submit();
  })

  function groupByFieldValue(camp, valor) {
    document.getElementById('agruparPerCamp').value = camp;
    document.getElementById('agruparPerValor').value = valor;
    document.bitacola.submit();
  }

</script>

  <%-- AGRUPAR PER - INICI --%>   

  <c:set var="displayGroupDiv" value="${__theFilterForm.visibleGroupBy?'':'display:none;'}" />  

  <c:if test="${fn:length(groupby_items) > 0}">
 <fmt:message var="buit" key="genapp.notdefined" />
  
  <div id="GroupDiv" class="well" style="${displayGroupDiv} padding: 1px; margin-right: 4px;  float: left; ">
      
      <div class="pull-right" style="padding-left:2px">
            <div class="span10">
               <i title="<fmt:message key="genapp.form.hidegroupby"/>" onclick="document.getElementById('GroupDiv').style.display='none'; document.getElementById('GroupButton').style.display='inline';" class="icon-remove"></i>
            </div>
      </div>


      <ul class="tree" style="margin:3px; padding:0px; float: left; ">

        <li>
          <a href="#" role="branch" class="tree-toggle" data-toggle="branch" data-value=" "><b><fmt:message key="genapp.form.groupby"/></b></a>
          <ul class="branch in">
              <c:if test="${IE8}">
                <c:set var="linkItem" value="onclick=\"groupByFieldValue(' ',' ')\"" />
              </c:if>
              <li><a href="#" role="leaf" data-value="" ${linkItem} >&raquo; <span style="${(__theFilterForm.groupBy eq null)? "font-weight: bold;" : ""}"><fmt:message key="genapp.form.groupby.noneitem"/></span></a></li>


              <c:forEach  var="groupby_item"  items="${groupby_items}"> 
                <li>
                  <a href="#" role="branch" class="tree-toggle ${groupby_item.selected? "" : "closed"}" data-toggle="branch" data-value="${groupby_item.value}">
                    <span style="${groupby_item.selected? "font-weight: bold;" : ""}">
                    <c:set var="code" value="${(empty __theFilterForm.labels[groupby_item.field])? groupby_item.codeLabel:__theFilterForm.labels[groupby_item.field]}" />
                        <c:if test="${!fn:startsWith(code,'=')}" >
                        <fmt:message key="${code}">
                              <fmt:param><fmt:message key="${groupby_item.codeParamLabel}"/></fmt:param>
                        </fmt:message>
                        </c:if>
                        <c:if test="${fn:startsWith(code,'=')}" >
                        <c:out value="${fn:substringAfter(code, '=')}" escapeXml="false" />
                        </c:if>
                    </span>
                  </a>
                  <%-- AQUI VANS ELS VALUES --%>
                  <ul class="${(groupby_item.selected || IE8)? "branch in" : "branch"}">
                  <c:forEach  var="groupbyvalue_item"  items="${groupby_item.values}">
                    <li>
                      <c:if test="${IE8}">
                        <c:set var="linkItem" value="onclick=\"groupByFieldValue('${groupby_item.value}','${groupbyvalue_item.value}')\"" />
                      </c:if>
                      <a href="#" role="leaf" data-value="${groupbyvalue_item.value}" ${linkItem} >
                        &raquo; <span style="${groupbyvalue_item.selected? "font-weight: bold;" : ""}" >
                          ${ (empty groupbyvalue_item.codeLabel) ? buit : groupbyvalue_item.codeLabel } (${groupbyvalue_item.count})
                      </span>
                      </a>
                    </li>
                  </c:forEach>
                  </ul>
                </li>
              </c:forEach>

          </ul>
        </li>
      </ul>

  </div>
 </c:if>
 
  <%-- AGRUPAR PER - FINAL --%>

