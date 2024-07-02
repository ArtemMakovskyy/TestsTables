package com.winestoreapp.service.cascade;

import com.winestoreapp.model.cascade.Human;
import com.winestoreapp.model.cascade.Human2;
import com.winestoreapp.model.cascade.PhoneNumber;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HumanService2 {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Human2 createPersonWithPhoneNumbers(String name) {
        Human2 human = new Human2();
        human.setName(name);
        entityManager.persist(human);
        return human;
    }

    @Transactional()
//    @Transactional(readOnly = true)
    public Human2 getPersonById(Long id) {
        return entityManager.find(Human2.class, id);
    }

    @Transactional
    public Human2 updatePersonName(Long id, String newName) {
        Human2 human = entityManager.find(Human2.class, id);
        if (human != null) {
            human.setName(newName);
        }
        return human;
    }

    @Transactional
    public void deletePerson(Long id) {
        Human2 human = entityManager.find(Human2.class, id);
        if (human != null) {
            entityManager.remove(human);
        }
    }

    @Transactional()
//    @Transactional(readOnly = true)
    public List<Human2> getAllPersons() {
        return entityManager.createQuery("SELECT h FROM Human2 h", Human2.class)
                .getResultList();
    }

//    @PostConstruct
//    public void intit(){
//        createPersonWithPhoneNumbers("ssdfg");
//    }
}