<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #noteCell::-webkit-scrollbar {
        display: none; /* for Chrome, Safari, and Opera */
    }
</style>
<div class="row">
    <h3 class="p-0"> Quản lý sản phẩm</h3>
</div>
<div class="row mt-2 mb-4">
    <div class="col-7 p-0">
        <div class="header__search">
            <input type="search" placeholder="Tìm kiếm" class="header__input">
            <i class='bx bx-search header__icon mt-1'></i>
        </div>
        <div class="header__toggle">
            <i class='bx bx-menu' id="header-toggle"></i>
        </div>
    </div>
    <div class="col-5 justify-content-end">
        <div class="row align-items-center">
            <div class="col-5">
                <div class="form-group">
                    <select class="form-select" name="filter-categories" id="filter-categories">
                        <option selected disabled>Bộ lọc</option>
                        <option value="">312321</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <a href="/Assignment_Java4/admin/product/create" class="text-dark">
                    <i class='bx bx-folder-plus fs-5'></i>
                    Thêm sản phẩm
                </a>
            </div>
            <div class="col-4">
                <i class='bx bx-box fs-5'></i>
                Tổng sản phẩm <span class='text-danger'>50</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th>STT</th>
            <th>Danh mục</th>
            <th>Tên sản phẩm</th>
            <th>Màu sắc</th>
            <th>Size</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Mô tả</th>
            <th>Thời gian tạo</th>
            <th>Thời gian cập nhật</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPro}" var="product" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${product.categoriesByCategoryId.name}</td>
                <td>${product.productName}</td>
                <td>
                    <select name="listColor">
                        <c:forEach items="${product.productColorsById}" var="productColor">
                            <option value="${productColor.colorByColorId.id}">${productColor.colorByColorId.colorName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="listSize">
                        <c:forEach items="${product.productSizesById}" var="productSize">
                            <option value="${productSize.sizeBySizeId.id}">${productSize.sizeBySizeId.sizeName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>${product.quantity}</td>
                <td>${product.price}</td>
                <td style="width: 500px">
                    <div id="noteCell" style="width:100%; max-height:70px; overflow:auto;">
                            ${product.notes}
                    </div>
                </td>
                <td>${product.createdAt}</td>
                <td>${product.updatedAt}</td>
                <td>
                    <a class="btn btn-warning" href="/Assignment_Java4/admin/product/edit">Sửa</a>
                    <a class="btn btn-danger" href="/Assignment_Java4/admin/product/delete">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
