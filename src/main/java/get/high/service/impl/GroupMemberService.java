package get.high.service.impl;

import get.high.model.entity.GroupMember;
import get.high.model.entity.UserInfo;
import get.high.repository.IGroupMemberRepository;
import get.high.service.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupMemberService implements IGroupMemberService {
    @Autowired
    private IGroupMemberRepository iGroupMemberRepository;

    @Override
    public Iterable<GroupMember> findAll() {
        return iGroupMemberRepository.findAll();
    }

    @Override
    public Optional<GroupMember> findById(Long id) {
        return iGroupMemberRepository.findById(id);
    }

    @Override
    public GroupMember save(GroupMember groupMember) {
        return iGroupMemberRepository.save(groupMember);
    }

    @Override
    public void remove(Long id) {
        iGroupMemberRepository.deleteById(id);
    }

    @Override
    public Optional<GroupMember> findByGroups_IdAndUserInfo_Id(Long groups_id, Long userinfo_id) {
        return iGroupMemberRepository.findByGroups_IdAndUserInfo_Id(groups_id, userinfo_id);
    }
}
