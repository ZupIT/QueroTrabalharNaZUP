<?php

	require_once "cadastro.php";
	require_once "mensagem.php";

	class db {
		private $host;
		private $connection;
		private $password;
		private $user;
		private $db_name;
		
		public function __construct($host, $user, $password, $db_name){
			$this -> host = $host;
			$this -> user = $user;
			$this -> password = $password;
			$this -> db_name = $db_name;
		}
		
		public function connect(){
			$this->connection = new mysqli ($this->host, $this->user, $this->password, $this->db_name);	
				if($this->connection->connect_error){
					die($this->connection->connect_error);
				}
		}
		
		public function save($obj){
			//insert into endereco(cep, endereco, bairro, cidade, estado) values(15061560,'jorge abrao','jardim yolanda','sjrp','sp')
			$sql = "insert into endereco(cep, endereco, bairro, cidade, estado) values(".$obj->cep.",'".$obj->endereco."','".$obj->bairro."','".$obj->cidade."','".$obj->estado."')";
			if($this->connection->query($sql) == TRUE){
				$mensagemSucesso = new Mensagem("SUCESSO", "O endereço foi cadastrado com sucesso.");
				return json_encode($mensagemSucesso);
			}
			$mensagemErro = new Mensagem("ERRO","O endereço informado já foi cadastrado.");
			return json_encode($mensagemErro);
		}
		
		public function close(){
			$this->connection->close();
		}
		
		public function search($cep){
			$tamanho = strlen($cep);
			if($tamanho != 8){
				$mensagemErro = new Mensagem("ERRO", "O CEP informado é inválido");
				return json_encode($mensagemErro);
			}
			$sql = "select * from endereco where cep=".$cep;			
			$result = $this->connection->query($sql);
				if($result->num_rows > 0){
					while ($row = $result->fetch_assoc()){
						$cadastro = new Cadastro($row['cep'],$row['endereco'],$row['bairro'],$row['cidade'],$row['estado']);
						return json_encode($cadastro);
						
					}	
				}
				$mensagemErro = new Mensagem("ERRO","O CEP informado não foi encontrado");
				return json_encode($mensagemErro);
			}
		}
	
	


?>