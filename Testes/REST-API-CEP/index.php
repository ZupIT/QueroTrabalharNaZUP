<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Zup - API de CEP</title>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

  </head>
  <body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="resultado"></div>
			</div>
			<div class="col-md-12">
				<h1>Cadastro de Endereço por CEP</h1>
				<hr>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				    <label for="cep">CEP</label>
				    <input type="text" class="form-control cep" id="cep" name="cep" required>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				    <label for="endereco">Endereço</label>
				    <input type="text" class="form-control endereco" id="endereco" name="endereco" required>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				    <label for="bairro">Bairro</label>
				    <input type="text" class="form-control bairro" id="bairro" name="bairro" required>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				    <label for="cidade">Cidade</label>
				    <input type="text" class="form-control cidade" id="cidade" name="cidade" required>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				    <label for="estado">Estado</label>
				    <input type="text" class="form-control estado" id="estado" name="estado" required>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label>Açao</label>
				    <button type="submit" class="btn btn-info cadastrar col-md-12 col-xs-12">Cadastrar</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		// Ao clicar no botao de cadastrar, pegamos o valor de todos os inputs
		$(".cadastrar").on("click",function(){
			var cep 		= $(".cep").val();
			var endereco 	= $(".endereco").val();
			var bairro 		= $(".bairro").val();
			var cidade 		= $(".cidade").val();
			var estado 		= $(".estado").val();

			// Enviamos para o cadastro.php via POST e recebendo a resposta em JSON
			$.ajax({
				url: 'cadastro.php',
				type: 'POST',
				dataType: 'json',
				data: {cep: cep, endereco: endereco, bairro:bairro, cidade:cidade, estado:estado},
				success: function(data){
					// Limpamos a div de resultado
					$(".resultado").html("");
					// Se houver sucesso ou nao, adiciona na div resultado a mensagem recebida
					if(data.status == "SUCESSO"){
						$(".resultado").append('<div class="alert alert-success" role="alert">'+data.mensagem+'</div>');
					}else if(data.status == "ERRO"){
						$(".resultado").append('<div class="alert alert-danger" role="alert">'+data.mensagem+'</div>');
					}
				}
			});			
		});
	});
	</script>
  </body>
</html>