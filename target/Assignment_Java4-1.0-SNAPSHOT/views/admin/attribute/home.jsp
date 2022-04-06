<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/04/2022
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <h3 class="p-0"> Quản lý thuộc tính</h3>
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
                <a href="#" class="text-dark">
                    <i class='bx bx-folder-plus fs-5'></i>
                    Thêm sản phẩm
                </a>
            </div>
            <div class="col-4">
                <i class='bx bx-box fs-5'></i>
                Tổng danh mục <span class='text-danger'>50</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table class="table">
        <thead class="table-dark">
        <th>STT</th>
        <th>Tên danh mục</th>
        <th>Thời gian tạo</th>
        <th>Thời gian cập nhật</th>
        <th>Hành động</th>
        </thead>
        <tbody>
        <td>1</td>
        <td>1</td>
        <td>1</td>
        <td>1</td>
        <td>
            <a class="btn btn-warning" href="#">Sửa</a>
            <a class="btn btn-danger" href="#">Xóa</a>
        </td>

        </tbody>
    </table>
</div>