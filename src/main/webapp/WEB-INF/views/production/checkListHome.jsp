<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
 
 
 <script>
// Error pop
$(document).ready(function() {
    getCheckList();
});
</script>


 <!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Checklist list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Raw &amp; Check list</div>
  </section>
 
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
          <div class="data_table_head">
            <ul>             
                 <li><a href="<c:url value='/addCheckList'/>"><i class="fa fa-plus"></i>Add Checklist</a></li>     
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                    
        </div>
    <div id="checkListDiv">
        <!-- <table>
            <tbody>
                <tr>
                    <th>Id</th>
                    <th>Checklist Name</th>
                    <th>Type</th>
                    <th>Name</th>
                    <th>Attachment</th>
                    <th>No. of Tests</th>
                    <th class="action">Action</th>
                </tr>
                <tr>
                    <td><div class="ta-m">004</div></td>
                    <td><a href="checklist-view.html"><div class="ta-m">Brand Name</div></a></td>
                    <td><div class="ta-m">Hyderabad, Telangana State</div></td>
                    <td><div class="ta-sm">Operox Corporation Pvt. Ltd.</div></td>
                    <td><div class="ta-m">Vannam Mahesh</div></td>
                    <td class="action"><div>
                        <a href="add-checklist.html"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme"></i></div>
                    </td>
                </tr>
            </tbody>
        </table> -->
       
       
    </div>     
 
  </div>
 
</section>
<!-- Content Area Ends-->

<script>
  function getCheckList(){
                var operoxUrl = '${operoxUrl}';
                         $.ajax({
                            type : "GET",
                            url : operoxUrl+ "/getCheckList",
                            success : function(data) {
                                var json = JSON.parse(data);
                                   var result = "";
                                   result = result + '<div class="data_table">';
                                   result = result + '<table id="checkListPagination" class="display" cellspacing="0" width="100%">';
                                   result = result + '<thead>';       
                                   result = result + '<tr>';
                                   result = result + '<th>Id</th>';
                                   result = result + '<th>Checklist name</th>';
                                   result = result + ' <th>Type</th>';
                                   result = result + '<th>Name</th>';
                                   result = result + '<th>Attachment</th>';
                                   result = result + '<th>No. of Tests</th>';
                                   result = result + '<th class="action">Action</th>';
                                   result = result + ' </tr>';
                                   result = result + '</thead>';
                                result = result + '<tbody>';
                                  var j=0;
                                   for (var i=0; i<json.length; i++)
                                   { 
                                	   j++
                                       var report = json[i];
                                       result = result + '<tr>';
                                       result = result + '<td><div class="ta-m">'+j+'</div></td>';
                                       if(report.checkName != null && report.checkName != '' && report.checkName != 'undefined'){
                                           result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewChecklist/'+report.id+'">'+report.checkName+'</div></td>';
                                       }else{
                                           result = result + '<td><div class="ta-m">--</div></td>';
                                       }
                                      
                                       if(report.checkListType != null && report.checkListType != '' && report.checkListType != 'undefined'){
                                           result = result + '<td><div class="ta-m">'+report.checkListType+'</div></td>';
                                       }else{
                                           result = result + '<td><div class="ta-m">--</div></td>';
                                       }
                                      
                                       if(report.nameOfMaterial != null && report.nameOfMaterial != '' && report.nameOfMaterial != 'undefined'){
                                           result = result + '<td><div class="ta-m">'+report.nameOfMaterial+'</div></td>';
                                       }else{
                                           result = result + '<td><div class="ta-m">--</div></td>';
                                       }
                                       
                                       if(report.fileName != null && report.fileName != '' && report.fileName != 'undefined'){
                                           result = result + '<td><div class="ta-m">'+report.fileName+'</div></td>';
                                       }else{
                                           result = result + '<td><div class="ta-m">--</div></td>';
                                       }
                                      
                                      
                                       if(report.noOfTests != null && report.noOfTests != '' && report.noOfTests != 'undefined'){
                                           result = result + '<td><div class="ta-m">'+report.noOfTests+'</div></td>';
                                       }else{
                                           result = result + '<td><div class="ta-m">--</div></td>';
                                       }
                                       result = result + '<td class="action"><div>';
                                       result = result + '<a href="#"><a href="${operoxUrl}/editCheckList/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteCheckList('+report.id+');"></i></div>';
                                       result = result + '</td>';
                                       result = result + '</tr>';
                                   }
                                   
                                   result = result +'</tbody>';
                                   result = result +'</table>';
//                                    result = result +'</div>';
                                   $("#checkListDiv").empty(); 
                                   $("#checkListDiv").append(result)
                                   checkListPagination();
                            },
                        });
  }

</script>

<script>
function checkListPagination(){
    $('#checkListPagination').DataTable( {
        "scrollCollapse": false,
        "lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
        "bLengthChange": false,
        "bDestroy": true,
        "ordering": false,
        "oLanguage": {
             "sSearch": ""
         }
      
    } );
}
</script>

<script>
function deleteCheckList(checkListId){
    var operoxUrl = '${operoxUrl}';
    var message = confirm("Do you want to delete this Check List");
    if (message == true) {
        $.ajax({
            type : "GET",
            url : operoxUrl+ "/deleteCheckList?&checkListId="+checkListId,
            success : function(data) {
                getCheckList();
            },
        });
    }
}
</script>


<jsp:include page="../masterFooter.jsp" />