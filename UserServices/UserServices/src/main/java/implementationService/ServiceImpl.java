package implementationService;

import Entity.UsersEntity;

import repository.UserRepository;
import userservices.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    UserRepository userrepo;
    @Autowired
    private RestTemplate restTemplate;
    
    private Logger logger=LoggerFactory.getLogger(ServiceImpl.class);
    
    @Override
    public UsersEntity createUser(UsersEntity user) {
        return userrepo.save(user);
    }

    @Override
    public UsersEntity getUser(int Id) {
        UsersEntity userList= userrepo.getReferenceById((long) Id);
        //localhost:8082/hotelrating/getbyusers/3
       ArrayList forObject =restTemplate.getForObject("localhost:8082/hotelrating/getbyusers/3", ArrayList.class);
       logger.info("this is your rating from hotel"+forObject);
        return userList;
        
    }

    @Override
    public List<UsersEntity> getAllDetails() {
        return userrepo.findAll();
    }
}
