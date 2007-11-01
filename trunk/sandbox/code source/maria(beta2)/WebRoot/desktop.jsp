<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<html>
  <head>
    <%@ include file="/commons/meta.jsp" %>
    <title>desktop</title>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/ext2.0/ext-all.css" /> 
    
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/ext-base.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/ext-all.js"></script>
    
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/grid/RowExpander.js"></script>
    
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/StartMenu.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/TaskBar.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/desktop.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/App.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/Module.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/desktop/sample.js"></script>

	<link rel="stylesheet" type="text/css" href="${ctx}/styles/ext2.0/desktop/desktop.css" />
  </head>

  <body scroll="no">
	<div id="x-desktop">
	    <a href="http://extjs.com" target="_blank" style="margin:5px; float:right;"><img src="images/ext2.0/desktop/powered.gif" /></a>
	
	    <dl id="x-shortcuts">
	        <dt id="grid-win-shortcut">
	            <a href="#"><img src="images/ext2.0/desktop/s.gif" />
	            <div>Grid Window</div></a>
	        </dt>
	        <dt id="acc-win-shortcut">
	            <a href="#"><img src="images/ext2.0/desktop/s.gif" />
	            <div>Accordion Window</div></a>
	        </dt>
	    </dl>
	</div>
	
	<div id="ux-taskbar">
		<div id="ux-taskbar-start"></div>
		<div id="ux-taskbuttons-panel"></div>
		<div class="x-clear"></div>
	</div>  
  </body>
</html>
