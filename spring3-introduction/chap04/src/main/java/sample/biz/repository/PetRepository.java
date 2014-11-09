package sample.biz.repository;

import sample.biz.domain.Pet;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PetRepository {

    int countAll();

    int countByOwnerName(String ownerName);

    String getNameById(Long id);

    Date getBirthDateById(Long id);

    Map mapById(Long id);

    List<Map<String, Object>> listByOwnerName(String ownerName);

    Pet findById(Long id);

    List<Pet> findByOwnerName(String ownerName);

    int insert();

    int update();

    int delete();

    int updateByNamedParameterJdbcTemplate();

    int[] batchUpdate(List<Pet> pets);
}
