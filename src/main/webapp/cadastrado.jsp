<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projeto Integrador</title>
    <link rel="icon" type="image/png" href="img/mascotecabeca.png">
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
 <!-- CONFETES -->
    <canvas id="confete"></canvas>

    <div class="container">

        <!-- TITULO -->
        <h1>Seu cadastro foi concluído com sucesso!</h1>

        <!-- ROBO -->
        <img id="robo" src="img/mascoteDeitado.png" alt="RobÃ´ Feliz">

        <h3>Agora falta só um passo, pegue o seu:
        </h3>

        <!-- BOTAO -->
        
        <form action="imprimir" method="post">
        	
        	<input type="hidden" name="id" value="${id}">
        	<input type="hidden" name="cursos" value="${cursos}">
        	<input type="hidden" name="nome" value="${nome}">
        	
        	
        	<button type="submit" class="btn-sucesso">

           		Número da sorte

        	</button>
        </form>


    </div>

    <script src="./javascript/script.js"></script>

</body>

</html>