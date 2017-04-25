package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.repository.BaseCommunityRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 6:08 PM
 */
@Service("communityService")
public class CommunityServiceImpl extends BaseCommunityServiceImpl<Community> {

    @Autowired
    public CommunityServiceImpl(BaseCommunityRepository<Community> repository) {
        super(repository);
    }
}
