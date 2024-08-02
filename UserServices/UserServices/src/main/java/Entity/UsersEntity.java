package Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usersdetail")
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private  int userId;
    @Column(name="username")
    private String username;
    @Column(name="aboout")
    private String aboout;

    @Column(name="location")
    private String location;
    
    
 
}
