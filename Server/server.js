// Port to listen requests from
var port = 1234;

// Modules to be used
var express = require('express');
var bodyParser = require('body-parser');
var request = require('request');
var app = express();
var mysql = require('mysql');

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database: 'cuitochette'
});

app.get("/commandes", function(req, res, next) {
	var restaurant = req.query.restaurant;
	var tableauResult= new Object();
	connection.connect();	
	connection.query('SELECT * FROM COMMANDE WHERE restaurant=?',restaurant,function(err, rows, fields) {
		if (err) throw err;
		for (var i = 0; i < rows.length; i++) {
			var tableauCommande = new Object();				
			tableauCommande["idCommande"]=rows[i].id;
			tableauCommande["table"]=rows[i].taable;		
			var tableauPlats = new Object();
			connection.query('SELECT P.label, C.quantite FROM PLAT AS P, COMMANDE_PLAT AS C WHERE C.plat=P.id AND C.commande=?',rows[i].id,function(err, rows, fields) {
				if (err) throw err;				
				for (var j = 0; j < rows.length; j++) {
					tableauPlats[rows[j].label]=rows[j].quantite;
				}
				tableauCommande["plats"]=JSON.stringify(tableauPlats);
				console.log(tableauCommande);
			});
			tableauResult[rows[i].id]=JSON.stringify(tableauCommande);
		}
	});
	connection.end();		
});

app.get("/connexion", function(req, res, next) {
	// On récupère les variables de la requête
	var login = req.query.login;
	var mdp = req.query.mdp;
	connection.connect();
	connection.query("SELECT * FROM UTILISATEUR WHERE login=? AND mdp=?",[login,mdp],function (err,rows,fields) {
		res.json(rows[0]);
	});
	connection.end();
});

app.get("/insertCommandePlat", function(req, res, next) {
	// On récupère les variables de la requête
	var quantite = req.query.quantite;
	var commande = req.query.commande;
	var plat = req.query.plat
	connection.connect();
	connection.query("INSERT INTO COMMANDE_PLAT (commande,plat,quantite) VALUES ("+commande+","+plat+","+quantite+");", function (err, result) {
		console.log("1 record inserted");
	});
	connection.end();
});

app.get("/insertCommande", function(req, res, next) {
	// On récupère les variables de la requête
	var restaurant = req.query.restaurant;
	var table = req.query.table;
	console.log("resto :"+restaurant);
	console.log("table : "+table);
	connection.connect();
	connection.query("INSERT INTO COMMANDE (status, restaurant, taable) VALUES ('commande',"+restaurant+","+table+");", function (err, result) {
		if (err) throw err;
		console.log("1 record inserted");
		res.json(result.insertId);
	});
	connection.end();
});

// application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false });

// Log requests
app.all("*", urlencodedParser, function(req, res, next){
	console.log(req.method + " " + req.url);
	console.dir(req.headers);
	console.log(req.body);
	console.log();
	next();
});

// Serve static files
app.use(express.static('public'));

// Startup server
app.listen(port, function () {
	console.log('Le serveur est accessible sur http://localhost:' + port + "/");
});
