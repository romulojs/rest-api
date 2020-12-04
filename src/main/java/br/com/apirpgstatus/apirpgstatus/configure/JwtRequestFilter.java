package br.com.apirpgstatus.apirpgstatus.configure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IUsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.service.JwtUserDetailsService;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IUsuariosService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUsuariosService _usuarioService;
	
	@Autowired
	private IUsuariosRepository _usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String idUsuario = null;
		String jwtToken = null;
		
		// JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				idUsuario = jwtTokenUtil.getIdFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Não foi possível construir o token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt Token expirou");
			}
		} else {
			logger.warn("Jwt não inicia com a nomenclatura 'Bearer'");
		}
		
		// Tendo o token, valide-o.
		if (idUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			Usuarios usuario = this._usuarioRepository.findById(Long.valueOf(idUsuario)).get();

			if (jwtTokenUtil.validateToken(jwtToken, usuario)) {
				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(usuario.getUsername());
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		chain.doFilter(request, response);
	}
}
