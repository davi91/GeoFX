package dad.geofx.root;

/**
 * Se encargará de gestionar todas las pestañas y además la IP del usuario.
 * @author David Fernández Nieves
 *
 */
public class RootController {	

	private RootView view = new RootView();
	
	// El modelo en realidad estará constituido por los content property de cada uno de los tab
	// y por la IP del principio.
	
	
	public RootController() {
		
	}
	
	public RootView getRootView() {
		return view;
	}
}
