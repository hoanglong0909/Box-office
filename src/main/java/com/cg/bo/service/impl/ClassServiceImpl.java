package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Class;
import com.cg.bo.repository.ClassRepository;
import com.cg.bo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Class> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Class save(Class aClass) {
        return classRepository.save(aClass);
    }

    @Override
    public void remove(Long id) {
        classRepository.deleteById(id);
    }
}
