package sample.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.biz.domain.Owner;
import sample.biz.domain.Pet;
import sample.biz.exception.BusinessException;
import sample.biz.repository.OwnerRepository;
import sample.biz.repository.PetRepository;
import sample.biz.service.RegisterService;

import java.util.List;

@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED,
        timeout = 10, readOnly = false,
        rollbackFor = BusinessException.class
)
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public Owner register(String ownerName, List<Pet> pets) {
        ownerRepository.insert(ownerName);
        int[] results = petRepository.batchInsert(pets);
        if (results.length == 0) {
            throw new BusinessException();
        }
        return ownerRepository.findOwnerByOwnerNameWithPet(ownerName);
    }
}
