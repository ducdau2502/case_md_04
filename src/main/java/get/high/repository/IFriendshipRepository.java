package get.high.repository;

import get.high.model.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendshipRepository extends JpaRepository<Friendship, Long> {
}
