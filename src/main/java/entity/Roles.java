package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 15)
    private String name;
    @OneToMany(mappedBy = "rolesByRoleId")
    private List<Users> usersById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(List<Users> usersById) {
        this.usersById = usersById;
    }
}
