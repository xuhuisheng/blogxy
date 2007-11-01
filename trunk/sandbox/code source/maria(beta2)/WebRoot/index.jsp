<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<html>
  <head>
    <%@ include file="/commons/meta.jsp" %>
    <title>index</title>

  </head>
  
  <body>
    <s:form action="user/list.do">
    	<s:submit/>
    </s:form>
    <a href="<s:url action="user/list" />">列表页面</a><br/>
    <a href="<s:url action="user/edit"><s:param name="id" value="1" /></s:url>">编辑页面</a>
  </body>
</html>
