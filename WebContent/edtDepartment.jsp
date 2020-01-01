<%@page import="entities.Department"%>
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
			<div class="container">
			<%
			
			if(request.getAttribute("dep") != null){
				Department d = (Department) request.getAttribute("dep");
				
			%>
			
			<h1 class="text-center"><%="Editar Departamento"%></h1>
				<form name="frmEdtDepartment" action="DepartmentController?acao=edit_proc"
					method="POST" onsubmit="return validarDadosDepartment()? true : false">
		
					<div class="form-group">
						<input hidden type="number" class="form-control" id="idfrmDepartmentId"	name="frmDepartmentId" value="<%=d.getId()%>">
					</div>
		
					<div class="form-group">
						<label>Nome do Departamento</label> 
						<input type="text" class="form-control"	id="idfrmDepartmentName" name="frmDepartmentName" placeholder="Entre com o nome do Departamento" value="<%=d.getName()%>">
					</div>
					<button type="submit" class="btn btn-primary">Salvar</button>
				</form>
			
			<% 
				}if (request.getAttribute("dep") == null){
			
			%>
					
			<h1 class="text-center"><%="Adicionar Departamento"%></h1>
				<form name="frmEdtDepartment" action="DepartmentController?acao=add_proc"
					method="POST" onsubmit="return validarDadosDepartment()? true : false">
								
					<div class="form-group">
						<label>Nome do Departamento</label> 
						<input type="text" class="form-control"	id="idfrmDepartmentName" name="frmDepartmentName" placeholder="Entre com o nome do Departamento">
					</div>
					<button type="submit" class="btn btn-primary">Salvar</button>
				</form>
			<% 
				}
			
			%>
	
	</div>
	<%@include file="./util/footer.jsp" %>
	<script type="text/javascript" src="./js/validacao.js"></script>

</body>
</html>