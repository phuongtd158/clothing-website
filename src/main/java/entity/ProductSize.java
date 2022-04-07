package entity;

import javax.persistence.*;

@Entity
@Table(name = "product_size", schema = "assignment_java4", catalog = "")
@IdClass(ProductSizePK.class)
public class ProductSize {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "size_id", nullable = false)
    private int sizeId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product productByProductId;
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Size sizeBySizeId;

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
