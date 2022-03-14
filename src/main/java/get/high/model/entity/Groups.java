package get.high.model.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="grouplist" )
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany
    @JoinTable(name = "members",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "userinfor_id"))
    private Set<UserInfor> userInfors;

    public Groups() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserInfor> getUserInfors() {
        return userInfors;
    }

    public void setUserInfors(Set<UserInfor> userInfors) {
        this.userInfors = userInfors;
    }
}
