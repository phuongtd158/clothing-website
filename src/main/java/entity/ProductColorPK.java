package entity;

import java.io.Serializable;

public class ProductColorPK implements Serializable {
    private int productId;
    private int colorId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductColorPK that = (ProductColorPK) o;

        if (productId != that.productId) return false;
        if (colorId != that.colorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + colorId;
        return result;
    }
}
