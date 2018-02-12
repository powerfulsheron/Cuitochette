Url types


http://localhost:1234/commandes?restaurant=1

retour json : 

{"1":{"table":1,"status":"commande","plats":{"Bourguignon de Boeuf":3,"Mousse au Chocolat":3}},"2":{"table":1,"status":"entreeEnvoye","plats":{"Salade de Crevettes":2,"Bourguignon de Boeuf":2}},"3":{"table":1,"status":"platEnvoye","plats":{"Columbo de Porc":3,"Bourguignon de Boeuf":1,"Mousse au Chocolat":4}},"4":{"table":1,"status":"dessertEnvoye","plats":{"Salade de Crevettes":1,"Bourguignon de Boeuf":1}}}


http://localhost:1234/connexion?login=chef&mdp=12345

retour json : 

{"id":2,"login":"chef","mdp":"12345","type":"chef"}


http://localhost:1234/inserCommande?restaurant=1&table=7

retourne l'id de la commande ex:13


http://localhost:1234/inserCommandePlat?commande=1&plat=4?quantite=2

ne retourne rien

