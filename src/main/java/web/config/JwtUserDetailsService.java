package web.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import web.model.Account;
import web.model.AccountDTO;
import web.repos.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepos;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepos.findByUserName(username);
		if (account == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(account.getUserName(), account.getPassword(),
				new ArrayList<>());
	}
	
	public Account save(AccountDTO accountDTO) {
		Account account = new Account();
		account.setUserName(accountDTO.getUsername());
		account.setEmail(accountDTO.getEmail());
		account.setPassword(bcryptEncoder.encode(accountDTO.getPassword()));
		account.setIsDeleted(false);
		return accountRepos.save(account);
	}
}
