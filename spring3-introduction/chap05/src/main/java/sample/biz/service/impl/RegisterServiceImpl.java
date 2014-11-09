package sample.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.biz.domain.Owner;
import sample.biz.domain.Pet;
import sample.biz.exception.BusinessException;
import sample.biz.repository.OwnerRepository;
import sample.biz.repository.PetRepository;
import sample.biz.service.RegisterService;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public Owner register(String ownerName, List<Pet> pets) {
        ownerRepository.insert(ownerName);
        petRepository.batchInsert(pets);
//        throw new BusinessException();
        return ownerRepository.findOwnerByOwnerNameWithPet(ownerName);
    }
}
