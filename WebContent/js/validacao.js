function validarDadosUsers() {
	var email = document.getElementById("idfrmUserEmail").value;
	var pass = document.getElementById("idfrmUserPass").value;
	var pass2 = document.getElementById("idfrmUserPassRepeticao").value;
	if (email == "") {
		alert("informe o e-mail!");
		frmEdtUser.frmUserEmail.focus();
		return false
	} else if (pass == "") {
		alert("Informe a senha!");
		frmEdtUser.frmUserEmail.focus();
		return false;
	}  else if(pass.trim() != pass2.trim()){
		alert("Senhas não conferem!");
		return false;
	}
	//document.nome_do_form.submit();
	return true;
	
}

function validarDadosSellers() {
	var nome = document.getElementById("idfrmSellerName").value;
	var email = document.getElementById("idfrmSellerEmail").value;
	if (nome == "") {
		alert("informe seu nome!");
		frmEdtSeller.frmSellerName.focus();
		return false
	} else if (email == "") {
		alert("Informe o e-mail!");
		frmEdtSeller.frmSellerEmail.focus();
		return false;
	}  
	return true;
	
}

function delUser(codigo) {  
    if (confirm('Deseja realmente excluir este registro?')) {  
        location.href = 'UsersController?acao=exc&id=' + codigo;
    }
}

function delSeller(codigo) {  
    if (confirm('Deseja realmente excluir este registro?')) {  
        location.href = 'SellerController?acao=exc&id=' + codigo;
    }
}

