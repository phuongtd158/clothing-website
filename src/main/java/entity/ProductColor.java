package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_color", schema = "assignment_java4")
public class ProductColor implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productByProductId;
    @ManyToOne
    @Id
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color colorByColorId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductColor that = (ProductColor) o;
        return Objects.equals(productByProductId, that.productByProductId) && Objects.equals(colorByColorId, that.colorByColorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productByProductId, colorByColorId);
    }
}
