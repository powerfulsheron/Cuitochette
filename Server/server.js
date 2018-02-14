// Port to listen requests from
var port = 1234;

// Modules to be used
var express = require('express');
var bodyParser = require('body-parser');
var request = require('request');
var app = express();
var mysql = require('mysql');
var Map = require("collections/map");

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database: 'cuitochette',
});

app.get("/plats", function(req, res, next) {
	var restaurant = req.query.restaurant;
	var plats = [];
	var result = new Object();
	connection.query('SELECT * FROM PLAT WHERE restaurant=?',restaurant,function(err, rows, fields) {
		for (var i = 0; i < rows.length; i++) {
			var plat = new Object();			
			plat["id"]=rows[i].id;
			plat["label"]=rows[i].label;
			plat["description"]=rows[i].description;
			plat["type"]=rows[i].type;
			plats.push(plat);	
		}
		result["plats"]=plats;
		res.json(result);
	});
});

app.get("/commandes2", function(req, res, next) {
	var restaurant = req.query.restaurant;
	var tableauResult= new Object();
	connection.query('SELECT CP.commande, C.taable, C.status, P.label, CP.quantite FROM PLAT AS P, COMMANDE AS C, COMMANDE_PLAT AS CP WHERE CP.plat=P.id AND C.id=CP.commande AND C.restaurant=?',restaurant,function(err, rows, fields) {
		if (err) throw err;
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].commande in tableauResult){
				tableauResult[rows[i].commande]["plats"][rows[i].label]=rows[i].quantite;
			}else{
				var tableauCommande = new Object();
				tableauCommande["table"]=rows[i].taable;
				tableauCommande["status"]=rows[i].status;				
				var tableauPlats = new Object();
				tableauPlats[rows[i].label]=rows[i].quantite;				
				tableauCommande["plats"]=tableauPlats;
				tableauResult[rows[i].commande]=tableauCommande;
			}
		}
		res.json(tableauResult);
	});
});

app.get("/commandes", function(req, res, next) {
	var restaurant = req.query.restaurant;
	var tableauResult= new Object();
	connection.query('SELECT CP.commande, C.taable, C.status, P.label, P.type, CP.quantite FROM PLAT AS P, COMMANDE AS C, COMMANDE_PLAT AS CP WHERE CP.plat=P.id AND C.id=CP.commande AND C.restaurant=?',restaurant,function(err, rows, fields) {
		if (err) throw err;
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].commande in tableauResult){
				var plat = new Object();
				plat["label"]=rows[i].label;
				plat["type"]=rows[i].type;
				plat["quantite"]=rows[i].quantite;	
				tableauResult[rows[i].commande]["plats"].push(plat);
			}else{
				var tableauCommande = new Object();
				tableauCommande["table"]=rows[i].taable;
				tableauCommande["status"]=rows[i].status;				
				var tableauPlats = [];
				var plat = new Object();
				plat["label"]=rows[i].label;
				plat["type"]=rows[i].type;
				plat["quantite"]=rows[i].quantite;
				tableauPlats.push(plat);
				tableauCommande["plats"]=tableauPlats;
				tableauResult[rows[i].commande]=tableauCommande;
			}
		}
		res.json(tableauResult);
	});
});

function strMapToObj(strMap) {
    let obj = Object.create(null);
    for (let [k,v] of strMap) {
        // We don’t escape the key '__proto__'
        // which can cause problems on older engines
        obj[k] = v;
    }
    return obj;
}

app.get("/connexion", function(req, res, next) {
	// On récupère les variables de la requête
	var login = req.query.login;
	var mdp = req.query.mdp;
	connection.query("SELECT * FROM UTILISATEUR WHERE login=? AND mdp=?",[login,mdp],function (err,rows,fields) {
		if (err) throw err;				
		res.json(rows[0]);
	});	
});

app.get("/insertCommandePlat", function(req, res, next) {
	// On récupère les variables de la requête
	var quantite = req.query.quantite;
	var commande = req.query.commande;
	var plat = req.query.plat
	connection.query("INSERT INTO COMMANDE_PLAT (commande,plat,quantite) VALUES ("+commande+","+plat+","+quantite+");", function (err, result) {
		if (err) throw err;				
		console.log("1 record inserted");
	});
});

app.get("/insertCommande", function(req, res, next) {
	// On récupère les variables de la requête
	var restaurant = req.query.restaurant;
	var table = req.query.table;
	console.log("resto :"+restaurant);
	console.log("table : "+table);
	connection.query("INSERT INTO COMMANDE (status, restaurant, taable) VALUES ('commande',"+restaurant+","+table+");", function (err, result) {
		if (err) throw err;
		console.log("1 record inserted");
		res.json(result.insertId);
	});
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
