<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Cadastro do Aluno</title>

	<link rel="stylesheet" href="css/style.css">

	<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
	<link rel="icon" type="image/png" href="img/mascotecabeca.png">
</head>

<body>

	<div class="container tela-cadastro">
	
		<div id="loading">
    		<img src="img/mascotinhomenor.png" alt="Robô">
    		<p>Gerando...</p>
    		<div class="barra-container">
        		<div id="barra"></div>
    		</div>
		</div>

		<img id="robo" src="img/mascotinhomenor.png" alt="Robo´">

		<h1> <strong>Cadastro do Aluno</strong></h1>

		<p>
			<strong>Preencha suas informações para
			<br> continuar o acesso :</strong>
		</p>
		<form action="update" method="get">
		
			<input type="hidden" name="id" value="${id}">
			
			<input type="text" name="nome" placeholder="Nome Completo" required>

			<input type="date" name="data" placeholder="Data de Nascimento" required>


			<select name="cursos" required>

				<option value="" disabled selected>
					Selecione seu curso
				</option>

				<option >
					Aprendizagem
				</option>

				<option >
					Estetica
				</option>

				<option >
					Fotografia
				</option>

				<option >
					Qualificacao profissional
				</option>

				<option >
					Tecnico em Informatica
				</option>
			</select>

			<button type="submit" class="btn-entrar"  onclick="mostrarLoading()">
            	Cadastrar
        	</button>

		</form>

	</div>

	<script src="javascript/script-js"></script>

</body>

</html>