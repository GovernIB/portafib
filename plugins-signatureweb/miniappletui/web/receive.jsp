<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%><%@page import = "java.io.*"
%><%!
 
%><%  
  try {
    // Llegir certificat com un Stream
    InputStream is = request.getInputStream();
    
	
	File jsp = new File(request.getSession().getServletContext().getRealPath("."));
     
	 String name = String.valueOf(System.currentTimeMillis()) + ".pdf";
	 FileOutputStream fos = new FileOutputStream(new File(jsp, name));
	 int b;
	 while((b = is.read()) != -1) {
       fos.write(b);
     }
	 
	 fos.close();
  
      session.setAttribute("fitxer", name);
    	
	
  } catch(Throwable e) {
     e.printStackTrace();
     throw new ServletException(e);
  }
%>