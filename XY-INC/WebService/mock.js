// 
var express = require('express');
var app = express();
var fs = require('fs');
var url = require('url');
var bodyParser = require('body-parser');
var jsonParser = bodyParser.json({ type: 'application/json'});
//
app.get('/pois', function(req, res) { 
//
  res.header("Content-Type", "application/json; charset=utf-8");
//
  var url_parts = url.parse(req.url, true);
  var query = url_parts.query;
  var x = query.x;
  var dmax = query.dmax;
  var y = query.y;
  var defaultResponse = {cod: 0, message:"", data:{}};
//
  if(Object.keys(query).length === 0) {
    fs.readFile(__dirname + "/pois.json", 'utf8', function(error, data) { 
      res.end(data);
    });
    //
  } else if(!x) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "coordenada x inválida ou não informada";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse));
  } else if(!y) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "coordenada y inválida ou não informada";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse));
  } else if(!dmax) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "Distância máxima inválida ou não informada";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse));   
  } else {
     fs.readFile(__dirname + "/pois.json", 'utf8', function(error, data) { 
       var jsonPois = JSON.parse(data);
       var filteredPois = [];

     for(var i = 0; i < jsonPois.pois.length; i++) {   
       var poi = jsonPois.pois[i];
       var equalized_x = poi.x - x;
       var equalized_y = poi.y - y;
       var distance = Math.sqrt(Math.pow(Math.abs(equalized_x),2) + Math.pow(Math.abs(equalized_y), 2));
       if(distance <= dmax) {
          filteredPois.push(poi);  }
       }
       res.end(JSON.stringify(filteredPois));
        
    });
  }
    
});
app.post('/pois',jsonParser, function(req, res) {
  console.log(req.body);
  var nome = req.body.nome;
  var x =  req.body.x;
  var y =  req.body.y;
  
  var defaultResponse = {cod: 0, message:"", data:{}};

  if(!x) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "coordenada x inválida ou não informada";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse));   
  } else if (!y) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "coordenada y inválida ou não informada";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse)); 
  } else if (!nome) {
     defaultResponse.cod = 1602;
     defaultResponse.message = "nome inválido ou não informado";
     defaultResponse.data = {};
     res.end(JSON.stringify(defaultResponse)); 
  } else {
    
    fs.readFile(__dirname + "/pois.json", 'utf8', function(error, data) { 
        var listPois = (JSON.parse(data));
        listPois.pois.push(req.body);

        fs.writeFile(__dirname + "/pois.json", JSON.stringify(listPois), function(error) {
         if (error) {
            defaultResponse.cod = 1602;
            defaultResponse.message = "Não foi possivel realizar o cadastro";
            defaultResponse.data = {};
            res.end(JSON.stringify(defaultResponse)); 
         } else {
            defaultResponse.cod = 0111;
            defaultResponse.message = "Ponto de interesse cadastrado com sucesso";
            defaultResponse.data = {}; 
            res.end(JSON.stringify(defaultResponse)); 
         }
        });
    });
  }
});

var server = app.listen(8081, function () {
var host = server.address().address
var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})
