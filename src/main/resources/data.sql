/*insert into commune (com_id, com_code_insee, com_latitude, com_longitude, com_nb_habitants, com_nom) values (1,
44109, 47.218371, -1.553621, 303382, 'Nantes');
insert into commune (com_id, com_code_insee, com_latitude, com_longitude, com_nb_habitants, com_nom) values (2,
44036, 47.7167, -1.3833, 12067, 'Chateaubriant');

insert into code_postal(cp_id, cp_valeur, cp_commune_id) values (1, 44000, 1);
insert into code_postal(cp_id, cp_valeur, cp_commune_id) values (2, 44100, 1);
insert into code_postal(cp_id, cp_valeur, cp_commune_id) values (3, 44110, 2);*/
insert into utilisateur (uti_nom, uti_prenom, uti_email, uti_mot_de_passe, uti_statut_notification, uti_tentative_connexion, uti_date_derniere_connexion) values('peyras', 'cecile', 'cecile@test.fr', '$2a$10$RztZElMbPpfpQTH0fUshh.xi4wKEZYSvej6WvPHM246p0k28ZD7GO', 0,  0,'2019-09-10 00:00:00');
insert into utilisateur (uti_nom, uti_prenom, uti_email, uti_mot_de_passe, uti_statut_notification, uti_tentative_connexion, uti_date_derniere_connexion) values('peyras2', 'cecile2', 'cecile2@test.fr', '$2a$10$RztZElMbPpfpQTH0fUshh.xi4wKEZYSvej6WvPHM246p0k28ZD7GO', 0,  0,'2019-09-10 00:00:00');
insert into UTILISATEUR_STATUT values (1 , 'ADMINISTRATEUR' );
insert into UTILISATEUR_STATUT values (2, 'MEMBRE');

/* DONNEES SUIVANTES EN COMMENTAIRE POUR FAVORISER L'UTILISATION DES DONNEES REELLEMENT RECUPEREES */

/*
insert into condition_meteo values (1, '2019-09-12 14:00:00', 10, 20, 20);
insert into polluant (pol_id, pol_nom, pol_unite, pol_valeur) values (1, 'azote', 'µg', 12);
insert into  qualite_air values (1, '2019-09-12 14:00:00');
insert into donnees_locales (don_id, don_date,don_condition_meteo, don_qualite_air ) values (1,'2019-09-09 14:00:00', 1, 1 );
insert into donnees_locales (don_id, don_date,don_condition_meteo, don_qualite_air ) values (2,'2019-09-12 14:00:00', 1, 1 );
insert into qualite_air values (1, '2019-09-12 14:00:00', 'Nantes');
*/

