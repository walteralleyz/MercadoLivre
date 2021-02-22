package br.com.zup.MercadoLivre.security;

import br.com.zup.MercadoLivre.exception.UserNotFoundException;
import br.com.zup.MercadoLivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityAuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UserNotFoundException {
        return repository.findByLogin(s).orElseThrow(() -> new UserNotFoundException("login"));
    }
}
