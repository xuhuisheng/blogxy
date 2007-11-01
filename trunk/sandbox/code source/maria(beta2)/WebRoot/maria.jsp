<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<html>
  <head>
    <%@ include file="/commons/meta.jsp" %>
    <title>user main</title>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/ext2.0/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/ext2.0/docs.css" />
    
	<script type="text/javascript">
		Application = {};
	</script>    
    
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/ext-base.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/ext-all.js"></script>
    
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/layout/layout.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/grid/user.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/grid/role.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/ext2.0/tree/config.js"></script>

	<script type="text/javascript" src="${ctx}/scripts/ext2.0/examples.js"></script>
	
    <style type="text/css">
        body .x-panel {
            margin-bottom:20px;
        }
        .icon-tabs {
            background-image:url(${ctx}/images/ext2.0/desktop/gears.gif) !important;
        }        
        .user-grid {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/fam/user.gif) !important;
        }
        .role-grid {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/fam/user_suit.gif) !important;
        }        
        .icon-tree {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/fam/user_female.gif) !important;
        }        
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/fam/add.gif) !important;
        }
        .edit {
            background-image:url(${ctx}/images/ext2.0/default/grid/columns.gif) !important;
        }
        .remove {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/fam/delete.gif) !important;
        }
        .save {
            background-image:url(${ctx}/images/ext2.0/default/shared/icons/save.gif) !important;
        }
    </style>	
  </head>

  <body>
	  <!-- <div id="north-div">
	    <p>user system</p>
	  </div>
	  <div id="south-div">
	    <p>user system</p>
	  </div> -->
	  <div id="path" action="/maria" />
	  
	  <!-- <div id="west-div"/> -->
	  
	  <div id="user-grid" />
	  <div id="role-grid" />
	  
	  <div id="usertree" />
	  <div id="roletree" />
  </body>
</html>
