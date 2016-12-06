/**
 * Application de suivi des réservations de la Maison des Ligues de Lorraine
 * Thème : développement et test des classes Reservation, Utilisateur et Passerelle
 * Auteur : JM CARTRON
 * Dernière mise à jour : 11/12/2014
 */

package reservations.classes;

import java.util.ArrayList;

/**
 * Cette classe représente un utilisateur
 */
public class Salle {

	/** Membres priv�s */
	private int _id; // identifiant de l'utilisateur
	private String _roomName; // le nom de la salle
	private int _capacity; // la capacité de la salle
	private String _areaName; // le nom de la zone

	/** Constructeurs */
	public Salle() {
		this._id = 0;
		this._roomName = "";
		this._capacity = 0;
		this._areaName = "";
	}

	public Salle(int unId, String unRoomName, int unCapacity, String unAreaName) {
		this._id = unId;
		this._roomName = unRoomName;
		this._capacity = unCapacity;
		this._areaName = unAreaName;
	}

	/** Accesseurs */
	public int getId() {
		return _id;
	}
	
	public void setId(int unId) {
		this._id = unId;
	}
	
	public String getRoomName() {
		return _roomName;
	}

	public void setRoomName(String unRoomName) {
		this._roomName = unRoomName;
	}

	public int getCapacity() {
		return _capacity;
	}

	public void setCapacity(int unCapacity) {
		this._capacity = unCapacity;
	}

	public String getAreaName() {
		return _areaName;
	}

	public void setAreaName(String unAreaName) {
		this._areaName = unAreaName;
	}	

}
