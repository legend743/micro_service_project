package userservices;

import Entity.UsersEntity;

import java.util.List;


public interface Service {

    //create user
     UsersEntity createUser(UsersEntity user);


     //fetchdewtails of users
    UsersEntity getUser(int Id);

    //get all users details at once;

    List<UsersEntity> getAllDetails();

}
