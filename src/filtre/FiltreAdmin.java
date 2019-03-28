package filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;

@WebFilter("/admin/*")
public class FiltreAdmin implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		if (session.getAttribute("utilisateur") != null) {
			Participant user = (Participant) session.getAttribute("utilisateur");
			
			if (user.isAdministrateur()) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect("../membre/accueil");
			}
			
			
		}else {
			resp.sendRedirect("../login");
		}
		
		
		
	}

}
