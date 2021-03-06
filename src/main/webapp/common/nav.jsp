<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <!-- Header desktop -->
    <div class="container-menu-desktop">
        <!-- Topbar -->
        <div class="top-bar navbar navbar-expand-lg">
            <div class="content-topbar flex-sb-m h-full container">
                <div class="left-top-bar">
                    Free shipping for standard order over $100
                </div>

                <c:choose>
                    <c:when test="${!empty sessionScope.user}">
                        <div class="right-top-bar flex-w h-full">
                            <div class="flex-c-m trans-04 p-lr-25">
                                <a class="flex-c-m trans-04 p-lr-25" href="#">
                                        ${sessionScope.user.fullName}
                                </a>
                                <a href="/Assignment_Java4/history"
                                   class="flex-c-m trans-04 p-lr-25">
                                    Lịch sử mua hàng
                                </a>
                                <a class="flex-c-m trans-04 p-lr-25" href="/Assignment_Java4/logout">
                                    Đăng xuất
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="right-top-bar flex-w h-full">
                            <div class="flex-c-m trans-04 p-lr-25">
                                <a class="flex-c-m trans-04 p-lr-25" href="/Assignment_Java4/login">
                                    Đăng nhập
                                </a>
                                <a class="flex-c-m trans-04 p-lr-25" href="#">
                                    Đăng ký
                                </a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="wrap-menu-desktop">
            <nav class="limiter-menu-desktop container">

                <!-- Logo desktop -->
                <a href="/Assignment_Java4/home" class="logo">
                    <img src="/Assignment_Java4/assets/images/icons/logo-01.png" alt="IMG-LOGO">
                </a>

                <!-- Menu desktop -->
                <div class="menu-desktop">
                    <ul class="main-menu">
                        <li>
                            <a href="/Assignment_Java4/home">Trang chủ</a>

                        </li>

                        <li>
                            <a href="/Assignment_Java4/product">Sản phẩm</a>
                        </li>

                        <li>
                            <a href="/Assignment_Java4/about">Giới thiệu</a>
                        </li>

                        <li>
                            <a href="/Assignment_Java4/contact">Liên hệ</a>
                        </li>
                    </ul>
                </div>

                <!-- Icon header -->
                <div class="wrap-icon-header flex-w flex-r-m">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
                        <i class="zmdi zmdi-search"></i>
                    </div>

                    <a href="/Assignment_Java4/shopping-cart"
                       class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
                       data-notify="${!empty sessionScope.count ? sessionScope.count : 0}">
                        <i class="zmdi zmdi-shopping-cart"></i>
                    </a>
                </div>
            </nav>
        </div>
    </div>

    <!-- Header Mobile -->
    <div class="wrap-header-mobile">
        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="/Assignment_Java4/home"><img src="/Assignment_Java4/assets/images/icons/logo-01.png"
                                                  alt="IMG-LOGO"></a>
        </div>

        <!-- Icon header -->
        <div class="wrap-icon-header flex-w flex-r-m m-r-15">
            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
                <i class="zmdi zmdi-search"></i>
            </div>

            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart"
                 data-notify="2">
                <i class="zmdi zmdi-shopping-cart"></i>
            </div>

            <a href="#" class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
               data-notify="0">
                <i class="zmdi zmdi-favorite-outline"></i>
            </a>
        </div>

        <!-- Button show menu -->
        <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
        </div>
    </div>


    <!-- Menu Mobile -->
    <div class="menu-mobile">
        <ul class="topbar-mobile">
            <li>
                <div class="left-top-bar">
                    Free shipping for standard order over $100
                </div>
            </li>

            <li>
                <div class="right-top-bar flex-w h-full">
                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        Help & FAQs
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        My Account
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        EN
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        USD
                    </a>
                </div>
            </li>
        </ul>

        <ul class="main-menu-m">
            <li>
                <a href="/Assignment_Java4/home">Trang chủ</a>
                <span class="arrow-main-menu-m">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</span>
            </li>

            <li>
                <a href="/Assignment_Java4/product">Sản phẩm</a>
            </li>

            <li>
                <a href="/Assignment_Java4/about">Giới thiệu</a>
            </li>

            <li>
                <a href="/Assignment_Java4/contact">Liên hệ</a>
            </li>
        </ul>
    </div>

    <!-- Modal Search -->
    <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
        <div class="container-search-header">
            <button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
                <img src="/Assignment_Java4/assets/images/icons/icon-close2.png" alt="CLOSE">
            </button>

            <form class="wrap-search-header flex-w p-l-15">
                <button class="flex-c-m trans-04">
                    <i class="zmdi zmdi-search"></i>
                </button>
                <input class="plh3" type="text" name="search" placeholder="Search...">
            </form>
        </div>
    </div>
</header>

<!-- Cart -->
<div class="wrap-header-cart js-panel-cart">
    <div class="s-full js-hide-cart"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Giỏ hàng
				</span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full">
                <c:if test="${empty sessionScope.cart}">
                    <p class="text-center">Giỏ hàng trống</p>
                </c:if>
                <c:if test="${!empty sessionScope.cart}">
                    <c:forEach items="${sessionScope.cart}" var="cart">
                        <li class="header-cart-item flex-w flex-t m-b-12">
                            <div class="header-cart-item-img">
                                <img src="/Assignment_Java4/upload/${cart.value.product.image}" alt="IMG">
                            </div>

                            <div class="header-cart-item-txt p-t-8">
                                <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                        ${cart.value.product.productName}
                                </a>

                                <span class="header-cart-item-info">
								 ${cart.value.quantity} x  ${cart.value.product.price}
							</span>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>

            <div class="w-full">
                <div class="header-cart-total w-full p-tb-40">
                    Tổng: ${sessionScope.totalPrice}
                </div>

                <div class="header-cart-buttons flex-w w-full">
                    <a href="/Assignment_Java4/shopping-cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                        Xem giỏ hàng
                    </a>

                    <a href="/Assignment_Java4/shopping-cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                        Thanh toán
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/Assignment_Java4/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<script>

    // $('#view').click(function () {
    //     $.ajax({
    //         url: 'viewcart',
    //         method: 'GET',
    //     })
    // })
</script>