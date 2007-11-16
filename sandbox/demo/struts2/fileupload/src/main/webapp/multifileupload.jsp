<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <title>多文件上传</title>
    </head>
    <body>
        <font color="red"><s:fielderror/></font>
        <form action="multifileupload.do" method="POST" enctype="multipart/form-data">
            文件标题：<input type="text" name="title" size="50" value="${param.title }"/><br/>
            <!-- 设置二个文件域,名字相同 -->
            选择第一个文件：<input type="file" name="upload" size="50"/><br/>
            选择第二个文件：<input type="file" name="upload" size="50"/><br/>
            <input type="submit" value=" 上传 "/>
        </form>
    </body>
</html>

