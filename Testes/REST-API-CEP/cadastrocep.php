<?php

    require_once "cadastro.php";
	require_once "db.php";
	require_once "mensagem.php";

	if($_SERVER['REQUEST_METHOD'] !== "POST"){
		http_response_code(405);
		$mensagemErro = new Mensagem ("ERRO", "Metodo não permitido");
		echo json_encode($mensagemErro);
		exit();
	}

	$json = file_get_contents("php://input");
	$objeto = json_decode($json);
	$cadastro = new Cadastro($objeto -> cep, $objeto -> endereco, $objeto -> bairro, $objeto -> cidade, $objeto -> estado);
	$banco = new db('localhost','root','','cadastrocep');
	$banco -> connect();
	$retorno = $banco -> save($cadastro);
	$banco -> close();
	echo $retorno;

?>