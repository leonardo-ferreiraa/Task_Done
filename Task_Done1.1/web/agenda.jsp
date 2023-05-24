<%-- 
    Document   : sucessoautenticacao
    Created on : 10/05/2022, 17:34:38
    Author     : proft
--%>

<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/tarefa.css" type="text/css" rel="stylesheet">
        <title>Tarefa</title>
    </head>
    <body>
        <% Usuario usu = (Usuario) request.getAttribute("usu");%>
        <div class="container">
            <div class="screen">
                <div class="tarefas">
                    <label class="label_title">Titulo</label>
                    <p>O vídeo fornece uma maneira poderosa de ajudá-lo a provar seu argumento. Ao clicar em Vídeo Online, você pode colar o código de inserção do vídeo que deseja adicionar.
                        Você também pode digitar uma palavra-chave para pesquisar online o vídeo mais adequado ao seu documento. Para dar ao documento uma aparência profissional, o Word fornece designs de cabeçalho, rodapé, folha de rosto e caixa de texto que se complementam entre si.
                        Por exemplo, você pode adicionar uma folha de rosto, um cabeçalho e uma barra lateral correspondentes. Clique em Inserir e escolha os elementos desejados nas diferentes galerias.
                    </p>
                    <input type="submit" value="+">
                    <input type="submit" value="-">
                </div>
            </div>

        </div>

    </body>
</html>
