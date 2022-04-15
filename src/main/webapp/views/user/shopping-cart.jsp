<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>

<!-- breadcrumb -->
<div class="container m-t-50">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="/Assignment_Java4/home" class="stext-109 cl8 hov-cl1 trans-04">
            Trang chủ
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4">
				Giỏ hàng
			</span>
    </div>
</div>


<!-- Shoping Cart -->

<div class="container">
    <div class="row">
        <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50 bg0 p-t-75 p-b-85">
            <c:if test="${!empty sessionScope.errorQuantityMess}">
                <div class="alert alert-danger w-full" style="margin-left: 25px;margin-bottom: 21px;">
                        ${sessionScope.errorQuantityMess}
                </div>
                <c:remove var="errorQuantityMess" scope="session"/>
            </c:if>
            <c:if test="${empty sessionScope.cart}">
                <h3 class="text-center"> Giỏ hàng trống</h3>
                <div class="text-center">
                    <a href="/Assignment_Java4/product">Quay lại</a>
                </div>
            </c:if>
            <c:if test="${!empty sessionScope.cart}">
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-1">Hình ảnh</th>
                                <th class="column-2">Tên sản phẩm</th>
                                <th class="column-3">Giá</th>
                                <th class="column-4">Số lượng</th>
                                <th class="column-5">Tổng tiền</th>
                                <th class="column-5">Hành động</th>
                            </tr>

                            <c:forEach items="${sessionScope.cart}" var="cart">
                                <tr class="table_row">
                                    <td class="column-1">
                                        <div class="how-itemcart1">
                                            <img src="/Assignment_Java4/upload/${cart.value.product.image}"
                                                 alt="IMG">
                                        </div>
                                    </td>
                                    <td class="column-2">${cart.value.product.productName}</td>
                                    <td class="column-3">${cart.value.product.price}</td>
                                    <form action="/Assignment_Java4/update-item" method="post">
                                        <input type="hidden" name="key" value="${cart.key}">
                                        <td class="column-4">
                                            <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                                </div>

                                                <input class="mtext-104 cl3 txt-center num-product" type="number"
                                                       name="quantity" value="${cart.value.quantity}">

                                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="column-5">
                                                ${cart.value.quantity * cart.value.product.price}
                                        </td>
                                        <td class="column-6">
                                            <button>
                                                <i class="zmdi zmdi-refresh text-dark" style="font-size: 25px"></i>
                                            </button>
                                            <a href="/Assignment_Java4/delete-item?key=${cart.key}">
                                                <i class="zmdi zmdi-delete text-dark"
                                                   style="font-size: 25px;margin-left: 45px;"></i>
                                            </a>
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>

                    <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm"
                         style="justify-content: end">
                        <a href="/Assignment_Java4/delete-all-item"
                           class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                            Xóa tất cả giỏ hàng
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
        <form class="bg0 p-t-75 p-b-85" action="/Assignment_Java4/checkout" method="post">
            <div>
                <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <c:if test="${!empty sessionScope.errorMess}">
                        <div>
                                <span class="text-danger">
                                        ${sessionScope.errorMess}
                                </span>
                        </div>
                        <c:remove var="errorMess" scope="session"/>
                    </c:if>
                    <div class="flex-w flex-t bor12 p-t-15 p-b-30">
                        <div class="size-208 w-full-ssm">
								<span class="stext-110 cl2">
									Thông tin
								</span>
                        </div>

                        <div>
                            <div class="p-t-15">
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="fullName"
                                           placeholder="Người nhận" value="${sessionScope.user.fullName}">
                                </div>
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="phoneNumber"
                                           placeholder="Số điện thoại" value="${sessionScope.user.phoneNumber}">
                                </div>
                                <div class="bor8 bg0 m-b-22">
                                    <input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="address"
                                           placeholder="Địa chỉ" value="${sessionScope.user.address}">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-27 p-b-33">
                        <div class="size-208">
								<span class="mtext-101 cl2">
									Tổng:
								</span>
                        </div>

                        <div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
                                    ${sessionScope.totalPrice}
                                </span>
                        </div>
                    </div>

                    <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
                    ${empty sessionScope.cart ? 'disabled' : ''}>
                        Thanh toán
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
