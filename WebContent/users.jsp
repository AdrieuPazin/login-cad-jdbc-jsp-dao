<%@page import="entities.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuários</title>
</head>
<body>
	<%@include file="./util/header.jsp"%>


	<div class="container text-center">

		<h1 class="text-center">Pagina Usuários</h1>
		<table class="table table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">E-mail</th>
					<th scope="col">  </th>
					<th scope="col"> 
							<a href="UsersController?acao=add" class="btn btn-primary " role="button" aria-pressed="true">Inserir Usuário</a>
					</th>
				</tr>
			</thead>
			<tbody>
	<%		
		int i = 1;
		List<Users> lista = (List<Users>) request.getAttribute("lista");
			for(Users u: lista){

    %>

				<tr>
					<td>
						<%=i%>
					</td>
					<td>
						<%=u.getEmail()%>
					</td>
					<td>
						<a href="UsersController?acao=edit&id=<%=u.getId() %>" class="btn btn-warning " role="button" aria-pressed="true">Editar</a>
					</td>
					<td>
						<a href="javascript:delUser(<%= u.getId() %>)" class="btn btn-danger " role="button" aria-pressed="true">Excluir</a>
					</td>
				</tr>


	<%			i++;
		
			}

	%>
			</tbody>
		</table>




	</div>

	<%@include file="./util/footer.jsp"%>
	
	<script type="text/javascript" src="./js/validacao.js"></script>	
	
</body>
</html>