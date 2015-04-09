<%-- 
    Document   : newjsp
    Created on : 09.04.2015, 16:53:36
    Author     : Robert
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="mybean" scope="session" class="newpackage.publicOCCHandler" />
        <h1>Hello World! <jsp:getProperty name="mybean" property="anzProvider" /></h1>
    </body>
</html>
