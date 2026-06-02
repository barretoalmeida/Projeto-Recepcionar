<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Cadastro do Aluno</title>

<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
<link rel="icon" type="image/png" href="img/mascotecabeca.png">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Manrope',sans-serif;
}

body{
    background:#f4f4f4;
}

.container{
    max-width:800px;
    margin:auto;
    text-align:center;
    padding:20px;
    padding-bottom:380px;
}

#robo{
    width:150px;
    margin-top:20px;
}

h1{
    margin-top:20px;
    margin-bottom:10px;
}

p{
    margin-bottom:20px;
}

form{
    display:flex;
    flex-direction:column;
    gap:15px;
}

input,
select{
    width:100%;
    padding:18px;
    font-size:22px;
    border:2px solid #ccc;
    border-radius:10px;
}

.btn-entrar{
    padding:18px;
    font-size:24px;
    border:none;
    border-radius:10px;
    background:#1e88e5;
    color:white;
    cursor:pointer;
}

.btn-entrar:hover{
    opacity:.9;
}

/* LOADING */

#loading{
    display:none;
    position:fixed;
    inset:0;
    background:white;
    z-index:99999;
    justify-content:center;
    align-items:center;
    flex-direction:column;
}

#loading img{
    width:120px;
}

.barra-container{
    width:300px;
    height:20px;
    border-radius:10px;
    background:#ddd;
    overflow:hidden;
    margin-top:20px;
}

#barra{
    width:0%;
    height:100%;
    background:#1e88e5;
}

/* TECLADO */

#teclado{
    position:fixed;
    bottom:0;
    left:0;
    width:100%;
    background:#222;
    padding:10px;
    z-index:9999;
    box-shadow:0 -5px 15px rgba(0,0,0,.3);
}

#teclado .linha{
    display:flex;
    justify-content:center;
    margin-bottom:5px;
}

.tecla{
    width:75px;
    height:70px;
    margin:3px;
    border:none;
    border-radius:10px;
    background:white;
    font-size:24px;
    font-weight:bold;
    cursor:pointer;
}

.tecla:active{
    transform:scale(.95);
}

.especial{
    width:200px;
}

</style>

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

    <img id="robo" src="img/mascotinhomenor.png" alt="Robô">

    <h1><strong>Cadastro do Aluno</strong></h1>

    <p>
        <strong>
            Preencha suas informações para
            <br>
            continuar o acesso:
        </strong>
    </p>

    <form action="update" method="get">

        <input type="hidden" name="id" value="${id}">

        <input type="text"
               name="nome"
               placeholder="Nome Completo"
               required>

        <input type="date"
               name="data"
               required>

        <select name="cursos" required>

            <option value="" disabled selected>
                Selecione seu curso
            </option>

            <option>Aprendizagem</option>
            <option>Estetica</option>
            <option>Fotografia</option>
            <option>Qualificacao profissional</option>
            <option>Tecnico em Informatica</option>

        </select>

        <button type="submit"
                class="btn-entrar"
                onclick="mostrarLoading()">
            Cadastrar
        </button>

    </form>

</div>

<!-- TECLADO -->

<div id="teclado">

    <div class="linha">
        <button class="tecla">1</button>
        <button class="tecla">2</button>
        <button class="tecla">3</button>
        <button class="tecla">4</button>
        <button class="tecla">5</button>
        <button class="tecla">6</button>
        <button class="tecla">7</button>
        <button class="tecla">8</button>
        <button class="tecla">9</button>
        <button class="tecla">0</button>
    </div>

    <div class="linha">
        <button class="tecla">Q</button>
        <button class="tecla">W</button>
        <button class="tecla">E</button>
        <button class="tecla">R</button>
        <button class="tecla">T</button>
        <button class="tecla">Y</button>
        <button class="tecla">U</button>
        <button class="tecla">I</button>
        <button class="tecla">O</button>
        <button class="tecla">P</button>
    </div>

    <div class="linha">
        <button class="tecla">A</button>
        <button class="tecla">S</button>
        <button class="tecla">D</button>
        <button class="tecla">F</button>
        <button class="tecla">G</button>
        <button class="tecla">H</button>
        <button class="tecla">J</button>
        <button class="tecla">K</button>
        <button class="tecla">L</button>
    </div>

    <div class="linha">
        <button class="tecla">Z</button>
        <button class="tecla">X</button>
        <button class="tecla">C</button>
        <button class="tecla">V</button>
        <button class="tecla">B</button>
        <button class="tecla">N</button>
        <button class="tecla">M</button>
    </div>

    <div class="linha">
        <button class="tecla especial" id="espaco">
            ESPAÇO
        </button>

        <button class="tecla especial" id="backspace">
            ⌫ APAGAR
        </button>
    </div>

</div>

<script>

let campoAtivo = null;

/* Detecta o campo ativo */
document.querySelectorAll("input").forEach(input => {

    input.addEventListener("focus", function(){
        campoAtivo = this;
    });

});

/* Teclado */

document.querySelectorAll(".tecla").forEach(tecla => {

    tecla.addEventListener("click", function(){

        if(!campoAtivo) return;

        if(this.id === "espaco"){
            campoAtivo.value += " ";
        }
        else if(this.id === "backspace"){
            campoAtivo.value =
                campoAtivo.value.slice(0,-1);
        }
        else{
            campoAtivo.value += this.innerText;
        }

        campoAtivo.focus();

    });

});

/* Inicia já focando no campo nome */
window.onload = function(){

    const nome =
        document.querySelector("input[name='nome']");

    nome.focus();
    campoAtivo = nome;

};

/* Loading */

function mostrarLoading(){

    document.getElementById("loading").style.display="flex";

    let barra = document.getElementById("barra");
    let largura = 0;

    let intervalo = setInterval(function(){

        largura += 5;
        barra.style.width = largura + "%";

        if(largura >= 100){
            clearInterval(intervalo);
        }

    },50);

}

</script>

</body>
</html>