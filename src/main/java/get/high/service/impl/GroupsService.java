package get.high.service.impl;

import get.high.model.entity.Groups;
import get.high.repository.IGroupsRepository;
import get.high.service.IGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupsService implements IGroupsService {
    @Autowired
    private IGroupsRepository iGroupsRepository;

    @Override
    public Iterable<Groups> findAll() {
        return iGroupsRepository.findAll();
    }

    @Override
    public Optional<Groups> findById(Long id) {
        return iGroupsRepository.findById(id);
    }

    @Override
    public Groups save(Groups groups) {
        return iGroupsRepository.save(groups);
    }

    @Override
    public void remove(Long id) {
        iGroupsRepository.deleteById(id);
    }
}
