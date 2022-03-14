package get.high.repository;

import get.high.model.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupMemberRepository extends JpaRepository<GroupMember, Long> {
}
