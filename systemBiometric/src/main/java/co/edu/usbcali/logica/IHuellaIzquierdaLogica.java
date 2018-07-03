package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.HuellaIzquierda;

public interface IHuellaIzquierdaLogica {

	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id) throws Exception;
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda() throws Exception;
	
}
