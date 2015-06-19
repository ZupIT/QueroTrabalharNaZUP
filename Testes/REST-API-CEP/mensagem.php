<?php
	class Mensagem {
		public $status;
		public $mensagem;

		public function __construct($status, $mensagem){
			$this -> status = $status;
			$this -> mensagem = $mensagem;

		}	
	}

?>