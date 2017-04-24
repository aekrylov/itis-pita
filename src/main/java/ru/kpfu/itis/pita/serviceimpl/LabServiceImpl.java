package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.repository.LabRepository;
import ru.kpfu.itis.pita.service.LabService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:56 PM
 */

@Service("labServiceImpl")
public class LabServiceImpl extends CommunityServiceImpl<Lab> implements LabService {

    @Autowired
    public LabServiceImpl(LabRepository labRepository) {
        super(labRepository);
    }

}
