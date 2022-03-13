package get.high.service.impl;

import get.high.model.entity.Account;
import get.high.repository.IAccountRepository;
import get.high.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Iterable<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        iAccountRepository.deleteById(id);
    }
}
