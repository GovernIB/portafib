<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div>
  <h5>Desenvolupament</h5>

  
  <ul class="tree" style="margin: 3px; padding: 0px;">
  
  
    <li>
      <a href="#" role="branch" class="tree-toggle" data-toggle="branch"><span >Per Executar com ROLE_USER</span></a>
      <ul class="branch in">
               
        
        <li style="list-style-type: disc; list-style-position: inside;">
          <a href="<c:url value="/webdb/annex/list/1"/>">
            <span>JSP no accessible per ROLE_USER (Error 403)</span>
          </a>
        </li>
        
        <li style="list-style-type: disc; list-style-position: inside;">
          <a href="<c:url value="/deletemethod"/>">
            <span>METODE no accesible per ROLE_DEST (javax.ejb.EJBAccessException: Caller unauthorized) </span>
          </a>
        </li>
        
      </ul>
    </li>
  
    <li>
      <a href="#" role="branch" class="tree-toggle" data-toggle="branch"><span >Per Executar com ROLE_ADMIN</span></a>
      <ul class="branch in">
    

    
    
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="/hibernateerror"/>">
          <span>Hibernate Error </span>
        </a>
      </li>
      
      </ul>
    </li>
    
    <li>
      <a href="#" role="branch" class="tree-toggle" data-toggle="branch"><span >Per Executar qualsevol</span></a>
      <ul class="branch in">
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/modelandviewnoexisteix"/>">
              <span>ModelAndView noexisteix</span>
            </a>
          </li>
          
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/errorcallback"/>">
              <span>Error Call Back</span>
            </a>
          </li>

          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/errorinvaliduser"/>">
              <span>Invalid User</span>
            </a>
          </li>
          
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/servletexception"/>">
              <span>ServletException</span>
            </a>
          </li>
          
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/jspexception"/>">
              <span>Error en la vista (jsp)</span>
            </a>
          </li>
          
          <%-- XYZ  --%>
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/firmanuvol"/>">
              <span>FirmaNuvol</span>
            </a>
          </li>

          
          <%-- XYZ --%>
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/develop/signmodule"/>">
              <span>AutoFirmaNuvol</span>
            </a>
          </li>
          
          

      </ul>
    </li>


  </ul>
</div>

