<?php

	require_once "db.php";
    
	$cep = $_GET['cep'];

	$banco = new db('localhost','root','','cadastrocep');
	$banco -> connect();
	$resultado = $banco -> search($cep);
	$banco -> close();
	
	echo $resultado;

?>