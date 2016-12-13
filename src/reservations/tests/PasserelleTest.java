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
	public void testConsulterSalles() {
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
		String msg = Passerelle.consulterSalles("jim", "adminnnnnnnn");
		assertEquals("Test Passerelle.consulterSalles", "Erreur : données incomplètes.", msg);
		
		msg = Passerelle.consulterSalles("giboired", "passeeeeeeeeeee");
		assertEquals("Test Passerelle.consulterSalles", "3", msg);	
	}
	
	
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
		assertEquals("Test Passerelle.creerUtilisateur", "Enregistrement effectué ; un mail va �tre envoyé à l'utilisateur.", msg);
		
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
	
	@Test
	public void testConfirmerReservations(){
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
		String msg = Passerelle.confirmerReservation(unUtilisateur, "7");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
		msg = Passerelle.confirmerReservation(unUtilisateur, "99");
		assertEquals("Erreur : numéro de réservation inexistant.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "jim", "fromage", "");
		msg = Passerelle.confirmerReservation(unUtilisateur, "8");
		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.confirmerReservation(unUtilisateur, "9");
		assertEquals("Erreur : cette réservation est dejà passée.", msg);

		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.confirmerReservation(unUtilisateur, "8");
		assertEquals("Enregistrement effectue ; vous allez recevoir un mail de confirmation.", msg);
		
	}
	
	@Test
	public void testAnnulerReservation(){
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
		String msg = Passerelle.annulerReservation(unUtilisateur, "7");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
		msg = Passerelle.annulerReservation(unUtilisateur, "99");
		assertEquals("Erreur : numéro de réservation inexistant.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "jim", "fromage", "");
		msg = Passerelle.annulerReservation(unUtilisateur, "4");
		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.annulerReservation(unUtilisateur, "4");
		assertEquals("Erreur : cette réservation est déjà passée.", msg);

		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.annulerReservation(unUtilisateur, "6");
		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
	}


	@Test
	public void testChangerDeMdp(){
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "jim", "fromage", "");
		String msg = Passerelle.changerDeMdp(unUtilisateur, "jim", "jiim");
		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont différents.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "dddd", "passe", "");
		 msg = Passerelle.changerDeMdp(unUtilisateur, "jim", "jim");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "jim", "fromage", "");
		 msg = Passerelle.changerDeMdp(unUtilisateur, "jim", "jim");
		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);		
	}
	
	@Test
	public void testDemanderMdp(){
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "zjkegfzuig", "", "");
		String msg = Passerelle.demanderMdp(unUtilisateur);
		assertEquals("Erreur : nom d'utilisateur inexistant.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "jim", "", "");
		msg = Passerelle.demanderMdp(unUtilisateur);
		assertEquals("Vous allez recevoir un mail avec votre nouveau mot de passe.", msg);
		
	}
	
	@Test
	public void testSupprimerUtilisateur(){
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "admin", "aaa", "");
		String msg = Passerelle.supprimerUtilisateur(unUtilisateur, "jim");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "jim", "jim", "");
		msg = Passerelle.supprimerUtilisateur(unUtilisateur, "jean-charles");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.supprimerUtilisateur(unUtilisateur, "jean-charles");
		assertEquals("Erreur : cet utilisateur a passé des réservations à venir.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "admin", "");
		msg = Passerelle.supprimerUtilisateur(unUtilisateur, "jean-charles2");
		assertEquals("Suppression  effectuée ; un mail va être envoyé à l'utilisateur.", msg);
		
		
		

		
		
	}
	
	
}
