package get.high.service;

import get.high.model.entity.GroupMember;

import java.util.Optional;

public interface IGroupMemberService extends IGeneralService<GroupMember> {
    Optional<GroupMember> findByGroups_IdAndUserInfo_Id(Long groups_id, Long userinfo_id);
}
