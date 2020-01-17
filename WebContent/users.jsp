<%@page import="entities.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usu�rios</title>
</head>
<body>
	<%@include file="./util/header.jsp"%>


	<div class="container text-center">
	<%
		String msg = (String) request.getAttribute("mensagem");
		if(msg!=null){
			out.println(msg);
		}
	%>
		<h1 class="text-center">Pagina Usu�rios</h1>
		<table class="table table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">E-mail</th>
					<th scope="col">  </th>
					<th scope="col"> 
							<a href="UsersController?acao=add" class="btn btn-primary " role="button" aria-pressed="true">Inserir Usu�rio</a>
					</th>
				</tr>
			</thead>
			<tbody>
	<%		
		int i = 1;
		List<Users> lista = (List<Users>) request.getAttribute("listaPaginada");
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
						<%
							if(session.getAttribute("email").equals(u.getEmail())){
						%>
								<a href="#" class="btn btn-secondary" role="button" aria-pressed="true">Excluir</a>
						<% 
							} else{
						%>
								<a href="javascript:delUser(<%= u.getId() %>)" class="btn btn-danger " role="button" aria-pressed="true">Excluir</a>
						<%
							}
						%>
					
					</td>
				</tr>


	<%			i++;
		
			}

	%>
			</tbody>
		</table>

		<nav>
			  <ul class="pagination justify-content-center">
			    <li class="page-item">
			      <a class="page-link" href="UsersController?numPag=1" tabindex="-1">In�cio</a>
			    </li>
			    <%
			    	//Pego o total de registros e divido por 10 que � o limit do SQL para obter o total de paginas.
			    	//Se o resto da divis�o pelo registros n�o for zero eu acrescento uma pagina para pegar o restante dos registros
			    	int totalRegistros = (Integer) request.getAttribute("qtdeRegistros");
			    	int totalPaginas = totalRegistros / 10;
			    	if(totalRegistros % 10 != 0){
			    		totalPaginas++;
			    	}
			    	for(int j = 1; j <= totalPaginas; j++){
			    	
			    %>
				   		<li class="page-item"><a class="page-link" href="UsersController?numPag=<%=j %>"><%=j %></a></li>
				    
				<% } %> 
			  </ul>
		</nav>


	</div>

	<%@include file="./util/footer.jsp"%>
	
	<script type="text/javascript" src="./js/validacao.js"></script>	
	
</body>
</html>