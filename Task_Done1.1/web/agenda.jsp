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
        %><div class="screen">
            <div class="cartao"> 
                <label class="label_title"><%out.println(a.getTR_TITULO());%></label>
                <p><%out.println(a.getTR_TAREFA());%></p>
                <form action="Controller_Agenda" method="post">
                    <div class="botoes">
                        <input type="hidden" name="txtID"value=<%out.println(a.getTR_ID());%> name="txtID">
                        <input type="submit" class="edit"  value="EDITAR">
                        <input type="submit" class="delete" value="DELETAR" name="operacao">
                    </div>
                </form>
            </div>
        </div>
                        <p></p>
                    <div class="screen">
                <div class="cartao">
                    <form action="Controller_Agenda" method="post">
                        <input class="titulo" type="text" name="txttitulo">
                        <br>
                        <input type="hidden" name="txtID"value=<%out.println(a.getTR_ID());%> name="txtID">
                        <textarea class="tarefa" type="text" name="txttarefa"></textarea>
                        <br>
                        <input class="criar_tarefa" type="submit" value="CRIAR" name="operacao">  
                        <input class="criar_tarefa" type="submit" value="EDITAR" name="operacao">  
                    </form>
                </div>
            </div>
            <%
                }
            }else
                out.println("Consulta sem retorno");
            %><div class="screen">
                <div class="cartao">
                    <form action="Controller_Agenda" method="post">
                        <input class="titulo" type="text" name="txttitulo">
                        <br>
                        <textarea class="tarefa" type="text" name="txttarefa"></textarea>
                        <br>
                        <input class="criar_tarefa" type="submit" value="CRIAR" name="operacao">  
                        <input class="criar_tarefa" type="submit" value="EDITAR" name="operacao">  
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
