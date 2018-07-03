package co.edu.usbcali.utilidad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilidad {
	
	private static Logger log = LoggerFactory.getLogger(Utilidad.class);
	
	
	public static boolean isNumeric(String cadena)
	{
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	public static void main(String args[])
	{
		System.out.println("HELLOW WORDL");
	}

}
