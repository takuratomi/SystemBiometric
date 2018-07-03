package co.edu.usbcali.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clienteRest")
public class ClienteServicioRest {

	/*@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	@RequestMapping(value="/consultarPorId/{id}",method=RequestMethod.GET)
	public ClienteDTO consultarClientePorId(@PathVariable("id")Long id)throws Exception{
		Clientes clientes=clienteLogica.consultarPorId(id);
		if(clientes==null){
			return null;
		}
		
		ClienteDTO clienteDTO=new ClienteDTO();
		clienteDTO.setCliDireccion(clientes.getCliDireccion());
		clienteDTO.setCliId(clientes.getCliId());
		clienteDTO.setCliMail(clientes.getCliMail());
		clienteDTO.setCliNombre(clientes.getCliNombre());
		clienteDTO.setCliTelefono(clientes.getCliTelefono());
		clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
		
		return clienteDTO;
	}
	
	@RequestMapping(value="/crear", method = RequestMethod.POST)
	public void crearCliente(@RequestBody ClienteDTO clienteDTO)throws Exception{
		
		Clientes clientes=new Clientes();
		clientes.setCliDireccion(clienteDTO.getCliDireccion());
		clientes.setCliId(clienteDTO.getCliId());
		clientes.setCliMail(clienteDTO.getCliMail());
		clientes.setCliNombre(clienteDTO.getCliNombre());
		clientes.setCliTelefono(clienteDTO.getCliTelefono());
		
		TiposDocumentos tiposDocumentos=tipoDocumentoLogica.consultarPorId(clienteDTO.getTdocCodigo());
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clienteLogica.crear(clientes);
		
	}
	
	@RequestMapping(value="/modificar", method = RequestMethod.PUT)
	public void modificarCliente(@RequestBody ClienteDTO clienteDTO)throws Exception{
		
		Clientes clientes=clienteLogica.consultarPorId(clienteDTO.getCliId());
		if(clientes==null){
			throw new Exception("El cliente no existe");
		}
		clientes.setCliDireccion(clienteDTO.getCliDireccion());
		clientes.setCliId(clienteDTO.getCliId());
		clientes.setCliMail(clienteDTO.getCliMail());
		clientes.setCliNombre(clienteDTO.getCliNombre());
		clientes.setCliTelefono(clienteDTO.getCliTelefono());
		
		TiposDocumentos tiposDocumentos=tipoDocumentoLogica.consultarPorId(clienteDTO.getTdocCodigo());
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clienteLogica.modificar(clientes);
		
	}
	
	@RequestMapping(value="/borrar/{id}", method = RequestMethod.DELETE)
	public void borrarCliente(@PathVariable("id")Long id)throws Exception{
		
		Clientes clientes=clienteLogica.consultarPorId(id);
		if(clientes==null){
			throw new Exception("El cliente no existe");
		}
		
		clienteLogica.borrar(clientes);
		
	}*/
	

}
