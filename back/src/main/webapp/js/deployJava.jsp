<%@page import="java.net.URL"
%><%@page import="java.net.HttpURLConnection"
%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%><%!

  public static boolean exists(String URLName){
    try {
      HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
      //        HttpURLConnection.setInstanceFollowRedirects(false)
      HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
      con.setRequestMethod("HEAD");
      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }
    catch (Exception e) {
       e.printStackTrace();
       return false;
    }
  }

%><%

  String url = "http://java.com/js/deployJava.js";
  if (!exists(url)) {
    url = request.getContextPath() + "/js/deployJava.js";
  }
  response.sendRedirect(url);
  
%>