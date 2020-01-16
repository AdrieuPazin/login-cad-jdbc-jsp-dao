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
		List<Seller> lista = (List<Seller>) request.getAttribute("listaPaginada");
	
		for(Seller s: lista){
	
	%>
			<tbody>
				<tr>
					<th scope="row"><%=i%></th>
					<td><%=s.getName() %></td>
					<td><%=s.getEmail() %></td>
					<td><%=s.getBirthDate() %></td>
					<td>R$ <%=s.getBaseSalary() %></td>
					<td><%=s.getDepartment().getName() %></td>
					<td><a href="SellerController?acao=edit&id=<%= s.getId() %>" class="btn btn-warning " role="button" aria-pressed="true">Editar</a></td>
					<td><a href="javascript:delSeller(<%= s.getId() %>)" class="btn btn-danger " role="button" aria-pressed="true">Excluir</a></td>
				</tr>
	<% i++;
		}
	%>
			</tbody>
		</table>

		<nav">
			  <ul class="pagination justify-content-center">
<!-- 			    <li class="page-item disabled"> -->
<!-- 			      <a class="page-link" href="#" tabindex="-1">Anterior</a> -->
<!-- 			    </li> -->
			    <%
			    	//Pego o total de registros e divido por 10 que é o limit do SQL para obter o total de paginas.
			    	//Se o resto da divisão pelo registros não for zero eu acrescento uma pagina para pegar o restante dos registros
			    	int totalRegistros = (Integer) request.getAttribute("qtdeRegistros");
			    	int totalPaginas = totalRegistros / 10;
			    	if(totalRegistros % 10 != 0){
			    		totalPaginas++;
			    	}
			    	for(int j = 1; j <= totalPaginas; j++){
			    	
			    %>
				    <li class="page-item"><a class="page-link" href="SellerController?numPag=<%=j %>"><%=j %></a></li>
				    
				<% } %>    
<!-- 			      <a class="page-link" href="#">Próximo</a> -->
			    </li>
			  </ul>
		</nav>


	</div>

	<%@include file="./util/footer.jsp"%>

<script type="text/javascript" src="./js/validacao.js"></script>

</body>
</html>