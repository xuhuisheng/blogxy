<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>CRUD</title>
  </head>
  <body>

<form action="<#if (model.id)??>test!update.do<#else>test!save.do</#if>" method="post">
    <#if (model.id)??>
    <input type="hidden" name="entityId" value="${(model.id?string("#"))!}"/>
    </#if>
    名字：<input type="text" name="name" value="${(model.name)!}"/><br/>
    描述：<input type="text" name="descn" value="${(model.descn)!}"/><br/>
    <input type="submit"/>
</form>

  </body>
</html>

