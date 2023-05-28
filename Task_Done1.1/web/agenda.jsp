<%-- 
    Document   : sucessoautenticacao
    Created on : 10/05/2022, 17:34:38
    Author     : proft
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Agenda"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="css/tarefa.css" type="text/css" rel="stylesheet">
        <title>Tarefa</title>
    </head>
    <body>
        <div class="container">
                    <% List<Agenda> listtarefa = (List<Agenda>) request.getAttribute("listtarefa");%>
            <%if (listtarefa.size() > 0){
                for (Agenda a : listtarefa){
        %><div class="screen"><%
            %><div class="cartao"> <%              
                %><label class="label_title"><%out.println(a.getTR_TITULO());%></label><%
                %><p><%out.println(a.getTR_TAREFA());%></p><%                             
                %><form action="Controller_Agenda" method="post"><%
                    %><div class="botoes"><%
                        %><input class="edit" type="submit" value="EDITAR"><%
                        %><input class="delete" type="submit" name="operacao" value="DELETAR"><%
                    %></div><%
                %></form><%
            %></div><%              
        %></div><%
                }
            }else
                out.println("Consulta sem retorno");
            %><div class="screen">
                <div class="cartao">               
                    <input class="titulo" type="text">
                    <br>
                    <textarea class="tarefa" type="text"></textarea>
                    <br>
                    <input class="criar_tarefa" type="submit" value="CRIAR">
                </div>
            </div>
        </div>
    </body>
</html>
