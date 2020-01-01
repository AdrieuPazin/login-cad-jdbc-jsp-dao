<%@page import="entities.Seller"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendedores</title>
</head>
<body>

	<%@include file="./util/header.jsp"%>


	<div class="container text-center">
		<h1>Página Vendedores</h1>

		<table class="table table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">id</th>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
					<th scope="col">Data de Nascimento</th>
					<th scope="col">Salário Base</th>
					<th scope="col">Departamento</th>
					<th scope="col"></th>
					<th scope="col"><a href="SellerController?acao=add" class="btn btn-primary " role="button" aria-pressed="true">Adicionar Vendedor</a></th>
				</tr>
			</thead>
			
			
	<%
		int i = 1;
		List<Seller> lista = (List<Seller>) request.getAttribute("lista");
	
		for(Seller s: lista){
	
	%>
			<tbody>
				<tr>
					<th scope="row"><%=i%></th>
					<td><%=s.getName() %></td>
					<td><%=s.getEmail() %></td>
					<td><%=s.getBirthDate() %></td>
					<td><%=s.getBaseSalary() %></td>
					<td><%=s.getDepartment().getName() %></td>
					<td><a href="SellerController?acao=edit&id=<%= s.getId() %>" class="btn btn-warning " role="button" aria-pressed="true">Editar</a></td>
					<td><a href="javascript:delSeller(<%= s.getId() %>)" class="btn btn-danger " role="button" aria-pressed="true">Excluir</a></td>
				</tr>
	<% i++;
		}
	%>
			</tbody>
		</table>

		


	</div>

	<%@include file="./util/footer.jsp"%>

<script type="text/javascript" src="./js/validacao.js"></script>

</body>
</html>