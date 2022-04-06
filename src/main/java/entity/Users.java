package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;
    @Basic
    @Column(name = "gender", nullable = false)
    private int gender;
    @Basic
    @Column(name = "address", nullable = false, length = 255)
    private String address;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 255)
    private String phoneNumber;
    @Basic
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    private String avatar;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @OneToMany(mappedBy = "usersByUserId")
    private List<Orders> ordersById;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Roles rolesByRoleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(List<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    public Roles getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(Roles rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleId=" + roleId +
                ", avatar='" + avatar + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}
