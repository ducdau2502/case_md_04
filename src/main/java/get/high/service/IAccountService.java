package get.high.service;

import get.high.model.entity.Account;

public interface IAccountService extends IGeneralService<Account> {
    Iterable<Account> findAllByUsername(String username);
}
