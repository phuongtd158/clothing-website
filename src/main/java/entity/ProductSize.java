package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_size", schema = "assignment_java4")
//@IdClass(ProductSizePK.class)
public class ProductSize implements Serializable {

    public ProductSize() {

    }

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productByProductId;
    @Id
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size sizeBySizeId;

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    public Size getSizeBySizeId() {
        return sizeBySizeId;
    }

    public void setSizeBySizeId(Size sizeBySizeId) {
        this.sizeBySizeId = sizeBySizeId;
    }
}
