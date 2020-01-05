<%@include file="sessionValidation.jsp" %>

<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/header.css" rel="stylesheet">

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="home.jsp">Logo</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="home.jsp">INÍCIO
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> CADASTROS </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="SellerController">Vendedores</a> 
					<a class="dropdown-item" href="DepartmentController">Departamentos</a>
					<a class="dropdown-item" href="UsersController">Usuários</a>

				</div></li>
			<li class="nav-item"><a class="nav-link" href="logout.jsp">Sair</a>
			</li>
		</ul>
	</div>
</nav>