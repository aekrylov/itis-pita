package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.repository.InterestRepository;
import ru.kpfu.itis.pita.service.InterestService;

import java.util.List;

/**
 * Created by taa on 01.04.17.
 */
@Service("interestServiceImpl")
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;

    public InterestServiceImpl(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }


    @Override
    public List<Interest> getAll() {
        return interestRepository.findAll();
    }

    @Override
    public Interest save(Interest interest) {
        Interest duplicate = interestRepository.findByName(interest.getName());
        if(duplicate == null)
            return interestRepository.save(interest);
        return duplicate;
    }
}
