<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RFS - Romel File System (because life is too short)</title>
    </head>
    <body>
        <h1>RFS, prova de conceito</h1>

        <form method="post" action="LerArquivoServlet" >
            <input type="text" name="nome" />
            <input type="file" name="arquivo" />
            <input type="submit" value="Go baby, go!" />
        </form>
    </body>
</html>
