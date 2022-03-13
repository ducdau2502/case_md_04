package get.high.repository;

import get.high.model.entity.Groups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupsRepository extends CrudRepository<Groups, Long> {
}
