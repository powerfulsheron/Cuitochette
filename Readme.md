# Cuitochette
## Valier Méryl
## Baptiste Gautier
## Tygrou Julien
## Canavaggio Lorenzo





### Url types

http://localhost:1234/commandes?restaurant=1

retour json:

{"1":{"table":1,"status":"commande","plats":[{"label":"Bourguignon de Boeuf","quantite":3},{"label":"Mousse au Chocolat","quantite":3}]},"2":{"table":1,"status":"entreeEnvoye","plats":[{"label":"Salade de Crevettes","quantite":2},{"label":"Bourguignon de Boeuf","quantite":2}]},"3":{"table":1,"status":"platEnvoye","plats":[{"label":"Columbo de Porc","quantite":3},{"label":"Bourguignon de Boeuf","quantite":1},{"label":"Mousse au Chocolat","quantite":4}]},"4":{"table":1,"status":"dessertEnvoye","plats":[{"label":"Salade de Crevettes","quantite":1},{"label":"Bourguignon de Boeuf","quantite":1}]}}


http://localhost:1234/commandes2?restaurant=1

retour json : 

{"1":{"table":1,"status":"commande","plats":{"Bourguignon de Boeuf":3,"Mousse au Chocolat":3}},"2":{"table":1,"status":"entreeEnvoye","plats":{"Salade de Crevettes":2,"Bourguignon de Boeuf":2}},"3":{"table":1,"status":"platEnvoye","plats":{"Columbo de Porc":3,"Bourguignon de Boeuf":1,"Mousse au Chocolat":4}},"4":{"table":1,"status":"dessertEnvoye","plats":{"Salade de Crevettes":1,"Bourguignon de Boeuf":1}}}


http://localhost:1234/connexion?login=chef&mdp=12345

retour json : 

{"id":2,"login":"chef","mdp":"12345","type":"chef"}


http://localhost:1234/inserCommande?restaurant=1&table=7

retourne l'id de la commande ex:13


http://localhost:1234/inserCommandePlat?commande=1&plat=4?quantite=2

ne retourne rien


http://localhost:3306/plats?restaurant=1

[{"id":1,"label":"Salade de Crevettes","description":"Une salade fraiche du marché avec des crevettes du poissonnier","type":"entree\t","restaurant":1},{"id":2,"label":"Bourguignon de Boeuf","description":"Sauté de boeuf mijoté pendant 5 heures","type":"plat","restaurant":1},{"id":3,"label":"Mousse au Chocolat","description":"75% de chocolat","type":"dessert","restaurant":1},{"id":4,"label":"Nems","description":"Les vrais nems du Vietnam","type":"entree","restaurant":1},{"id":5,"label":"Columbo de Porc","description":"La vraie recette Antillaise","type":"plat","restaurant":1},{"id":6,"label":"Gateau à l'Ananas","description":"Fait maison le matin même","type":"dessert","restaurant":1},{"id":7,"label":"Fanta","description":"Canette de 33cl","type":"boisson","restaurant":1},{"id":8,"label":"Coca","description":"Canette de 33cl","type":"boisson","restaurant":1}]
