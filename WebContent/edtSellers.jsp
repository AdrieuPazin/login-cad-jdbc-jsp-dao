<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entities.Department"%>
<%@page import="java.util.List"%>
<%@page import="entities.Seller"%>
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
	<div class="container">

		<%
			if (request.getAttribute("seller") != null) {
				Seller s = (Seller) request.getAttribute("seller");
		%>
		<h1 class="text-center"><%="Editar Vendedor"%></h1>
		<form name="frmEdtSeller" action="SellerController?acao=edit_proc"
			method="POST" onsubmit="return validarDadosSellers()? true : false">

			<div class="form-group">
				<input hidden type="number" class="form-control" id="idfrmSellerId"	name="frmSellerId" value="<%=s.getId()%>">
			</div>

			<div class="form-group">
				<label>Nome</label> 
				<input type="text" class="form-control"	id="idfrmSellerName" name="frmSellerName" placeholder="Entre com seu nome" value="<%=s.getName()%>">
			</div>
			<div class="form-group">
				<label>E-mail</label> 
				<input type="email" class="form-control" id="idfrmSellerEmail" name="frmSellerEmail" placeholder="Entre com seu e-mail" value="<%=s.getEmail()%>">
			</div>
			<div class="form-group">
				<label>Data de nascimento</label> 
				<input type="date" class="form-control" id="idfrmSellerBirthDate" name="frmSellerBirthDate" value="<%=s.getBirthDate()%>">
			</div>

			<div class="form-group">
				<label>Salário Base</label> 
				<input type="number" class="form-control" id="idfrmSellerBaseSalary" name="frmSellerBaseSalary" value="<%=s.getBaseSalary()%>">
			</div>

			<div class="form-group">
				<label>Departamento</label> <select name="selectSellerDepartment">

					<option value="<%=s.getDepartment().getId()%>"><%=s.getDepartment().getName()%></option>
					<%
						if (request.getAttribute("listDep") != null) {
								List<Department> list = (List<Department>) request.getAttribute("listDep");
								for (Department d : list) {
					%>
									<option value="<%=d.getId()%>"><%=d.getName()%></option>
					<%
								}
						}
					%>
				</select>

			</div>
			<button type="submit" class="btn btn-primary">Salvar</button>
		</form>
		<%
			}
		%>

		<%
			if (request.getAttribute("seller") == null) {
				Seller s = new Seller();
		%>
		<h1 class="text-center"><%="Cadastrar Vendedor"%></h1>
		<form name="frmEdtSeller" action="SellerController?acao=add_proc"
			method="POST" onsubmit="return validarDadosSellers()? true : false">

			<div class="form-group">
				<label>Nome</label> 
				<input type="text" class="form-control" id="idfrmSellerName" name="frmSellerName" placeholder="Entre com seu nome">
			</div>
			<div class="form-group">
				<label>E-mail</label> 
				<input type="email" class="form-control" id="idfrmSellerEmail" name="frmSellerEmail" placeholder="Entre com seu e-mail">
			</div>
			<div class="form-group">
				<label>Data de nascimento</label> 
				<input type="date"
					class="form-control" id="idfrmSellerBirthDate" name="frmSellerBirthDate" value="">
			</div>

			<div class="form-group">
				<label>Salário Base</label> 
				<input type="number" step="any" class="form-control" id="idfrmSellerBaseSalary" name="frmSellerBaseSalary" value="">
			</div>

			<div class="form-group">
				<label>Departamento</label> 
				<select name="selectSellerDepartment">
					<%
						if (request.getAttribute("listDep") != null) {
								List<Department> list = (List<Department>) request.getAttribute("listDep");
								for (Department d : list) {
					%>

					<option value="<%=d.getId()%>"><%=d.getName()%></option>

					<%
						}
							}
					%>
				</select>

			</div>
			<button type="submit" class="btn btn-primary">Salvar</button>
		</form>
		<%
			}
		%>

	</div>
	<%@include file="./util/footer.jsp"%>
	<script type="text/javascript" src="./js/validacao.js"></script>
</body>
</html>