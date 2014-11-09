package sample.biz.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sample.biz.domain.Pet;
import sample.biz.repository.PetRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcPetRepository implements PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int countAll() {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PET");
    }

    @Override
    public int countByOwnerName(String ownerName) {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PET WHERE OWNER_NAME=?", ownerName);
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT PET_NAME FROM PET WHERE PET_ID=?", String.class, id);
    }

    @Override
    public Date getBirthDateById(Long id) {
        return jdbcTemplate.queryForObject("SELECT BIRTH_DATE FROM PET WHERE PET_ID=?", Date.class, id);
    }

    @Override
    public Map mapById(Long id) {
        return jdbcTemplate.queryForMap("SELECT * FROM PET WHERE PET_ID=?", id);
    }

    @Override
    public List<Map<String, Object>> listByOwnerName(String ownerName) {
        return jdbcTemplate.queryForList("SELECT * FROM PET WHERE OWNER_NAME=?", ownerName);
    }

    @Override
    public Pet findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM PET WHERE PET_ID=?", new RowMapper<Pet>() {
            @Override
            public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pet p = new Pet();
                p.setPetId(rs.getLong("PET_ID"));
                p.setPetName(rs.getString("PET_NAME"));
                p.setOwnerName(rs.getString("OWNER_NAME"));
                p.setPrice(rs.getInt("PRICE"));
                p.setBirthDate(rs.getDate("BIRTH_DATE"));
                return p;
            }
        }, id);
    }

    @Override
    public List<Pet> findByOwnerName(String ownerName) {
//        return jdbcTemplate.query("SELECT * FROM PET WHERE OWNER_NAME=?", new RowMapper<Pet>() {
//            @Override
//            public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Pet p = new Pet();
//                p.setPetId(rs.getLong("PET_ID"));
//                p.setPetName(rs.getString("PET_NAME"));
//                p.setOwnerName(rs.getString("OWNER_NAME"));
//                p.setPrice(rs.getInt("PRICE"));
//                p.setBirthDate(rs.getDate("BIRTH_DATE"));
//                return p;
//            }
//        }, ownerName);
        return jdbcTemplate.query("SELECT * FROM PET WHERE OWNER_NAME=?", new BeanPropertyRowMapper<>(Pet.class), ownerName);
    }

    @Override
    public int insert() {
        return jdbcTemplate.update("INSERT INTO PET (PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES (?, ?, ?, ?)"
        , "john", "Galford", 2300, new Date());
    }

    @Override
    public int update() {
        Date birthDate = Date.from(LocalDateTime.of(1999, 10, 10, 0, 0).toInstant(ZoneOffset.ofHours(9)));
        return jdbcTemplate.update("UPDATE PET SET PET_NAME=?, PRICE=?, BIRTH_DATE=? WHERE PET_ID=?"
        , "Pochi", 100, birthDate, 1L);
    }

    @Override
    public int delete() {
        return jdbcTemplate.update("DELETE FROM PET WHERE PET_ID=?", 1L);
    }

    @Override
    public int updateByNamedParameterJdbcTemplate() {
//        return namedParameterJdbcTemplate.update("INSERT INTO PET (PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES (:PET_NAME, :OWNER_NAME, :PRICE, :BIRTH_DATE)",
//                new MapSqlParameterSource()
//        .addValue("PET_NAME", "ハチ公")
//        .addValue("OWNER_NAME", "Galford")
//        .addValue("PRICE", 99999)
//        .addValue("BIRTH_DATE", Date.from(LocalDateTime.of(2000, 1, 1, 0, 0).toInstant(ZoneOffset.ofHours(9)))));
        Pet pet = new Pet();
        pet.setPetName("小太郎");
        pet.setOwnerName("Galford");
        pet.setPrice(25000);
        pet.setBirthDate(Date.from(LocalDateTime.of(2013, 6, 6, 0, 0).toInstant(ZoneOffset.ofHours(9))));
        return namedParameterJdbcTemplate.update("INSERT INTO PET (PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES (:petName, :ownerName, :price, :birthDate)",
                new BeanPropertySqlParameterSource(pet));
    }

    @Override
    public int[] batchUpdate(List<Pet> pets) {
        List<Object[]> args = new ArrayList<>();
        for (Pet pet : pets) {
            args.add(new Object[]{"強制名前変更", pet.getPetId()});
        }
        return jdbcTemplate.batchUpdate("UPDATE PET SET PET_NAME=? WHERE PET_ID=?", args);
    }

    @Override
    public int[] batchInsert(List<Pet> pets) {
        List<Object[]> args = new ArrayList<Object[]>();
        for (Pet pet : pets) {
            args.add(new Object[]{pet.getPetName(), pet.getOwnerName(), pet.getPrice(), pet.getBirthDate()});
        }
        return jdbcTemplate.batchUpdate("INSERT INTO PET (PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES (?, ?, ?, ?)", args);
    }
}
