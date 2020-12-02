package fr.applications.appli3.messages;

import java.util.ResourceBundle;

public abstract class messages {
private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("fr.applications.appli3.messages.messages_error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  String getMessageError(int code) {
		String message="";
		try {
			if(rb!=null) {
				message = rb.getString(String.valueOf(code));
			} else {
				message="Problème de lecture";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="Une erreur est survenue";
		}
		return message;
	}

}
