package co.edu.usbcali.demo.seguridad;

import java.awt.FontFormatException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.vista.IDelegadoDelNegocio;

@Scope("singleton")
@Component("proveedorDeSeguridad")
public class ProveedorDeSeguridad implements AuthenticationProvider {

	@Autowired
	private IDelegadoDelNegocio delegadoDelNegocio;
	
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
        
    	Usuario user ;
        
    	String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        try {
			
        	
        	Long id = Long.parseLong(name.toString().trim());
        	user = delegadoDelNegocio.consultarPorIdUsuario(id);
        	
        	if(user == null) throw new Exception("EL usuario no existe");
        	else
        	{
        		if(user.getTipoUsuario().getTusuId() == 1)
        		{
        			 final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        	            final UserDetails principal = new User(name, password, grantedAuths);
        	            final Authentication auth = new UsernamePasswordAuthenticationToken(principal,password, grantedAuths);

        	            return auth;
        		}
        		else if(user.getTipoUsuario().getTusuId() == 3)
        		{
        		
        			final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
    	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_GESTORUSUARIO"));

    	            final UserDetails principal = new User(name, password, grantedAuths);
    	            final Authentication auth = new UsernamePasswordAuthenticationToken(principal,password, grantedAuths);

    	            return auth;

        		}
        		else
        		{
        			throw new Exception("EL usuario no tiene rol autorizado paraingresar.");
        		}
        	}        	
		} 
        catch (Exception e) {	
        	return null;
		}
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
