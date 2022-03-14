package get.high.repository;

import get.high.model.entity.UserInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserInfor, Long> {
}
