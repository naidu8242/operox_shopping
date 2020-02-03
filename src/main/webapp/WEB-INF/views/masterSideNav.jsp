<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<aside> 
  <div class="side-bar">
      <div class="side-nav">
        <ul class="list-unstyled">
          
          <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER','ROLE_CASHIER')"> 
	          <li class="${sessionData.nav == 'Dashboard' ? 'side-nav-active' : ''}">
	          <a href="${operoxUrl}/dashboard">
	          	<span title="Dashboard"><i class="fa fa-dashboard"></i></span>
	          		<p>Dashboard</p>
	          </a>
	          </li>
          </sec:authorize>
          
         <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATION_MANAGER','ROLE_QC_MANAGER','ROLE_PRODUCTION_MANAGER')"> 
          <li class="${sessionData.nav == 'Production' ? 'side-nav-active' : ''}">
          <a href="javascript:void(0)" title="candiate">
          <span title="Setup"><i class="glyphicon glyphicon-shopping-cart"></i></span><p>Production</p>
          <i class="fa fa-fw fa-caret-right"></i>
          </a>
            <ul class="list-unstyled">
              <li class="${sessionData.subNav == 'RawMaterial' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/rawMaterials">Raw Materials</a></li>
              <li class="${sessionData.subNav == 'WorkOrder' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/workOrder">Work Order</a></li>
              <li class="${sessionData.subNav == 'CheckList' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/qaCheckList">QA/QC CheckList</a></li>
            </ul>
          </li>
        </sec:authorize> 
          
          
          <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATION_MANAGER','ROLE_STORE_MANAGER','ROLE_PRODUCTION_MANAGER','ROLE_CASHIER')">
          <li class="${sessionData.nav == 'Customers' ? 'side-nav-active' : ''}">
          <a href="javascript:void(0)" title="candiate">
          <span title="Setup"><i class="fa fa-users"></i></span><p>Customer</p>
          <i class="fa fa-fw fa-caret-right"></i>
          </a>
            <ul class="list-unstyled">
              <li class="${sessionData.subNav == 'Customer' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/customers">Customers</a></li>
              <li class="${sessionData.subNav == 'Ticket' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/ticket">Ticket</a></li>
            </ul>
          </li>
          </sec:authorize>
          
          <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATION_MANAGER','ROLE_STORE_MANAGER','ROLE_PRODUCTION_MANAGER')">
          <li class="${sessionData.nav == 'Suppliers' ? 'side-nav-active' : ''}"><a href="<c:url value='/suppliers'/>"><span title="Suppliers"><i class="glyphicon glyphicon-fullscreen"></i></span><p>Suppliers</p></a></li>
          </sec:authorize>
          
          <sec:authorize access="!hasAnyRole('ROLE_QC_MANAGER','ROLE_CASHIER')"> 
	          <li class="${sessionData.nav == 'Setup' ? 'side-nav-active' : ''}">
	          <a href="javascript:void(0)" title="candiate">
	          <span title="Setup"><i class="glyphicon glyphicon-cog"></i></span><p>Setup</p>
	          <i class="fa fa-fw fa-caret-right"></i>
	          </a>
	            <ul class="list-unstyled">
	             
	             
	              <li class="${sessionData.subNav == 'User' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/users">User</a></li>
	              <li class="${sessionData.subNav == 'Shift' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/shift">Shift</a></li>
	              <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER')">
		              <li class="${sessionData.subNav == 'Category' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/category">Category</a></li>
		              <li class="${sessionData.subNav == 'Product' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/product">Product</a></li>
		              <li class="${sessionData.subNav == 'Store' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/store">Location</a></li>
		              <li class="${sessionData.subNav == 'Brands' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/brandHome">Brands</a></li>
		              <li class="${sessionData.subNav == 'MeasuringUnit' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/measuringUnit">Measuring Unit</a></li>
		              <li class="${sessionData.subNav == 'Counter' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/counterList">Counter</a></li>
		              <li class="${sessionData.subNav == 'Offers' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/offers">Offers</a></li>
	                  <li class="${sessionData.subNav == 'Tax' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/tax">Tax</a></li>
	                  <li class="${sessionData.subNav == 'Currency' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/currency">Currency</a></li>
	              </sec:authorize>
	              
	              <li class="${sessionData.subNav == 'Depatment' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/departmentList">Department</a></li>
	              <li class="${sessionData.subNav == 'Holiday' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/holiday">Holiday</a></li>
	              <li class="${sessionData.subNav == 'Leave' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/leaves">Leave</a></li>
	              <li class="${sessionData.subNav == 'PayRoll' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/payroll">Payroll</a></li>
	            </ul>
	          </li>
          </sec:authorize>
          
         <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER','ROLE_CASHIER')"> 
	          <li class="${sessionData.nav == 'Stock' ? 'side-nav-active' : ''}">
	          <a href="javascript:void(0)" title="candiate">
	          <span title="Setup"><i class="fa fa-cubes"></i></span><p>Stock</p>
	          <i class="fa fa-fw fa-caret-right"></i>
	          </a>
	            <ul class="list-unstyled">
	              <li class="${sessionData.subNav == 'PurchaseOrder' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/purchaseorderDashboard">Purchase Order</a></li>
	              <li class="${sessionData.subNav == 'ReceivedItem' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/receivedItemDashboard">Received Stock</a></li>
	              <li class="${sessionData.subNav == 'ReturnStock' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/returnStockDashboard">Return Stock</a></li>
	              <li class="${sessionData.subNav == 'OpeningStock' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/openingStockDashboard">Opening Stock</a></li>
	              <li class="${sessionData.subNav == 'TransferStock' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/transferStockDashboard">Transfer Stock</a></li> 	
	            </ul>
	          </li>
          </sec:authorize>
          <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER','ROLE_QC_MANAGER')"> 
          <li class="${sessionData.nav == 'Billing' ? 'side-nav-active' : ''}"><a href="${operoxUrl}/billing" ><span title="Billing"><i class="fa fa-money"></i></span><p>Billing</p></a></li>
          </sec:authorize>
        </ul>
      </div>
  </div>
</aside>