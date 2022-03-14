package get.high.service.impl;

import get.high.model.entity.Friendship;
import get.high.model.entity.UserInfo;
import get.high.repository.IFriendshipRepository;
import get.high.service.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService implements IFriendshipService {
    @Autowired
    private IFriendshipRepository iFriendshipRepository;

    @Override
    public Iterable<Friendship> findAll() {
        return iFriendshipRepository.findAll();
    }

    @Override
    public UserInfo findById(Long id) {
        return iFriendshipRepository.findById(id);
    }

    @Override
    public Friendship save(Friendship friendship) {
        return iFriendshipRepository.save(friendship);
    }

    @Override
    public void remove(Long id) {
        iFriendshipRepository.deleteById(id);
    }
}
