package web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.model.Account;
import web.repos.AccountRepository;
import web.exception.AccountExistsException;
import web.exception.AccountNotFoundException;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/account")
	public List<Account> getAll() {
		return accountRepository.findAllAvailable();
	}

	@GetMapping("/account/{id}")
	public Account getById(@PathVariable int id) {
		return accountRepository.findById(id).filter(account -> !account.getIsDeleted())
				.orElseThrow(() -> new AccountNotFoundException(id));
	}

	@PostMapping("/account")
	public Account addNew(@Valid @RequestBody Account account) {
		// TODO: implement validation
		if(accountRepository.existByUserName(account.getUserName())) {
			throw new AccountExistsException(account.getUserName());
		}
		account.setIsDeleted(false);
		return accountRepository.save(account);
	}

	@PutMapping("/account/{id}")
	public Account update(@Valid @RequestBody Account newAccount, @PathVariable int id) {
		// if found, perform update, else throw error
		return accountRepository.findById(id).map(account -> {
			// skip id, createdTime and isDeleted
			account.setPassword(newAccount.getPassword());
			account.setEmail(newAccount.getEmail());
			return accountRepository.save(account);
		}).orElseThrow(() -> new AccountNotFoundException(id));
	}

	@DeleteMapping("/account/{id}")
	public void delete(@PathVariable int id) {
		// if found, perform update, else throw error
		accountRepository.findById(id).map(account -> {
			account.setIsDeleted(true);
			return accountRepository.save(account);
		}).orElseThrow(() -> new AccountNotFoundException(id));
	}
	
	
}
