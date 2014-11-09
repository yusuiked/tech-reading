package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.biz.domain.Owner;
import sample.biz.domain.Pet;
import sample.biz.service.RegisterService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxByFileMain {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TxByFileMain main = new TxByFileMain();
        main.execute(beanFactory);
    }

    private void execute(BeanFactory beanFactory) {
        RegisterService registerService = beanFactory.getBean(RegisterService.class);
        String ownerName = "Mike";
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pet pet = new Pet();
            pet.setPetName("Pet" + i);
            pet.setOwnerName(ownerName);
            pet.setPrice(i * 1500);
            pet.setBirthDate(new Date());
            pets.add(pet);
        }
        Owner owner = registerService.register(ownerName, pets);
        System.out.println(owner);
    }
}
