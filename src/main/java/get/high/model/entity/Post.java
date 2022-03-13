package get.high.model.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String imgUrl;
    private LocalDate dateCreated;
    private Integer status;


    @Transient
    private MultipartFile imgFile;

    @ManyToOne
    @JoinColumn(name = "userinfor_id")
    private UserInfor userInfor;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Groups groups;

    @ManyToMany
    @JoinTable(name = "like_post", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "userinfor_id"))
    private Set<UserInfor> userInfors;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public UserInfor getUserInfor() {
        return userInfor;
    }

    public void setUserInfor(UserInfor userInfor) {
        this.userInfor = userInfor;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Set<UserInfor> getUserInfors() {
        return userInfors;
    }

    public void setUserInfors(Set<UserInfor> userInfors) {
        this.userInfors = userInfors;
    }
}
