package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "category_id", nullable = false)
    private int categoryId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private double price;
    @Basic
    @Column(name = "image", nullable = true, length = 255)
    private String image;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name = "notes", nullable = true, length = 255)
    private String notes;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @OneToMany(mappedBy = "productByProductId")
    private List<OrderDetails> orderDetailsById;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false , insertable = false, updatable = false)
    private Categories categoriesByCategoryId;
    @OneToMany(mappedBy = "productByProductId")
    private List<ProductColor> productColorsById;
    @OneToMany(mappedBy = "productByProductId")
    private List<ProductSize> productSizesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderDetails> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(List<OrderDetails> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }

    public Categories getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(Categories categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    public List<ProductColor> getProductColorsById() {
        return productColorsById;
    }

    public void setProductColorsById(List<ProductColor> productColorsById) {
        this.productColorsById = productColorsById;
    }

    public List<ProductSize> getProductSizesById() {
        return productSizesById;
    }

    public void setProductSizesById(List<ProductSize> productSizesById) {
        this.productSizesById = productSizesById;
    }
}
