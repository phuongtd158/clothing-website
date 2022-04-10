<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <h3 class="p-0"> Quản lý màu sắc</h3>
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
                <a href="/Assignment_Java4/admin/color/create" class="text-dark">
                    <i class='bx bx-folder-plus fs-5'></i>
                    Thêm màu sắc
                </a>
            </div>
            <div class="col-4">
                <i class='bx bx-box fs-5'></i>
                Tổng màu sắc <span class='text-danger'>${count}</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th>STT</th>
            <th>Tên màu sắc</th>
            <th>Thời gian tạo</th>
            <th>Thời gian cập nhật</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listColor}" var="color" varStatus="counter">
            <tr class="align-middle">
                <td>${counter.count}</td>
                <td>${color.colorName}</td>
                <td>${color.createdAt}</td>
                <td>${color.updatedAt}</td>
                <td>
                    <a class="btn btn-warning" href="/Assignment_Java4/admin/color/edit?id=${color.id}">Sửa</a>
                    <a
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModal${color.id}"
                            class="btn btn-danger">Xóa</a>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal${color.id}" tabindex="-1"
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
                                    Bạn có muốn xóa màu sắc ${color.colorName} không ?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <a type="button" id="btnDelete" class="btn btn-primary"
                                       href="/Assignment_Java4/admin/color/delete?id=${color.id}">Xóa</a>
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
