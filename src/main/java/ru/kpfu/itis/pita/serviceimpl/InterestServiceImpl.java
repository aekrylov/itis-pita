package ru.kpfu.itis.pita.serviceimpl;

import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.repository.InterestRepository;
import ru.kpfu.itis.pita.service.InterestService;

import java.util.List;

/**
 * Created by taa on 01.04.17.
 */
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;

    public InterestServiceImpl(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }


    @Override
    public List<Interest> getAll() {
        return interestRepository.findAll();
    }
}
