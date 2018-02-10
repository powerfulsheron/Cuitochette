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
	connection.connect();
	connection.query('SELECT * FROM COMMANDE_PLAT', function(err, rows, fields) {
		if (err) throw err;
		res.json(rows);
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
