package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.biz.domain.Pet;
import sample.biz.repository.OwnerRepository;
import sample.biz.repository.PetRepository;

import java.util.List;

public class ExecuteSqlMain {
    public static void main(String[] args) {
        ExecuteSqlMain executeSqlMain = new ExecuteSqlMain();
        executeSqlMain.execute();
    }

    private void execute() {
        BeanFactory ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PetRepository petRepository = ctx.getBean(PetRepository.class);
        System.out.println("----> INSERT");
        System.out.println("insert(): " + petRepository.insert());
        System.out.println("----> ドメインに変換しない");
        System.out.println("countAll(): " + petRepository.countAll());
        System.out.println("countAllByOwnerName(String): " + petRepository.countByOwnerName("Galford"));
        System.out.println("getNameById(Long): " + petRepository.getNameById(1L));
        System.out.println("getBirthDateById(Long): " + petRepository.getBirthDateById(1L));
        System.out.println("mapById(Long): " + petRepository.mapById(1L));
        System.out.println("listByOwnerName(String): " + petRepository.listByOwnerName("Galford"));
        System.out.println();
        System.out.println("----> UPDATE");
        System.out.println("update(): " + petRepository.update());
        System.out.println();
        System.out.println("----> ドメインに変換する");
        System.out.println("findById(Long): " + petRepository.findById(2L));
        System.out.println("findByOwnerName(String): " + petRepository.findByOwnerName("Galford"));
        System.out.println();
        OwnerRepository ownerRepository = ctx.getBean(OwnerRepository.class);
        System.out.println("----> JOIN");
        System.out.println("findOwnerByOwnerNameWithPet(String): " + ownerRepository.findOwnerByOwnerNameWithPet("Galford"));
        System.out.println("----> DELETE");
        System.out.println("delete(): " + petRepository.delete());
        System.out.println();
        System.out.println("----> NamedParameterJdbcTemplate");
        System.out.println("updateByNamedParameterJdbcTemplate(): " + petRepository.updateByNamedParameterJdbcTemplate());
        System.out.println(petRepository.findByOwnerName("Galford"));
        System.out.println();
        System.out.println("----> batchUpdate");
        List<Pet> pets = petRepository.findByOwnerName("Galford");
        int[] result = petRepository.batchUpdate(pets);
        for (int i : result) {
            System.out.println("batchUpdate(): " + i);
        }
        System.out.println(petRepository.findByOwnerName("Galford"));
    }
}
