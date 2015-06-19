<?php 
    class Cadastro {
		public $cep; 
		public $endereco;
		public $bairro; 
		public $cidade; 
		public $estado; 
        
        public function __construct($cep, $endereco, $bairro, $cidade, $estado){
            $this-> cep = $cep;
            $this-> endereco = $endereco;
            $this-> bairro = $bairro;
            $this-> cidade = $cidade;
            $this-> estado = $estado;
        }
        
    }
?>