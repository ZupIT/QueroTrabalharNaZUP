<?php 
// Recebemos envios de qualquer origem
header("Access-Control-Allow-Orgin: *");
header("Access-Control-Allow-Methods: *");

// Setamos o header para aplicaçao em json
header("Content-Type: application/json");

// Incluimos e chamamos a classe do banco de dados
include("class.mysql.php");
$db = new classMySQL();

// Vejo se estamos recebendo um arquivo json via POST
$json = json_decode(file_get_contents('php://input'), true);

// Se sim, setamos o a variavel $_POST para receber os dados do json
// Utilizei esse metodo para nao criar outras variaveis e regras pra isso, sendo que ja existem
if($json == true){
  $_POST['cep']       = $json['cep'];
  $_POST['endereco']  = $json['endereco'];
  $_POST['bairro']    = $json['bairro'];
  $_POST['cidade']    = $json['cidade'];
  $_POST['estado']    = $json['estado'];
}

//Verificamos se o CEP esta setado e se esta vazio
if(isset($_POST['cep']) AND $_POST['cep'] != ""){
  // Retiramos o traço do CEP
  $cep = str_replace("-","",$_POST['cep']);
  // Verificamos se o CEP e numero e se o tamanho dele e igual a 8
  if(is_numeric($cep) AND strlen($cep) == 8){
    // Efetuamos uma busca no banco de dados pelo CEP
    $db->consulta_bd("SELECT * FROM tb_ceps WHERE cep = '$cep' AND status = 1");
    // Se nao houverem resultados, continuamos o processo
    // Verificamos todos os dados, para saber se estao setados e se nao estao vazios
    if($db->consulta_registros() == 0){
      if(isset($_POST['endereco']) AND $_POST['endereco'] != ""){
        $endereco = $_POST['endereco'];
      }

      if(isset($_POST['bairro']) AND $_POST['bairro'] != ""){
        $bairro = $_POST['bairro'];
      }

      if(isset($_POST['cidade']) AND $_POST['cidade'] != ""){
        $cidade = $_POST['cidade'];
      }

      if(isset($_POST['estado']) AND $_POST['estado'] != ""){
        $estado = $_POST['estado'];
      }

      // Tabela em qual iremos inserir os dados
      $tabela = 'tb_ceps';
      // Dados que serao inseridos na tabela, lado esquerdo o campo no banco, lado direito a variavel
      $dados = array(
                'cep'       => "$cep",
                'endereco'  => utf8_decode("$endereco"),
                'bairro'    => utf8_decode("$bairro"),
                'cidade'    => utf8_decode("$cidade"),
                'estado'    => utf8_decode("$estado"),
                'status'    => "1"
              );
      // Efetuamos a insercao no banco de dados
      $db->inserir_dados($tabela, $dados);

      // Retornamos o status e a mensagem em JSON
      $mensagem["status"]   = "SUCESSO";
      $mensagem["mensagem"] = "O endereço foi cadastrado com sucesso.";
      echo json_encode($mensagem);
    }else{
      // Se houver erro, retornamos o status e a mensagem de erro em JSON
      $mensagem["status"]   = "ERRO";
      $mensagem["mensagem"] = "O endereço informado já foi cadastrado.";
      echo json_encode($mensagem);
    }
  }
}
?>