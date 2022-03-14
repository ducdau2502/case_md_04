package get.high.repository;

import get.high.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Iterable<Account> findAllByUsernameContaining(String username);
}