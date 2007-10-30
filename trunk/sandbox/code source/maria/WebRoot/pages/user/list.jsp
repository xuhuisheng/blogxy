<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<html>
  <head>
    <%@ include file="/commons/meta.jsp" %>
    <title>user grid</title>
	
  </head>
  
  <body>
    <s:property value="name"/>
    <s:iterator value="collection">
    	<a href="<s:url action="user/edit"><s:param name="id" value="id" /></s:url>">编辑</a>
    </s:iterator>    
  </body>
</html>
