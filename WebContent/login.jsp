<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/estiloLogin.css" rel="stylesheet">
</head>
<body class="text-center">


	
    <form action="Login" method="post" class="frmLogin">
      <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Bem Vindo</h1>
      
      <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" name="frmLoginEmail" required autofocus>
      
      <input type="password" id="inputPassword" class="form-control" placeholder="Senha" name="frmLoginPass"required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
    </form>


<script src="./js/bootstrap.min.js"></script>

<%@include file="./util/footer.jsp" %>
</body>
</html>