<%-- 
    Document   : busca
    Created on : 27/12/2014, 13:47:10
    Author     : DiemersonFernando
--%>

<%@page import="com.mycompany.cadastrodecep.Manipulacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busca de Endereco por CEP</title>
    </head>
    <body>
        <%
            String cepserv = request.getParameter("cep1") + request.getParameter("cep2");
            String resposta;
            
            Manipulacao busca = new Manipulacao();
            
            busca.retornaCep(cepserv);
            
            out.write(busca.resp.toString());
        %>              
    </body>
</html>
