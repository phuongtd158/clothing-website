<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container" style="margin-top: 110px; margin-bottom: 650px">
    <div class="row justify-content-center">
        <div class="text-center mb-3">
            <h3>Lịch sử mua hàng</h3>
        </div>
    </div>

    <c:if test="${empty listOrder}">
        <h4 class="text-center mt-5">Không có dữ liệu</h4>
        <div class="text-center">
            <a href="/Assignment_Java4/product">
                Quay lại
            </a>
        </div>
    </c:if>
    <c:if test="${!empty listOrder}">
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
                <th>Chi tiết</th>
            </tr>
            <c:forEach items="${listOrder}" var="order">
                <tr>
                    <td>${order.fullname}</td>
                    <td>${order.email}</td>
                    <td>${order.phoneNumber}</td>
                    <td>${order.address}</td>
                    <td>${order.note}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.totalMoney}</td>
                    <td>
                        <c:if test="${order.status == 0}">
                            Chờ xác nhận
                        </c:if>
                        <c:if test="${order.status == 1}">
                            Đã xác nhận
                        </c:if>
                        <c:if test="${order.status == 2 }">
                            Đã hủy
                        </c:if>
                    </td>
                    <td>
                        <a class="btn btn-dark" href="/Assignment_Java4/order-detail?orderId=${order.id}">
                            Xem chi tiết
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>