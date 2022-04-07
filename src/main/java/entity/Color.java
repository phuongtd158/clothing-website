package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Color {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "color_name", nullable = false, length = 255)
    private String colorName;
    @OneToMany(mappedBy = "colorByColorId")
    private List<ProductColor> productColorsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public List<ProductColor> getProductColorsById() {
        return productColorsById;
    }

    public void setProductColorsById(List<ProductColor> productColorsById) {
        this.productColorsById = productColorsById;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
