package get.high.model.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "userinfor")
public class UserInfor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String fullName;
    private String phoneNumber;
    private LocalDate birthday;
    private String address;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    @JoinTable(name = "listFriend", joinColumns = @JoinColumn(name = "userinfor_id_O1"), inverseJoinColumns = @JoinColumn(name = "userinfor_id_02"))
    private Set<UserInfor> userInfors;


    public UserInfor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<UserInfor> getUserInfors() {
        return userInfors;
    }

    public void setUserInfors(Set<UserInfor> userInfors) {
        this.userInfors = userInfors;
    }
}
