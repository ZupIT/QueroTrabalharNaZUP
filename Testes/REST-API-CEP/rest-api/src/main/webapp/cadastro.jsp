<%-- 
    Document   : ServCadastraCep
    Created on : 27/12/2014, 13:46:51
    Author     : DiemersonFernando
--%>

<%@page import="com.mycompany.cadastrodecep.Manipulacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de CEP</title>
    </head>
    <body>
        <%
            String cep = request.getParameter("cep1") +'-'+ request.getParameter("cep2");
            String endereco = request.getParameter("end");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            
            Manipulacao cadastro = new Manipulacao();            
            StringBuilder dados = new StringBuilder();
            
            dados.append("{");
            dados.append("\"cep\": \"").append(cep).append("\", ").append("\n");
            dados.append("\"endereco\": \"").append(endereco).append("\", ");
            dados.append("\"bairro\": \"").append(bairro).append("\", ");
            dados.append("\"cidade\": \"").append(cidade).append("\", ");
            dados.append("\"estado\": \"").append(uf).append("\"");
            dados.append("}");
            
            cadastro.gravaCep(dados.toString());
            
            out.write(cadastro.resp);
        %>
    </body>
    
    
</html>
