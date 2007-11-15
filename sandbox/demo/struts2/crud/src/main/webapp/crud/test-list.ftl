<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>CRUD</title>
  </head>
  <body>

<a href="test!insert.do">添加</a>
<table border="1">
    <tr style="background-color:gray;color:white;">
        <td>序号</td>
        <td>名称</td>
        <td>描绘</td>
        <td>修改</td>
        <td>删除</td>
    </tr>
<#list results! as item>
    <tr>
        <td>${item.id!}</td>
        <td>${item.name!}</td>
        <td>${item.descn!}</td>
        <td><a href="test!edit.do?entityId=${item.id?string('#')!}">修改</a></td>
        <td><a href="test!remove.do?entityId=${item.id?string('#')!}">删除</a></td>
    </tr>
</#list>
</table>

  </body>
</html>

