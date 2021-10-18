<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>
  

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
      document.usuariAplicacioConfiguracio.submit();
  })

  function groupByFieldValue(camp, valor) {
    document.getElementById('agruparPerCamp').value = camp;
    document.getElementById('agruparPerValor').value = valor;
    document.usuariAplicacioConfiguracio.submit();
  }

</script>

  <%-- AGRUPAR PER - INICI --%>   

  <c:set var="displayGroupDiv" value="${__theFilterForm.visibleGroupBy?'':'display:none;'}" />  

  <c:if test="${fn:length(groupby_items) > 0}">
 <fmt:message var="buit" key="genapp.notdefined" />
  
  <div id="GroupDiv" class="well" style="${displayGroupDiv} padding: 1px; margin-right: 4px;  float: left; ">
      
      <div class="float-right" style="padding-left:2px">
            <div class="span10">
               <i title="<fmt:message key="genapp.form.hidegroupby"/>" onclick="document.getElementById('GroupDiv').style.display='none'; document.getElementById('GroupButton').style.display='inline';" class="fas fa-trash"></i>
            </div>
      </div>


      <div id="tree"></div>
      
      <script>
      
      
      <c:set var="expandID" value="-" />
      
      var treedata =
            [{
                "id": '-1',
                "text": "<b><fmt:message key="genapp.form.groupby"/></b>",
                "field": null,
                "hasChildren": true,
                "children": [{
                        "id": '${groupby_item.value}',
                        "text": "&#8811; <span style='${(__theFilterForm.groupBy eq null)? "font-weight: bold;" : ""}'><fmt:message key="genapp.form.groupby.noneitem"/></span>",
                        "field": ' ',
                        "hasChildren": false,
                        "children": []
                    }
                    
                    <c:forEach  var="groupby_item"  items="${groupby_items}">
                    
                        <c:set var="code" value="${(empty __theFilterForm.labels[groupby_item.field])? groupby_item.codeLabel:__theFilterForm.labels[groupby_item.field]}" />
                        <c:if test="${!fn:startsWith(code,'=')}" >
                        <fmt:message var="thetext" key="${code}">
                              <fmt:param><fmt:message key="${groupby_item.codeParamLabel}"/></fmt:param>
                        </fmt:message>
                        </c:if>
                        <c:if test="${fn:startsWith(code,'=')}" >
                        <c:set var="thetext" value="${fn:substringAfter(code, '=')}" />
                        </c:if>
                    
                    ,{
                        "id": '${groupby_item.value}',
                        "text": "<span style='${groupby_item.selected? "font-weight: bold;" : ""}'>${thetext}</span>",
                        "field": '${groupby_item.value}',
                        "hasChildren": true,
                        "children": [
                            
                            <c:set var="counterG" value="0" />
                            
                            <c:forEach  var="groupbyvalue_item"  items="${groupby_item.values}">
                            
                            
                            
                            <c:if test="${counterG ne 0}">,</c:if>
                            <c:set var="counterG" value="${counterG + 1}" />
                            {
                                "id": '${groupby_item.value}_${counterG}',
                                "text": "<span style='${groupbyvalue_item.selected? "font-weight: bold;" : ""}' >${ (empty groupbyvalue_item.codeLabel) ? buit : groupbyvalue_item.codeLabel } (${groupbyvalue_item.count})</span>",
                                "field": '${groupby_item.value}',
                                "value" : '${groupbyvalue_item.value}',
                                "hasChildren": false,
                                "children": []
                            }
                            
                          </c:forEach>
                            
                        ]
                    }   
                    
                    <c:if test="${groupby_item.selected}" >
                        <c:set var="expandID" value="${groupby_item.value}"/>
                    </c:if>
                    
                    </c:forEach>

                ]
            }];

      var tree = $('#tree').tree({
          dataSource: treedata,
          primaryKey: 'id',
          uiLibrary: 'bootstrap4',
          select: function (e, node, id) {
              var nodedata = tree.getDataById(id);
              if (!nodedata.hasChildren) {
                  groupByFieldValue(nodedata.field, nodedata.value);                  
              }
          },
          icons: {
              expand: '<i class="far fa-plus-square"></i>',
              collapse: '<i class="far fa-minus-square"></i>'
          }
        });
      
       <c:if test="${expandID ne '-'}" >
          var node = tree.getNodeById('${expandID}');
          tree.expand(node);
          
          node = tree.getNodeById('-1');
          tree.expand(node);
       </c:if>
      
      
      </script>
  </div>
 </c:if>
 
  <%-- AGRUPAR PER - FINAL --%>

