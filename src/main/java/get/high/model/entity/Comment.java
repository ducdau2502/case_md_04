package get.high.model.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userinfor_id")
    private UserInfor userInfor;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    @ManyToMany
    @JoinTable(name = "like_comment",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "userinfor_id"))
    private Set<UserInfor> userInfors;


    public Set<UserInfor> getUserInfors() {
        return userInfors;
    }

    public void setUserInfors(Set<UserInfor> userInfors) {
        this.userInfors = userInfors;
    }

    public Comment() {
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

    public UserInfor getUserInfor() {
        return userInfor;
    }

    public void setUserInfor(UserInfor userInfor) {
        this.userInfor = userInfor;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
