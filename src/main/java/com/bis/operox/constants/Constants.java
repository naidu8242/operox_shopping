package com.bis.operox.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
	
	
	public static final String Currency = "Currency";
	public static  String Dashboard = "Dashboard";
	public static  String Customers = "Customers";
	public static  String Suppliers = "Suppliers";
	public static  String Setup = "Setup";
	public static  String Stock = "Stock";
	public static  String Production = "Production";

	public static  String Billing = "Billing";

	public static Integer RECORD_ACTIVE = 1;
	public static Integer RECORD_IN_ACTIVE = 0;
	public static Integer Active = 1;
	public static Integer IN_Active = 0;
	
	public static  String WareHouse = "Warehouse";
	
	public static String INVITE_USER = "Invite User";
	public static String FORGOT_PASSWORD = "Forgot Password";
	public static String ENTITY_TYPE_EMAIL = "Email";
	public static String TICKET_BY_CUSTOMER = "Ticket By Customer";
	public static String TICKET_ASSIGNED_USER = "Ticket Assigned User";
	public static String PURCHASE_ORDER_SUPPLIER = "Purchase Order";
	public static String TRANSFER_STOCK = "Transfer Stock";
	public static String RECEIVED_STOCK = "Received Stock";
	
	public static String NOTIFICATION_UNREAD = "unread";
	public static String NOTIFICATION_READ = "read";
	public static String  NOTIFICATION_DELETED= "0";
	public static String  NOTIFICATION_NOT_DELETED= "1";
	
	public static String SUPPLIER_ACTIVE = "Active";
	public static String SUPPLIER_INACTIVE = "Inactive";
	
	public static transient DateFormat DMY = new SimpleDateFormat("dd-MM-yyyy");
	public static DateFormat DF_DMY = new SimpleDateFormat("dd-MM-yyyy");

	
	public static String ENTITY_TYPE_NOTIFICATION = "Notification";
	public static String NOTIFICATION_TO_ASSIGNED_USER = "Assigned User";

	public static DateFormat DF_MDY = new SimpleDateFormat("MM/dd/yyyy");
	

	public static String TAX_VALUE = "payroll";
	public static Integer FIRST_QUARTER_LEAVES = 20;
	public static Integer SECOND_QUARTER_LEAVES = 15;
	public static Integer THIRD_QUARTER_LEAVES = 10;
	public static Integer FOURTH_QUARTER_LEAVES = 5;
	
	
	public static Long ROLE_STORE_MANAGER = 2l;
	public static  String WorkOrder = "WorkOrder";
	public static  String RawMaterial = "RawMaterial";
	public static  String Customer = "Customer";
	public static  String Ticket = "Ticket";
	public static  String CheckList = "CheckList";
	
	public static  String User = "User";
	public static  String Shift = "Shift";
	public static  String Category = "Category";
	public static  String Product = "Product";
	public static  String Store = "Store";
	public static  String Brands = "Brands";
	public static  String MeasuringUnit = "MeasuringUnit";
	public static  String Depatment = "Depatment";
	public static  String Counter = "Counter";
	public static  String Offers = "Offers";
	public static  String Holiday = "Holiday";
	public static  String Leave = "Leave";
	public static  String PayRoll = "PayRoll";
	public static  String Tax = "Tax";
	
	public static  String PurchaseOrder = "PurchaseOrder";
	public static  String ReceivedItem = "ReceivedItem";
	public static  String ReturnStock = "ReturnStock";
	public static  String OpeningStock = "OpeningStock";
	public static  String TransferStock = "TransferStock";
	
	public static  String StoreType = "Production Unit";
	
	public static String PAYSLIP_GENERATED_YES = "Y";
	public static String PAYSLIP_GENERATED_NO = "N";
}



