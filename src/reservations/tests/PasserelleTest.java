package reservations.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import reservations.classes.Passerelle;
import reservations.classes.Reservation;
import reservations.classes.Utilisateur;

public class PasserelleTest {

	@Test
	public void testConnecter() {
		String msg = Passerelle.connecter("admin", "adminnnnnnnn");
		assertEquals("Test Passerelle.connecter", "Erreur : authentification incorrecte.", msg);
		
		msg = Passerelle.connecter("admin", "admin");
		assertEquals("Test Passerelle.connecter", "Administrateur authentifié.", msg);
		
		msg = Passerelle.connecter("giboired", "passe");
		assertEquals("Test Passerelle.connecter", "Utilisateur authentifié.", msg);	
	}

	@Test
	public void testCreerUtilisateur() {
		Utilisateur unUtilisateur = new Utilisateur(0, 4, "yvesz", "", "yves.zenels@gmail.com");
		String msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : le niveau doit être 0, 1 ou 2.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "jean-charles3", "", "jean.charles@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Enregistrement effectué ; un mail va être envoyé à l'utilisateur.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : nom d'utilisateur déjà existant.", msg);	
	}

	private static String FormaterDateHeure(Date uneDate, String unFormat) {
		SimpleDateFormat leFormat = new SimpleDateFormat(unFormat);
		return leFormat.format(uneDate);
	}
	
	@Test
	public void testConsulterReservations() {
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
		String msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
		msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Erreur : vous n'avez aucune réservation.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Vous avez effectué 5 réservation(s).", msg);
		assertEquals(5, unUtilisateur.getNbReservations());
		
		String formatUS = "yyyy-MM-dd HH:mm:ss";
		Reservation laReservation = unUtilisateur.getLaReservation(0);
		assertEquals("Hall d'accueil", laReservation.getRoomName());		
		assertEquals(0, laReservation.getStatus());	
		assertEquals("2017-06-21 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
		assertEquals("2017-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
		
		laReservation = unUtilisateur.getLaReservation(1);
		assertEquals("Hall d'accueil", laReservation.getRoomName());		
		assertEquals(0, laReservation.getStatus());	
		assertEquals("2017-06-21 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
		assertEquals("2017-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
	}
}
