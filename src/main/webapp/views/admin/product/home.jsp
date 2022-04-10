<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                Tổng sản phẩm <span class='text-danger'>${count}</span>
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
            <th>Hình ảnh</th>
            <th>Thời gian tạo</th>
            <th>Thời gian cập nhật</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPro}" var="product" varStatus="counter">
            <tr class="align-middle">
                <td>${counter.count}</td>
                <td>${product.categoriesByCategoryId.name}</td>
                <td>${product.productName}</td>
                <td>
                    <select name="listColor" class="form-select">
                        <c:forEach items="${product.productColorsById}" var="productColor">
                            <option value="${productColor.colorByColorId.id}">${productColor.colorByColorId.colorName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="listSize" class="form-select">
                        <c:forEach items="${product.productSizesById}" var="productSize">
                            <option value="${productSize.sizeBySizeId.id}">${productSize.sizeBySizeId.sizeName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>${product.quantity}</td>
                <td><fmt:formatNumber value="${product.price}" pattern="#,###,###"/></td>
                <td style="width: 500px">
                    <div id="noteCell" style="width:100%; max-height:70px; overflow:auto;">
                            ${product.notes}
                    </div>
                </td>
                <td>
                    <img src="/Assignment_Java4/upload/${product.image}" width="70px" alt="">
                </td>
                <td>${product.createdAt}</td>
                <td>${product.updatedAt}</td>
                <td>
                    <a class="btn btn-warning" href="/Assignment_Java4/admin/product/edit?id=${product.id}">Sửa</a>
                    <a
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModal${product.id}"
                            class="btn btn-danger">Xóa</a>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal${product.id}" tabindex="-1"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Xóa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có muốn xóa sản phẩm ${product.productName} không ?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <a type="button" id="btnDelete" class="btn btn-primary"
                                       href="/Assignment_Java4/admin/product/delete?id=${product.id}">Xóa</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

