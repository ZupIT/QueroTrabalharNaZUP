<?php 
// Incluimos e chamamos a classe do banco de dados
include("class.mysql.php");
$db = new classMySQL();

//Verificamos se o CEP esta setado e se esta vazio
if(isset($_GET['cepEscolhido']) AND $_GET['cepEscolhido'] != ""){
	// Retiramos o traço do CEP
	$cep = str_replace("-","",$_GET['cepEscolhido']);
	// Verificamos se o CEP e numero e se o tamanho dele e igual a 8
	if(is_numeric($cep) AND strlen($cep) == 8){
		// Efetuamos uma busca no banco de dados pelo CEP
		// Se houver resultados, trazemos e mostramos para a API
		$db->consulta_bd("SELECT * FROM tb_ceps WHERE cep = $cep AND status = 1");
		if($db->consulta_registros() == 1){
			$resultado = mysqli_fetch_object($db->dados);
			// Estrutura do JSON
			$mensagem["status"] 	= "SUCESSO";
			$mensagem["cep"] 		= $resultado->cep;
			$mensagem["endereco"] 	= utf8_encode($resultado->endereco);
			$mensagem["bairro"] 	= utf8_encode($resultado->bairro);		
			$mensagem['cidade']     = utf8_encode($resultado->cidade);
			$mensagem['estado']     = utf8_encode($resultado->estado);
			echo json_encode($mensagem);
		}else{
			// Retornamos o status e a mensagem em JSON
			$mensagem["status"]   = "ERRO";
			$mensagem["mensagem"] = "O CEP informado não foi encontrado";
			echo json_encode($mensagem);
		}
	}else{
		// Retornamos o status e a mensagem em JSON
		$mensagem["status"]   = "ERRO";
		$mensagem["mensagem"] = "O CEP informado é inválido";
		echo json_encode($mensagem);		
	}
}else{
	// Retornamos o status e a mensagem em JSON
	$mensagem["status"]   = "ERRO";
	$mensagem["mensagem"] = "O CEP informado é inválido";
	echo json_encode($mensagem);	
}
?>