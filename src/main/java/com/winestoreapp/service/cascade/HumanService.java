package com.winestoreapp.service.cascade;

import com.winestoreapp.model.cascade.Human;
import com.winestoreapp.model.cascade.PhoneNumber;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HumanService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void verifyPerson(Long personId) {
        // Завантажуємо людину з бази даних
        Human human = entityManager.find(Human.class, personId);

        // Від'єднуємо людину від контексту персистенції
        entityManager.detach(human);

        // Верифікація інформації про людину
        verifyPersonInfo(human);

        // Подальша обробка
    }

    private void verifyPersonInfo(Human human) {
        // Логіка верифікації інформації про людину без змін у базі даних
        for (PhoneNumber phoneNumber : human.getPhoneNumbers()) {
            // Перевірка кожного номера телефону
        }
    }
    @Transactional
    public Human createPersonWithPhoneNumbers(String name, List<String> phoneNumbers) {
        Human human = new Human();
        human.setName(name);

        for (String number : phoneNumbers) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(number);
            human.addPhoneNumber(phoneNumber);

        }
        entityManager.persist(human);
        return human;
    }

    @PostConstruct
    public void init(){
        System.out.println("HumanService doesn't work");
//        createPersonWithPhoneNumbers("Ivan",List.of("05038266445"));
//        verifyPerson(1L);
    }
}