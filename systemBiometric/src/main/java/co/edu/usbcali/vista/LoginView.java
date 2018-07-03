package co.edu.usbcali.vista;

import java.awt.FontFormatException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ViewScoped
@ManagedBean(name = "loginView")
public class LoginView {
   
	private String userId;
    private String password;
    
    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    
    public String login() {
        try {
        	
        	if(getUserId() == null | getUserId().toString().trim().equals("")) throw new Exception("El usuario es obligatorio");
        	
        	if(getPassword() == null | getPassword().toString().trim().equals("")) throw new Exception("El password es obligatorio");
        	
        	Long id = Long.parseLong(getUserId().toString().trim());
        	
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserId(),this.getPassword());
            
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);
            
            String role = securityContext.getAuthentication().getAuthorities().toArray()[0].toString().trim();
            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            if(role.equals("ROLE_ADMIN"))
            {
            	return "/admin/homeAdminGui.xhtml";
            }
            else if( role.equals("ROLE_GESTORUSUARIO"))
            {
            	return "/gestorUsuario/homeGestorUsuarioGui.xhtml";
            }
            
            
        } catch (AuthenticationException e) {
        	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario no existe o no dispone de rol para ingresar", ""));
            return "/login.xhtml";
        }
        catch (NumberFormatException e) {
        	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL id no puede contener caracteres, solo números", ""));
        	return "/login.xhtml";
		}
        catch(Exception e)
        {
        	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        	return "/login.xhtml";
        }
       return "/login.xhtml";
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}

