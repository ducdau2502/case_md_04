package get.high.service;

import get.high.model.entity.UserInfo;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    UserInfo findById(Long id);

    T save(T t);

    void remove(Long id);
}
