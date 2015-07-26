<?php

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  Classe de Banco de Dados MySQL com PHP
// 	Desenvolvido por Pablo V.S. Pereira em Outubro / 2011
//
//	abrir_conexao: Cria uma conexão com o banco de dados
//	fechar_conexao: Fecha a conexão com o banco de dados
//	consulta_bd(Sintaxe SQL): Realiza uma consulta a partir da sintaxe SQL passada no parâmetro
//	consulta_campos(Mostrar): Retorna o nome dos campos da consulta
//	consulta_registros(Mostrar): Retorna o número de registros da consulta
//	consulta_tabela: Mostra uma tabela com os dados retornados da consulta
//	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


class classMySQL {
	public $conexao;
	public $dados;
	public $campos_num;
	public $campos_nome = array();
	
	// abre a conexão com o banco de dados, caso falhe, mostra a mensagem
	function abrir_conexao() {
		//ini_set('max_execution_time', 300); //300 seconds = 5 minutes
		$this->conexao = mysqli_connect("localhost", "usuario", "senha", "database") or
						 die("Não foi possível conectar ao banco de dados. <br> Informe o seguinte erro ao administrador do sistema:<b> " . mysqli_connect_error() . "</b>");
						
						
		
		//Acaba com problema de acentuação quando inserir ou exibir dados do DataBase				 
		// mysql_query("SET NAMES 'utf8'");
		// mysql_query('SET character_set_connection=utf8');
		// mysql_query('SET character_set_client=utf8');
		// mysql_query('SET character_set_results=utf8');
		
	}
	
	// fecha a conexão com o banco de dados
	function fechar_conexao() {
		mysqli_close($this->conexao);
	}
	
	// executa a string SQL passada no parametro, e armazena o resultado em "$dados"
	function consulta_bd($sql) {
		if($sql){
			$this->abrir_conexao();
			$this->dados = mysqli_query($this->conexao, $sql) or die(mysqli_error($this->conexao));
			$this->fechar_conexao();
		} else {
			echo ("Sintaxe SQL incorreta");
		}
	}
	
	// retorna o nome dos campos da consulta
	function consulta_campos($mostrar = false) {
		$this->campos_num = 0;
		foreach(mysqli_fetch_fields($this->dados) as $campo) { 
			if($mostrar == false){
				array_push($this->campos_nome, $campo->name);
			} else {
				echo($campo->name. " [" . $campo->type . "]<br>" ); 	
			}
			$this->campos_num++; // guarda a quantidade de campos
    	}
		if($mostrar != false) 	echo("Total de campos: " . $this->campos_num); // mostra o número de campos
		
    }
	
	// retorna o número de registros da consulta
	function consulta_registros($mostrar = false){
		if($mostrar == false)
			return(mysqli_num_rows($this->dados));
		else
			echo(mysqli_num_rows($this->dados));
	}
	
	// mostra a tabela estruturada com o resultado da pesquisa 
	function consulta_tabela(){
		$this->consulta_campos();
	
		echo("<table border='1'>\n");
		echo("<tr>\n");
		foreach(mysqli_fetch_fields($this->dados) as $campo) {
			echo("<td>");
			echo($campo->name);
			echo("</td>\n");
		}
		echo("</tr>\n");
		
		while ($reg = mysqli_fetch_assoc($this->dados)) {
			echo("<tr>\n");
			for($i=0;$i<$this->campos_num;$i++){	
				echo("<td>");
				echo($reg[$this->campos_nome[$i]]);
				echo("</td>\n");
			}
			echo("</tr>\n");
		}
		echo("</table>\n");		
	}
		
	//Inserir Dados
	function inserir_dados($tabela, $dados){
			$arrCampo = array_keys($dados);
			$arrValores = array_values($dados);
			$numCampo = count($arrCampo);
			$numValores = count($arrValores);
			if($numCampo == $numValores){
				$sql = "INSERT INTO ".$tabela." (";
					foreach($arrCampo as $campo){
						$sql .="$campo, ";						
					}
				$sql = substr_replace($sql, ")", -2, 1);
				$sql .= " VALUES (";
					foreach($arrValores as $valores){
						$sql .="'".$valores."', ";						
					}
				$sql = substr_replace($sql, ")", -2, 1);
				
			} else {
				echo "Erro ao checar os valores";
			}
			$this->consulta_bd($sql);		
	}
	
	//Alterar Dados
	function alterar_dados($tabela, $dados, $string){
			$arrCampo = array_keys($dados);
			$arrValores = array_values($dados);
			$numCampo = count($arrCampo);
			$numValores = count($arrValores);
			if($numCampo == $numValores && $numValores > 0){
				$sql = "UPDATE ".$tabela." SET ";
				for($i = 0; $i < $numCampo; $i++){
					$sql .= $arrCampo[$i]." = '".$arrValores[$i]."',";				
				}
				$sql = substr_replace($sql, " ", -1, 1);
				$sql .= "WHERE $string";
		} else {
				echo "Erro ao checar o Update";
		}
		$this->consulta_bd($sql);
	}
		
	//Deletar dados da DataBase
	function deletar_dados($tabela, $string){
		$sql = "DELETE FROM ".$tabela;
		$sql .= " WHERE $string";
		$this->consulta_bd($sql);		
	}
	
	// função anti injection
	function anti_injection($str){
		$str = trim($str);
		$str = strip_tags($str);
		$str = addslashes($str);
		return($str);
	}

}
//========================//
//EXEMPLO INSERÇÃO DE DADO//
//========================//
/*
$db = new classMySQL();
$tabela = 'clientes';
$dados = array(
				  'Nome'  => 'Fulano de Tal',
				  'Email' => 'fulano@email.com.br'
				);
$db->inserir_dados($tabela, $dados);
*/

//==========================//
// EXEMPLO CONSULTA SIMPLES //
//==========================//
/*
$db = new classMySQL();
$db->consulta_bd("SELECT * FROM clientes");
*/

//==================================//
// CONSULTA COM RESULTADO EM TABELA //
//==================================//
/*
$db = new classMySQL;
$consulta = $db->consulta_bd("SELECT * FROM clientes");
$db->consulta_tabela($consulta);
*/

//=========================//
//  EXEMPLO DELETAR DADOS  //
//=========================//
/*
$db = new classMySQL();
$tabela = 'clientes';
$dados = array(
				  'id' => 3
				);
$db->deletar_dados($tabela, $dados);
*/

//=======================//
// EXEMPLO ALTERAR DADOS //
//=======================//
/*
$db = new classMySQL();
$tabela = 'clientes';
$string = 'ID = 3';
$dados = array(
				'nome' => 'Fulano Ciclano',
				'email' => 'fulanociclano@email.com'
				);
$db->alterar_dados($tablea, $dados, $string);
*/
?>
