<%@page import="entities.Department"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Departamentos</title>
</head>
<body>

<%@include file="./util/header.jsp" %>
	<div class="container text-center">
		<h1>Página de Departmentos</h1>
	
			<table class="table table-striped table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nome</th>
						<th scope="col">  </th>
						<th scope="col"> 
								<a href="DepartmentController?acao=add" class="btn btn-primary " role="button" aria-pressed="true">Inserir Departamento</a>
						</th>
					</tr>
				</thead>
				<tbody>
		<%
			int i = 1;
			List<Department> list = (List<Department>) request.getAttribute("listDep");
		 	for(Department d: list){
		%>		
				
				<tr>
					<td>					
						<%=i %>
					</td>
					<td>
						<%=d.getName() %>
					</td>
					<td>
						<a href="DepartmentController?acao=edit&id=<%=d.getId()%>" class="btn btn-warning " role="button" aria-pressed="true">Editar</a>
					</td>
					<td>
						<a href="javascript:delDepartment(<%=d.getId()%>)" class="btn btn-danger " role="button" aria-pressed="true">Excluir</a>
					</td>
				</tr>
			<%
					i++;
		 		}
			%>
				</tbody>
		</table>




	</div>
			



<%@include file="./util/footer.jsp" %>
<script type="text/javascript" src="./js/validacao.js"></script>

</body>
</html>