package com.bis.operox.payroll.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.bis.operox.inv.dao.entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PayrollHelper {
   /**
    * Generates pay slip for employee
 * @return 
    */
	
	public byte[] generatePDFPayroll(User user, String tax, String leaves, String lop, String netPay,String fromDate,String toDate){
		 Document document = new Document();
		 
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 try {
				PdfWriter writer = PdfWriter.getInstance(document, bos);
//	            PdfWriter.getInstance(document,new FileOutputStream(user.getFirstName()+"_paySlip"+".pdf"));
	            document.open();
	            List orderedList = new List(List.ORDERED);
	            Paragraph para = new Paragraph("Payroll from :"+fromDate+" to "+toDate);
	            orderedList.add(new ListItem("Employee Id :"+user.getId()));
	            
	            if(user.getDepartment() !=null){
	            	if(user.getDepartment().getDepartmentName()!=null && !"".equalsIgnoreCase(user.getDepartment().getDepartmentName())){
		            	orderedList.add(new ListItem("Department :"+user.getDepartment().getDepartmentName()));
		            }	
	            }
	            orderedList.add(new ListItem("Role :"+user.getRole().getDisplayName()));
	            if(user.getAddress() != null){
	            	if(user.getAddress().getAddress()!=null && !"".equalsIgnoreCase(user.getAddress().getAddress())){
		            	 orderedList.add(new ListItem("Location :"+user.getAddress().getAddress()));
		            }
	            }
	            
	            document.add(para);
	            String name = "";
	            if(user.getFirstName() != null && user.getLastName() != null){
	            	name = user.getFirstName().concat(user.getLastName());
	            }else{
	            	name = user.getFirstName();
	            }
	            Paragraph employeePara = new Paragraph("Employee Name : " +name);
	            document.add(employeePara);
	            document.add(orderedList);

	            
	            PdfPTable table = new PdfPTable(5); // 3 columns.
	            PdfPCell cell1 = new PdfPCell(new Paragraph("Compensation Type"));
	            PdfPCell cell2 = new PdfPCell(new Paragraph("Tax"));
	            PdfPCell cell3 = new PdfPCell(new Paragraph("Leaves"));
	            PdfPCell cell4 = new PdfPCell(new Paragraph("Lop"));
	            PdfPCell cell5 = new PdfPCell(new Paragraph("Total"));


	            table.addCell(cell1);
	            table.addCell(cell2);
	            table.addCell(cell3);
	            table.addCell(cell4);
	            table.addCell(cell5);
	            
	            table.setHeaderRows(1);

	            table.addCell(user.getCompensatationType());
	            table.addCell(tax+"%");
	            
	            if(leaves != null && !"".equalsIgnoreCase(leaves)){
	            	table.addCell(leaves);	
	            }else{
	            	table.addCell("--");
	            }
	            if(lop != null && !"".equalsIgnoreCase(lop)){
	            	  table.addCell(lop);	
	            }else{
	            	table.addCell("--");
	            }
	            table.addCell(netPay);
	            
	            float[] columnWidths = {20f, 20f, 20f, 20f,20f};
	            table.setWidths(columnWidths);
	            Paragraph paragraph = new Paragraph();
	            addEmptyLine(paragraph, 5);
	            document.add(paragraph);
	            document.add(table);


	            document.close();

	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } 
	        return bos.toByteArray();
	}
	  private static void addEmptyLine(Paragraph paragraph, int number) {
          for (int i = 0; i < number; i++) {
                  paragraph.add(new Paragraph(" "));
          }
  }
		public  Map<String,Date> getPreviousMonthStartDateAndEndDate(){
	        HashMap<String,Date> dateList = new HashMap<String,Date>();
	        Calendar startcal = Calendar.getInstance(TimeZone.getDefault());
	        startcal.add(Calendar.MONTH, -1);
	        startcal.set(Calendar.DATE, 1);
	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        String previousMonthStartDateStr = sdf.format(startcal.getTime());
	       
	         
	        Calendar endCal = Calendar.getInstance(TimeZone.getDefault());
	        endCal.add(Calendar.MONTH, -1);
	        endCal.set(Calendar.DATE, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String previousMonthEndDateStr = sdf.format(endCal.getTime());
	        try {
	            Date previousMonthStartDate = sdf.parse(previousMonthStartDateStr);
	            dateList.put("startDate", previousMonthStartDate);
	            Date previousMonthEndDate = sdf.parse(previousMonthEndDateStr);
	            dateList.put("endDate", previousMonthEndDate);
	           
	        } catch (ParseException e) {
	        }
	        return dateList;
	    }
}
