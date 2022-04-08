<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/04/2022
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row ">
    <h3 class="mt-2 mb-4 text-center">Thêm sản phẩm</h3>
</div>
<div class="container">
    <form method="post" action="/Assignment_Java4/admin/product/store" enctype="multipart/form-data">
        <div class="row">

            <div class="col-3">
                <img class="img-fluid" id="imgPreview" src="" alt=""
                     style="border:1px solid #ccc;">
                <label>Hình ảnh</label>
                <input type="file" id="photo" class="form-control" name="image-product">

            </div>
            <div class="col-9">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input type="text" class="form-control" name="product-name" id="product-name">
                </div>
                <div class="form-group mt-3">
                    <label>Danh mục</label>
                    <select class="form-select" name="category">
                        <c:forEach items="${listCategories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <div class="form-group">
                            <label>Màu sắc</label>
                            <select class="js-example-basic-multiple form-select" name="product-color[]" multiple="multiple">
                                <c:forEach items="${listColor}" var="color">
                                    <option value="${color.id}">${color.colorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label>Kích thước</label>
                            <select class="js-example-basic-multiple form-select" name="product-size[]" multiple="multiple">
                                <c:forEach  items="${listSize}" var="size">
                                    <option value="${size.id}">${size.sizeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="product-quantity">Số lượng</label>
                            <input type="number" class="form-control" name="product-quantity" id="product-quantity">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="product-price">Giá</label>
                            <input type="number" class="form-control" name="product-price" id="product-price">
                        </div>
                    </div>
                </div>
                <div class="form-group mt-3">
                    <label for="product-note">Mô tả</label>
                    <textarea class="form-control" name="product-note" id="product-note" cols="30" rows="10"></textarea>
                </div>
                <button type="submit" class="btn btn-primary mt-3" style="float:right">Thêm</button>
            </div>
        </div>
    </form>

</div>
<script>

    $(document).ready(()=>{
        $('#photo').change(function(){
            const file = this.files[0];
            if (file){
                let reader = new FileReader();
                reader.onload = function(event){
                    console.log(event.target.result);
                    $('#imgPreview').attr('src', event.target.result);
                }
                reader.readAsDataURL(file);
            }
        });
    });
</script>

