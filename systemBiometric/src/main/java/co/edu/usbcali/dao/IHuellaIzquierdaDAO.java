package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.HuellaIzquierda;

public interface IHuellaIzquierdaDAO {

	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda);
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda);
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda);
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id);
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda();
	
}
