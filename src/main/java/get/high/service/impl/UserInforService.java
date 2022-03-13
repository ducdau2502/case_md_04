package get.high.service.impl;

import get.high.model.entity.UserInfor;
import get.high.repository.IUserInforRepository;
import get.high.service.IUserInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInforService implements IUserInforService {
    @Autowired
    private IUserInforRepository iUserInforRepository;

    @Override
    public Iterable<UserInfor> findAll() {
        return iUserInforRepository.findAll();
    }

    @Override
    public Optional<UserInfor> findById(Long id) {
        return iUserInforRepository.findById(id);
    }

    @Override
    public UserInfor save(UserInfor userInfor) {
        return iUserInforRepository.save(userInfor);
    }

    @Override
    public void remove(Long id) {
        iUserInforRepository.deleteById(id);
    }
}
