package sample.biz.service;

import sample.biz.domain.Owner;
import sample.biz.domain.Pet;

import java.util.List;

public interface RegisterService {

    Owner register(String ownerName, List<Pet> pets);
}
