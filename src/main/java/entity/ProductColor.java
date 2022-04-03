package entity;

import javax.persistence.*;

@Entity
@Table(name = "product_color", schema = "assignment_java4", catalog = "")
@IdClass(ProductColorPK.class)
public class ProductColor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "color_id", nullable = false)
    private int colorId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product productByProductId;
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Color colorByColorId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    public Color getColorByColorId() {
        return colorByColorId;
    }

    public void setColorByColorId(Color colorByColorId) {
        this.colorByColorId = colorByColorId;
    }
}
