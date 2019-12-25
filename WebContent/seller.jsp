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

	<h1>Pagina Seller</h1>

	<div class="container">

		<table class="table table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
					<th scope="col">Salário Base</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>Thornton</td>
					<td>@fat</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Larry</td>
					<td>the Bird</td>
					<td>@twitter</td>
				</tr>
			</tbody>
		</table>

		


	</div>

	<%@include file="./util/footer.jsp"%>

</body>
</html>