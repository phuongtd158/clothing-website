<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container" style="margin-top: 110px; margin-bottom: 400px">
    <div class="row justify-content-center">
        <div class="text-center mb-3">
            <h3>Chi tiết lịch sử</h3>
        </div>
    </div>
    <table class="table">
        <tr class="table-dark">
            <th>STT</th>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Thành tiền</th>
        </tr>
        <c:forEach items="${listOrderDetails}" var="orderDetail" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${orderDetail.productByProductId.productName}</td>
                <td>${orderDetail.quantity}</td>
                <td>${orderDetail.productByProductId.price}</td>
                <td>${orderDetail.unitPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>