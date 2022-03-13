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

    private String contents;
    private String urlImg;
    private LocalDate dateCreate;
    private Integer status;


    @Transient
    private MultipartFile imgFile;

    @ManyToOne
    @JoinColumn(name = "userinfor_id")
    private UserInfor userInformation;

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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
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

    public UserInfor getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInfor userInformation) {
        this.userInformation = userInformation;
    }
}
