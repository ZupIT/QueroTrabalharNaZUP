
<?php
//importando API
require 'Slim/Slim.php';
\Slim\Slim::registerAutoloader();
$app = new \Slim\Slim();

$app->contentType('application/json');
$app->get('/busca', 'getCEP_list');     //  Atribuindo funções a URL 
$app->get('/busca/:cep', 'getCEP');
$app->post('/cadastro', 'addEndereco');

$app->run();

//função buscar todos os endereços 
function getCEP_list() {
    $sql = "select * FROM endereco";
    try {
        $db = getConnection();                     //abre conexao
        $stmt = $db->query($sql);                  //consulta o sql especificado 
        $cep = $stmt->fetchAll(PDO::FETCH_OBJ);    //retorna lista de obj do banco
        $db = null;
        echo json_encode($cep);                    //Retorna a representação JSON de um valor
        
    } catch (PDOException $e) {
        echo json_encode($e->getMessage());
    }
}

//função retorna os enderecos de um determinado CEP
function getCEP($cep) {   
    
    $cep = preg_replace("/[^0-9]/", "", $cep); //subistitui tudo que não for numero por vazio
    
    if (strlen($cep) <> 8) {                   //caso o CEP não contenha 8 digitos é invalido   
        $json_resposta = '{"status":"ERRO", "mensagem":"O CEP informado e invalido"}';
        echo json_encode($json_resposta);
    } else {
        $sql = "select * FROM endereco where cep = $cep";
        try {
            $db = getConnection();
            $stmt = $db->query($sql);
            $cep = $stmt->fetchAll(PDO::FETCH_OBJ);
            $db = null;

            if ($cep) {
                echo json_encode($cep);
            } else {                                // caso a query retornar vazia, cep não encontrado
                $json_resposta = '{"status":"ERRO", "mensagem":"O CEP informado nao foi encontrado"}';
                echo json_encode($json_resposta);
            }
        } catch (PDOException $e) {
            echo json_encode($e->getMessage());
        }
    }
}

//funcao cdastrar um novo endereco
function addEndereco() {
    $req = \Slim\Slim::getInstance()->request();
    $endereco = json_decode($req->getBody());     //converte um json em um objeto 
    $endereco->cep = preg_replace("/[^0-9]/", "", $endereco->cep);  //subistitui tudo que não for numero por vazio
    $sql = "select `cep`,`endereco`,`bairro`,`cidade`,`estado` FROM endereco where cep =  $endereco->cep ";
    $db = getConnection();
    $stmt = $db->query($sql);
    $enderecos_list = $stmt->fetchAll(PDO::FETCH_OBJ);      //recebe todos os endereços daquele CEP

    $count = 0;      //variavel contadora se o endereco ja existe
    foreach ($enderecos_list as $ende) {   //for dos enderecos existentes
       if ($ende == $endereco) {            // compara existente com o novo endereço
            $count++;                       //caso existir $count soma+1
        }
    }
    if ($count > 0) {                      //count > 0 então ja existe um enredeço igual
        $json_resposta = '{"status":"ERRO", "mensagem":"O endereco informado ja foi cadastrado."}';
        echo json_encode($json_resposta);
    } else {       // se não cadastre o novo endereco

        $sql = "INSERT INTO endereco(`cep`,`endereco`,`bairro`,`cidade`,`estado`) VALUES(:cep, :endereco, :bairro, :cidade,:estado)";
        try {
            $db = getConnection();
            $stmt = $db->prepare($sql);
            $stmt->bindParam("cep", $endereco->cep);              //atribuindo valor aos parametros do insert     
            $stmt->bindParam("endereco", $endereco->endereco);
            $stmt->bindParam("bairro", $endereco->bairro);
            $stmt->bindParam("cidade", $endereco->cidade);
            $stmt->bindParam("estado", $endereco->estado);
            $stmt->execute();
            $db = null;
            $json_resposta = '{"status":"SUCESSO", "mensagem":"O endereco foi cadastrado com sucesso"}';
            echo json_encode($json_resposta);
        } catch (PDOException $e) {
            echo json_encode($e->getMessage());
        }
    }
}

//conexao com o banco de dados
function getConnection() {
    $dbhost = "127.0.0.1";
    $dbuser = "root";
    $dbpass = "";
    $dbname = "api_cep";  //nome que escolhi para o banco de dados
    $dbh = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    return $dbh;
}

?>