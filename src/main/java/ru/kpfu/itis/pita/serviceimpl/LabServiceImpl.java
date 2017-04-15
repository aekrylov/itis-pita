package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.repository.LabRepository;
import ru.kpfu.itis.pita.service.LabService;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:56 PM
 */

@Service("labServiceImpl")
public class LabServiceImpl implements LabService {

    private LabRepository labRepository;
    private GroupRepository groupRepository;

    @Autowired
    public LabServiceImpl(LabRepository labRepository, GroupRepository groupRepository) {
        this.labRepository = labRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Lab create(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public boolean exists(String name) {
        return groupRepository.findByName(name) != null;
    }

    @Override
    public List<Lab> getAll() {
        return labRepository.findAll();
    }

}
