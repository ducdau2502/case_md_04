package get.high.repository;

import get.high.model.entity.UserInfor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInforRepository extends CrudRepository<UserInfor, Long> {
}
