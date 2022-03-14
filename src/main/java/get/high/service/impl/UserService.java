package get.high.service.impl;

import get.high.model.entity.UserInfor;
import get.high.repository.IUserRepository;
import get.high.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Iterable<UserInfor> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<UserInfor> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public UserInfor save(UserInfor userInfor) {
        return iUserRepository.save(userInfor);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }
}
