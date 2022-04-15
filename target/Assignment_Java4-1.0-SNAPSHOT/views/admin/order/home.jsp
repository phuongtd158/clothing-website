<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <h3 class="p-0"> Quản lý đơn hàng</h3>
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
                        <option selected disabled value="none">Bộ lọc</option>
                        <option value="1">312321</option>
                    </select>
                </div>
            </div>
            <div class="col-4">
                <i class='bx bx-box fs-5'></i>
                Tổng đơn hàng <span class='text-danger'>${count}</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table class="table">
        <tr class="table-dark">
            <th>Họ tên</th>
            <th>Email</th>
            <th>SĐT</th>
            <th>Địa chỉ</th>
            <th>Ghi chú</th>
            <th>Ngày đặt hàng</th>
            <th>Tổng tiền</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        <c:forEach items="${listOrder}" var="order">
            <form action="/Assignment_Java4/admin/order/update?orderId=${order.id}" method="post">
                <tr>
                    <td>${order.fullname}</td>
                    <td>${order.email}</td>
                    <td>${order.phoneNumber}</td>
                    <td>${order.address}</td>
                    <td>${order.note}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.totalMoney}</td>
                    <td>
                        <select name="status" class="form-control" id="status">
                            <option value="0" ${order.status == 0 ? 'selected' : ''}>Chờ xác nhận</option>
                            <option value="1" ${order.status == 1 ? 'selected' : ''}>Đã xác nhận</option>
                            <option value="2" ${order.status == 2 ? 'selected' : ''}>Đã hủy</option>
                        </select>
                    </td>
                    <td>
                        <button class="btn btn-dark">
                            Cập nhật
                        </button>
                        <a class="btn btn-warning" href="/Assignment_Java4/admin/order/detail?orderId=${order.id}">
                            Chi tiết
                        </a>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>


