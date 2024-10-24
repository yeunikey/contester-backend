package me.yeunikey.contester.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<O, K, S extends JpaRepository<O, K>> {

    @Autowired
    private final S repository;

    public BaseService(S service) {
        this.repository = service;
    }

    public List<O> findAll() {
        return repository.findAll();
    }

    public O find(K id) {
        return repository.findById(id).orElse(null);
    }

    public O save(O object) {
        return repository.save(object);
    }

    public void delete(O object) {
        repository.delete(object);
    }

}
