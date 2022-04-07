package entity;

import javax.persistence.*;
import java.io.Serializable;

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

}
