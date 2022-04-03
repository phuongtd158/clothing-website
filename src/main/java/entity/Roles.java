package entity;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Users> usersById;

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

    public Collection<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<Users> usersById) {
        this.usersById = usersById;
    }
}
