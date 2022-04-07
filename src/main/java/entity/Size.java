package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Size {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "size_name", nullable = false)
    private String sizeName;
    @OneToMany(mappedBy = "sizeBySizeId")
    private List<ProductSize> productSizesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public List<ProductSize> getProductSizesById() {
        return productSizesById;
    }

    public void setProductSizesById(List<ProductSize> productSizesById) {
        this.productSizesById = productSizesById;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", sizeName='" + sizeName + '\'' +
                '}';
    }
}
