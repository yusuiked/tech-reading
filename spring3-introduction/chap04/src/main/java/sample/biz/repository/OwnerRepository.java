package sample.biz.repository;

import sample.biz.domain.Owner;

public interface OwnerRepository {
    Owner findOwnerByOwnerNameWithPet(String ownerName);
}
