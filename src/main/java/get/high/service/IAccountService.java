package get.high.service;

import get.high.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountService extends JpaRepository<Account,Long> {
    Optional<Account> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String name);
}
