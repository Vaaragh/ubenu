package Ubenu.model.utilities;

import java.util.UUID;

public class IdGen {
	
	public static String newID() {
		
		UUID id = UUID.randomUUID();
		return id.toString();
		
		
	}

}