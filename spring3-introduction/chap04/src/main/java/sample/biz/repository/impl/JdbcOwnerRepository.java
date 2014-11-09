package sample.biz.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import sample.biz.domain.Owner;
import sample.biz.domain.Pet;
import sample.biz.repository.OwnerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcOwnerRepository implements OwnerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Owner findOwnerByOwnerNameWithPet(String ownerName) {
        return jdbcTemplate.query(
                "SELECT * FROM OWNER O INNER JOIN PET P ON O.OWNER_NAME=P.OWNER_NAME WHERE O.OWNER_NAME=?"
                , new ResultSetExtractor<Owner>() {
                    @Override
                    public Owner extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        Owner owner = new Owner();
                        owner.setOwnerName(rs.getString("OWNER_NAME"));
                        do {
                            Pet pet = new Pet();
                            pet.setPetId(rs.getLong("PET_ID"));
                            pet.setPetName(rs.getString("PET_NAME"));
                            pet.setOwnerName(rs.getString("OWNER_NAME"));
                            pet.setPrice(rs.getInt("PRICE"));
                            pet.setBirthDate(rs.getDate("BIRTH_DATE"));
                            owner.getPetList().add(pet);
                        } while (rs.next());
                        return owner;
                    }
                }, ownerName);
    }
}
