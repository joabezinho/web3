<%@page import="database.DenunciaDAO"%>
<%@page import="model.Denuncia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.UsuarioDAO"%>
<%@include file="session/validar.jsp" %>

<%  
    Usuario u = (Usuario) request.getSession().getAttribute("userLogged");
    DenunciaDAO dao = new DenunciaDAO();
    ArrayList<Denuncia> list = dao.getAllDenuncias(u);
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
            <h1 class="text-center">Lista de Denuncias</h1>
            <hr>
            <h2>Bem vindo, <%= u.getNome()%>!</h2>

            <div id="btn-menu">
                <a href="add-denuncia.html" class="btn btn-success" >+ Nova Denuncia</a>
                <a href="session/finalizar.jsp" class="btn btn-danger" > Sair </a>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Cod</th>
                        <th>Denúncia</th>
                        <th>Descrição</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Denuncia d : list) {%>
                    <tr>
                        <td> <%= d.getId()%> </td>
                        <td> <%= d.getDenuncia()%> </td>
                        <td> <%= d.getDescricao()%> </td>
                        <td> <%= d.getStatusDenuncia()%> </td>
                        <td>
                            <a onclick="excluir(<%= d.getId()%>)" title="Excluir denúncia <%= d.getId()%>"> 🗑️ </a>
                        </td>
                        <td>
                            <a href="denuncia-controller?pagina=editar&id=<%= d.getId()%>" title="Atualizar denúncia <%= d.getId()%>"> ✏️️ </a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <script>
            function excluir(id) {
                if (confirm("Deseja excluir a Denuncia número " + id + "?")) {
                    window.location.href = "denuncia-controller?pagina=excluir&id=" + id;
                } else {
                    alert("Exclusão cancelada!");
                }
            }
        </script>

    </body>
</html>