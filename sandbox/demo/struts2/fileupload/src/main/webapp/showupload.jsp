<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <title>Struts2 File Upload</title>
    </head>
    <body>
        <hr>
        单文件上传：
        <hr>
        <form action="fileupload.do" method="POST" enctype="multipart/form-data">
            文件标题：<input type="text" name="title" size="50"/><br/>
            选择文件：<input type="file" name="upload" size="50"/><br/>
            <input type="submit" value=" 上传 "/>
        </form>
        <hr>
    </body>
</html>

