package dad.geofx.utils;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.geofx.main.GeoApp;

/**
 * Clase encargada de gestionar las conexiones con las API Rest
 * @author David Fernández Nieves
 *
 */
public class UnirestObject {


	/**
	 * Obtenemos la IP del usuario a partir de la API Rest de IPIFY
	 * @return La IP del usuario
	 */
	public String getUserIP() {
		
		try {
			
			IPObject ip = Unirest
								.get("https://geo.ipify.org/api/v1?apiKey="+GeoApp.IPIFY_KEY)
								.asObject(IPObject.class)
								.getBody();
			
			return ip.getIp();
			
		} catch (UnirestException e) {
			GeoApp.sendConnectionError("Error al obtener la IP del usuario");
		}
		
		return null;
	}
	
	public LocationObject getLocationInfo(String ip) {
		
		try {
			
			LocationObject location = Unirest
											.get(String.format("http://api.ipapi.com/%s?access_key=%s&format=1", ip, GeoApp.IPAPI_KEY))
											.asObject(LocationObject.class)
											.getBody();
			
			if( location == null)
				GeoApp.sendConnectionError("Error al obtener los datos de la localización del usuario");
			
			return location;
			
		} catch( UnirestException e) {
			GeoApp.sendConnectionError("Error al obtener los datos de la localización del usuario");
		}
		
		return null;
	}
}
