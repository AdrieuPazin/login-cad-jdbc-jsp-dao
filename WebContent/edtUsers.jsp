<%@page import="entities.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usu�rio</title>
</head>
<body>

<%@include file="./util/header.jsp" %>

<div class="container">

		<%
			String msg = (String) request.getAttribute("invalidEmail");
			if(msg != null){
				out.println(msg);
			}
		
			if(request.getAttribute("user") != null){
	
				Users user = (Users) request.getAttribute("user");	
				
		%>		
		<h1><%="Editar Usu�rio"%></h1>
				<form name="frmEdtUser" action="UsersController?acao=edit_proc" method="POST" onsubmit="return validarDadosUsers()? true : false">
				
					<div class="form-group">
				    <input hidden type="text" class="form-control" id="idfrmUserId" name = "frmUserId" value="<%= user.getId()%>">
				  </div>
				  <div class="form-group">
				    <label >E-mail</label>
				    <input readonly type="email" class="form-control" id="idfrmUserEmail" name = "frmUserEmail" placeholder="Entre com seu e-mail" value="<%= user.getEmail()%>">
				  </div>
				  <div class="form-group">
				    <label >Senha</label>
				    <input type="password" class="form-control" id="idfrmUserPass" name="frmUserPass" placeholder="Entre com sua senha" value="<%= user.getPass()%>">
				  </div>
				  <div class="form-group">
				    <label >Repita a senha</label>
				    <input type="password" class="form-control" id="idfrmUserPassRepeticao" name="frmUserPassRepeticao" placeholder="Entre com sua senha">
				  </div>
				  <button type="submit" class="btn btn-primary">Salvar</button>
				</form>
		<%		
			}
		%>
	
	
		<%
			if(request.getAttribute("user") == null){
	
					
		%>
		
		<h1><%="Cadastrar Usu�rio"%></h1>
				<form name="frmEdtUser"action="UsersController?acao=adicionar_proc" method="POST" onsubmit="return validarDadosUsers()? true : false">				
				  <div class="form-group">
				    <label >E-mail</label>
				    <input type="email" class="form-control" id="idfrmUserEmail" name = "frmUserEmail" placeholder="Entre com seu e-mail" >
				  </div>
				  <div class="form-group">
				    <label >Senha</label>
				    <input type="password" class="form-control" id="idfrmUserPass" name="frmUserPass" placeholder="Entre com sua senha">
				  </div>
				  <div class="form-group">
				    <label >Repita a senha</label>
				    <input type="password" class="form-control" id="idfrmUserPassRepeticao" name="frmUserPassRepeticao" placeholder="Entre com sua senha">
				  </div>
				  <button type="submit" class="btn btn-primary">Salvar</button>
				</form>
		<%		
			}
		%>


</div>
<%@include file="./util/footer.jsp" %>
<script type="text/javascript" src="./js/validacao.js">


// function msg(){
	
// 	alert("ok");
// 	redireciona('UsersController');
	
// }

// function redireciona(param){
// 	  location.href=param+"?acao=adicionar_proc";
// 	}

</script>

</body>
</html>