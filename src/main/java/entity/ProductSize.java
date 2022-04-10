package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_size", schema = "assignment_java4")
public class ProductSize implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSize)) return false;
        ProductSize that = (ProductSize) o;
        return Objects.equals(getProductByProductId(), that.getProductByProductId()) && Objects.equals(getSizeBySizeId(), that.getSizeBySizeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductByProductId(), getSizeBySizeId());
    }
}
