package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductSizePK implements Serializable {
    @Column(name = "product_id", nullable = false)
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "size_id", nullable = false)
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSizePK that = (ProductSizePK) o;

        if (productId != that.productId) return false;
        if (sizeId != that.sizeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + sizeId;
        return result;
    }
}
