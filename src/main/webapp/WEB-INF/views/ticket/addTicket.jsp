<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Ticket</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Add Ticket</div>
  </section>
  
  <!-- Content Area inner -->
<form:form id="ticket_form" name="ticket_form" method="post">
  <div class="content-area clearfix">
  
  <div class="div33">
       <label class="labl">Issue Date</label>
       <span>
       <input type="text" placeholder="DD/MM/YYYY" class="datefild" id="issueDate" value="${ticket.issueDateStr}" name="issueDate" field-name="Issue Date" data-validation-optional="true">
       </span>
    </div> 
  
  <div class="div33">
       <label class="labl">Due Date</label>
       <span>
       <input type="text" placeholder="DD/MM/YYYY" class="datefild" id="dueDate" value="${ticket.dueDateStr}" name="dueDate" field-name="Due Date" data-validation-optional="true">
       </span>
    </div> 
    <div class="div33">
       <label class="labl">Subject <b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="subject" value="${ticket.subject}" name="subject" id="subject" field-name="subject" data-validation="required validate_Space" >
       </span>
    </div>
     <input type="hidden" id="id" name="id" value="${ticket.id}">
     
     <div class="div33">
       <label class="labl">Source Type<b class="text-danger">*</b></label>
       <span>
       <select id="sourceType" name="sourceType" field-name="Source Type"  data-validation="required">
          <option selected disabled>--Select Source Type --</option>
          <option value="phone" ${ticket.sourceType  =='phone' ? 'selected' : ''}>Phone</option>
          <option value="email" ${ticket.sourceType  =='email' ? 'selected' : ''}>Email</option>
         </select>
       </span>
    </div>
     <div class="div33" >
       <label class="labl">Store<b class="text-danger">*</b></label>
       <span>
       <select class="chosen-select"  id="store" name="store" field-name="Store"  data-validation="required">
			<option value="">-- Select Store --</option>
				<c:forEach var="option" items="${storeList}">
					<option value="${option.id}" ${option.id  == ticket.store.id ? 'selected' : ''}>${option.storeName}</option>
				</c:forEach>
			</select>
    </span>
    </div>
    
     <div class="div33" >
       <label class="labl">Department</label>
       <span>
    <select class="chosen-select"  id="department" name="department" field-name="Department" >
			<option value="">-- Select Department --</option>
				<c:forEach var="option" items="${departmentList}">
					<option value="${option.id}" ${option.id  == ticket.department.id ? 'selected' : ''}>${option.departmentName}</option>
				</c:forEach>
			</select>
			</span>
			</div>
     
     <div class="div33">
        <label class="labl">Ticket Status <b class="text-danger">*</b></label>
        <span>
         <select id="ticketStatus" name="ticketStatus" field-name="Ticket Status"  data-validation="required">
         <option selected disabled>-- Select Status--</option>
         <option value="New" ${ticket.ticketStatus  =='New' ? 'selected' : ''}>New</option>
         <option value="onHold" ${ticket.ticketStatus  =='onHold' ? 'selected' : ''}>On Hold</option>
         <option value="In-Progress" ${ticket.ticketStatus  =='In-Progress' ? 'selected' : ''}>In-Progress</option>
         <option value="Re-Open" ${ticket.ticketStatus  =='Re-Open' ? 'selected' : ''}>Re-Open</option>
         <option value="Re-Open" ${ticket.ticketStatus  =='Re-Open' ? 'selected' : ''} >Closed</option>
       </select>
        </span>
      </div>
     
     <div class="div33">
        <label class="labl">Priority</label>
        <span>
         <select id="priority" name="priority">
         <option selected disabled>-- Select Priority--</option>
         <option value="low" ${ticket.priority  =='low' ? 'selected' : ''}>Low</option>
         <option value="medium" ${ticket.priority  =='medium' ? 'selected' : ''}>Medium</option>
         <option value="high" ${ticket.priority  =='high' ? 'selected' : ''}>High</option>
       </select>
        </span>
      </div>
        <div class="div33">
        <label class="labl">Customer</label>
        <span>
         <select class="chosen-select"  id="customer" name="customer" field-name="Customer" >
			<option value="">-- Select Customer --</option>
				<c:forEach var="option" items="${customersList}">
					<option value="${option.id}" ${option.id  == ticket.customer.id ? 'selected' : ''}>${option.customerName}</option> 
				</c:forEach>
			</select>
			</span>
			</div>
   
   
   <div class="div33" id="uploadFileGroup">
      <label class="labl">Attachment</label>
      <!-- upload file -->
      <span class="custom-upload">
           <input type="file" name="uploadBtn" id="uploadBtn" >
           <div class="fake-file">
               <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document" placeholder="Upload File..." value="${ticket.fileName}">
           </div>
       </span>
      <!-- upload file -->
   </div>
    <div class="div33">
        <label class="labl">Ticket Created by</label>
       <span>
       <select class="chosen-select"  id="createUser" name="createUser" field-name="Create User"  >
					<option value="">-- Select Create User--</option>
						<c:forEach var="option" items="${userList}">
							<option value="${option.id}" ${option.id  == ticket.ticketCreatedBy.id ? 'selected' : ''}>${option.firstName}${option.lastName}</option>
						</c:forEach>
			</select>
       <%-- <input type="text" placeholder="DD/MM/YYYY" class="datefild" id="createUserDate" value="${ticket.ticketCreatedDateStr}" name="createUserDate" field-name="CreateUser Date" data-validation-optional="true"> --%>
		</span>
		</div>
	 <div class="div33">
        <label class="labl">Assign to <b class="text-danger">*</b></label>
        <span>	
			<select class="chosen-select"  id="assignUser" name="assignUser" field-name="Assign User" data-validation="required" >
					<option value="">-- Select Assign User--</option>
						<c:forEach var="option" items="${userList}">
							<option value="${option.id}" ${option.id  == ticket.user.id ? 'selected' : ''}>${option.firstName}${option.lastName}</option>
						</c:forEach>
			</select>
		</span>
		</div>
	
     <div class="div50" >
       <label class="labl">Message <b class="text-danger">*</b></label>
       <span>
       <textarea placeholder="message" id="message" name="message" field-name="Message" rows="1" data-validation="required">${ticket.message}</textarea>
       </span>
    </div>         
    
    <div class="clearfix"></div>
   </div>
       <!-- Content Area inner -->
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
        <c:if test="${empty ticket.id}">
          <input type="button" value="Add"  id="save_ticket" onclick="saveTicket();" class="btn btn-primary">
          </c:if>
          <c:if test="${!empty ticket.id}">
           <input type="button" value="Update"  id="save_ticket" onclick="saveTicket();" class="btn btn-primary">
          </c:if>
          <input type="button" value="Cancel" class="btn btn-default ml10" onClick="cancelTicket();" >
        </div>
      </div>
    </div>
   </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelTicket(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/ticket");
}
</script>

<script type="text/javascript">
function saveTicket() {
	
 	var validationSettings = {
		errorMessagePosition : 'element',
		borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd'
	}; 
 	if ($('#ticket_form').validate(false, validationSettings)) { 
 		var frm = $('#ticket_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var form = $('#ticket_form')[0];
  		var formData = new FormData(form); 
		$("#save_ticket").attr("disabled", "disabled");
		$("#save_ticket").addClass('button').val('Processing..');
		var operoxUrl = '${operoxUrl}';
		var jsonData = encodeURIComponent(con);
		/* $("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;'); */
 		$.ajax({
 			type : "POST",
 			data: formData,
    	    contentType : 'application/json; charset=utf-8',
    	    enctype: 'multipart/form-data',
	        processData: false,
	        contentType:false,
    	    dataType : 'json',
					url : operoxUrl+ "/saveTicket?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
					success : function(result) {
                     if(result == 'success'){
                    	 location.replace(operoxUrl+ "/ticket");
                     }else{
                    	 location.replace(operoxUrl+ "/ticket");
                     }
					},
				}); 
		return true;
	 } else {
		return false;
	} 
}
$('body').on('blur', '#ticket_form', function() {
	$("#ticket_form").showHelpOnFocus();
	$("#ticket_form").validateOnBlur(false, validationSettings);
});

</script>

<script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 

<script type="text/javascript">
  $( function() {
    $( "#issueDate" ).datepicker();
    $( "#dueDate" ).datepicker();
    
  });
</script>

<script type="text/javascript">
	(function($) {
		$.fn.serializeFormJSON = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
	})(jQuery);
</script>

<script type="text/javascript">
 var selectedFile = document.getElementById("uploadBtn");
	    selectedFile.addEventListener("change", function() {
	      var docSize = this.files[0].size;
	      $("#uploadFileGroup").find('p.jquery_form_error_message').remove();
	      if(docSize > 2097152){
	    	  document.getElementById("uploadFile").value = '';
	    	  $("#uploadFileGroup").append("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </p>");
	      }
	    });
	    document.getElementById("uploadBtn").onchange = function () {
		    document.getElementById("uploadFile").value = this.value;
		    };
		    $('.custom-upload input[type=file]').change(function(){
		    	   $(this).next().find('input').val($(this).val());
		    	});
</script>

<jsp:include page="../masterFooter.jsp" />




