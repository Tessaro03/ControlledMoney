package ControlledMoney.api.infra.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Security.SecurityFilter;
import ControlledMoney.api.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenUsuario {
    
    @Autowired
    TokenService tokenService;

    @Autowired
    SecurityFilter securityFilter;
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario usuarioToken(HttpServletRequest request){
        var token = securityFilter.recuperarToken(request);
        var login = tokenService.getSubject(token);
        var usuario = repository.UsuarioPorLogin(login);
        return usuario;
    }
}
