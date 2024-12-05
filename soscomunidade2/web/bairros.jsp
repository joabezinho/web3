<%@page import="database.BairrosDAO"%>
<%@page import="model.Bairros"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.UsuarioDAO"%>
<%@include file="session/validar.jsp" %>

<%    Usuario u = (Usuario) request.getSession().getAttribute("userLogged");
    BairrosDAO dao = new BairrosDAO();
    ArrayList<Bairros> list = dao.getAllTasks(u);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> WEB III </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="icon" href="img/logotipo.png"/>
    </head>
    <body>
        <div class="container mt-4">
            <h1 class="text-center">Lista de Bairross</h1>
            <hr>
            <h2>Bem vindo, <%= u.getNome()%>!</h2>

            <div id="btn-menu">
                <a href="add-Bairros.html" class="btn btn-success" >+ Nova Bairros </a>
                <a href="session/finalizar.jsp" class="btn btn-danger" > Sair </a>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Cod</th>
                        <th>Título</th>
                        <th>Descrição</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Bairros t : list) {%>
                    <tr>
                        <td> <%= t.getId()%> </td>
                        <td> <%= t.getTitulo()%> </td>
                        <td> <%= t.getDescricao()%> </td>
                        <td> <%= t.getStatusBairros()%> </td>
                        <td>
                            <a onclick="excluir(<%= t.getId()%>, '<%= t.getTitulo()%>')" title="Excluir <%= t.getTitulo()%>"> 🗑️ </a>
                        </td>
                        <td>
                            <a href="Bairros-controller?pagina=editar&id=<%= t.getId()%>" title="Atualizar <%= t.getTitulo()%>"> ✏️️ </a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <script>
            function excluir(id, Bairros) {
                if (confirm("Deseja excluir a Bairros " + Bairros + "?")) {
                    window.location.href = "Bairros-controller?pagina=excluir&id=" + id;
                } else {
                    alert("Exclusão cancelada!");
                }
            }
        </script>

    </body>
</html>