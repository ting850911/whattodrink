package _00_init.utils;

public class DBService {
	public  static final String host = "127.0.0.1";
	public  static final String DB_MYSQL = "MYSQL";
	public  static final String DB_SQLSERVER = "SQLSERVER";

	public  static final String DB_TYPE = DB_MYSQL;

	private static final String DBURL_SQLServer = "jdbc:sqlserver://" + host + ":1433;databaseName=project";
	public  static final String USERID_SQLServer = "sa";
	public  static final String PSWD_SQLServer = "sa123456";

	public  static final String nameMs = "java:comp/env/jdbc/memberDB";
	public  static final String nameMy = "java:comp/env/jdbc/memberDB";
	
	public  static String JNDI_DB_NAME = (DB_TYPE.equals(DB_MYSQL)? nameMy : nameMs);
	
	static {
		if (JNDI_DB_NAME.equals(DB_MYSQL)) {
			JNDI_DB_NAME = nameMy;
		} else if (JNDI_DB_NAME.equals(DB_SQLSERVER)) {
			JNDI_DB_NAME = nameMs;
		}
	}
	
	private static final String DBURL_MySQL = "jdbc:mysql://" + host
			+ "/project?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true";
	public static final String USERID_MySQL = "root";
	public static final String PSWD_MySQL = "00000000";
	
	private static final String DROP_item_MySQL = "DROP Table IF EXISTS item";
	
	private static final String DROP_tag_product_MySQL = "DROP Table IF EXISTS tag_product ";
	
	private static final String DROP_favorite_MySQL = "DROP Table IF EXISTS favorite ";
	
	private static final String DROP_company_MySQL = "DROP Table IF EXISTS company ";
	
	private static final String DROP_customer_MySQL = "DROP Table IF EXISTS customer ";
	
	
	
	//CreateTable_company
	private static final String CREATE_company_MySQL = "Create TABLE company " 
			+ "(company_id       		VARCHAR(10) PRIMARY KEY NOT NULL, "
			+ " company_name     		VARCHAR(20), " 
			+ " company_account         VARCHAR(20), " 
			+ " company_password      	VARCHAR(20), "
			+ " company_owner           VARCHAR(20), " 
			+ " company_owner_email     VARCHAR(40) UNIQUE KEY," 
			+ " start_time         		TIME,"
			+ " end_time        	    TIME,"
			+ " tel        			    VARCHAR(20),"
			+ " company_address         VARCHAR(75),"
			+ " website         		VARCHAR(50),"
			+ " company_iconpath       	VARCHAR(255),"
			+ " company_filename        VARCHAR(20),"
			+ " tax_id_number          	VARCHAR(10),"
			+ " register_date         	DATETIME,"
			+ " alter_date         		DATETIME"
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";
	
	
	//CreateTable_customer
	private static final String CREATE_customer_MySQL = "Create TABLE customer " 
			+ "(customer_id       		INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
			+ " customer_password  		VARCHAR(20), " 
			+ " sign_date         		DATETIME, " 
			+ " customer_name      		VARCHAR(20), "
			+ " birthday          	 	DATE, " 
			+ " email     				VARCHAR(40) UNIQUE KEY," 
			+ " customer_address        VARCHAR(75),"
			+ " alter_date        	    DATETIME,"
			+ " invitation        		VARCHAR(10),"
			+ " weight         			DECIMAL(4,1)"
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";
	
	
	//CreateTable_tag_product
	private static final String CREATE_tag_product_MySQL = "Create TABLE tag_product " 
			+ "(product_id       		INT NOT NULL, "
			+ " tag_name  				VARCHAR(20), " 
			+ " CONSTRAINT tag_product_product_id_FK FOREIGN KEY (product_id) REFERENCES product(product_id)" 
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";
	

	//CreateTable_favorite
	private static final String CREATE_favorite_MySQL = "Create TABLE favorite " 
			+ "(customer_id       		INT NOT NULL, "
			+ " company_id  			VARCHAR(10)," 
			+ " CONSTRAINT favorite_customer_id_FK FOREIGN KEY (customer_id) REFERENCES customer(customer_id)," 
			+ " CONSTRAINT favorite_company_id_FK FOREIGN KEY (company_id) REFERENCES company(company_id)" 
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";
	
	
	//CreateTable_item
	private static final String CREATE_item_MySQL = "Create TABLE item " 
			+ "(item_id       			INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
			+ " order_id  				INT, " 
			+ " product_id         		INT, " 
			+ " temp_id      			INT, "
			+ " sugar_id          	 	INT, " 
			+ " capacity     			VARCHAR(5)," 
			+ " item_cal        		INT,"
			+ " price        	    	INT,"
			+ " note        			VARCHAR(60),"
			+ " CONSTRAINT item_order_id_FK FOREIGN KEY (order_id) REFERENCES `order`(order_id)," 
			+ " CONSTRAINT item_product_id_FK FOREIGN KEY (product_id) REFERENCES product(product_id)," 
			+ " CONSTRAINT item_temp_id_FK FOREIGN KEY (temp_id) REFERENCES temp_level(temp_id)," 
			+ " CONSTRAINT item_sugar_id_FK FOREIGN KEY (sugar_id) REFERENCES sugar_level(sugar_id)" 
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";
	
	
	
	public static String getDropItemMysql() {
		return DROP_item_MySQL;
	}

	public static String getDropTagProductMysql() {
		return DROP_tag_product_MySQL;
	}

	public static String getDropFavoriteMysql() {
		return DROP_favorite_MySQL;
	}

	public static String getDropCompanyMysql() {
		return DROP_company_MySQL;
	}

	public static String getDropCustomerMysql() {
		return DROP_customer_MySQL;
	}

	public static String getCreateCompanyMysql() {
		return CREATE_company_MySQL;
	}

	public static String getCreateCustomerMysql() {
		return CREATE_customer_MySQL;
	}

	public static String getCreateTagProductMysql() {
		return CREATE_tag_product_MySQL;
	}

	public static String getCreateFavoriteMysql() {
		return CREATE_favorite_MySQL;
	}

	public static String getCreateItemMysql() {
		return CREATE_item_MySQL;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static final String CREATE_OrderItems_MySQL = "Create TABLE OrderItems " 
			+ "(seqno            int NOT NULL AUTO_INCREMENT Primary Key , "
			+ " orderNo          int, " 
			+ " bookId           int, " 
			+ " Description      varchar(72), "
			+ " amount           int, " 
			+ " unitPrice        DECIMAL(18,1), " 
			+ " Discount         Decimal(8,3) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_OrderItems_SQLServer = "Create TABLE OrderItems "
			+ "(seqno            int NOT NULL IDENTITY Primary Key , " 
			+ " orderNo          int, "
			+ " bookId           int, " 
			+ " Description      varchar(72), " 
			+ " amount           int, "
			+ " unitPrice        DECIMAL(18,1), " 
			+ " Discount         MONEY " 
			+ " ) ";

	private static final String CREATE_Orders_MySQL = "Create Table Orders "
			+ "(orderNo  int NOT NULL AUTO_INCREMENT Primary Key , " 
			+ " memberId          varchar(20), "
			+ " totalAmount       Decimal(11,1), " 
			+ " shippingAddress   varchar(64), "
			+ " BNO               varchar(8), " 
			+ " invoiceTitle      varchar(72), " 
			+ " orderDate         DateTime, "
			+ " ShippingDate      DateTime, " 
			+ " CancelTag         Char(1) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_Orders_SQLServer = "Create Table Orders "
			+ "(orderNo  int NOT NULL IDENTITY Primary Key , " 
			+ " memberId          varchar(20), "
			+ " totalAmount       Decimal(11,1), " 
			+ " shippingAddress   varchar(64), "
			+ " BNO               varchar(8), " 
			+ " invoiceTitle      varchar(72), " 
			+ " orderDate         DateTime, "
			+ " ShippingDate      DateTime, " 
			+ " CancelTag         Char(1) " 
			+ " )";

	private static final String CREATE_BookCompany_MySQL =
			  " CREATE TABLE BookCompany "
			+ "(id  int NOT NULL AUTO_INCREMENT , " 
			+ " name		    varchar(60), " 
			+ " address    		varchar(60), "
			+ " url  			varchar(120), " 
			+ " PRIMARY KEY (id) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci  ";

	private static final String CREATE_BookCompany_SQLServer = "CREATE TABLE BookCompany "
			+ "(id  int NOT NULL IDENTITY Primary Key , " 
			+ " name		    varchar(60), "
			+ " address    		varchar(60), " 
			+ " url  			varchar(120) " 
			+ " ) ";

	private static final String CREATE_Book_MySQL = " CREATE TABLE Book " 
			+ " (bookId int NOT NULL AUTO_INCREMENT, "
			+ " title				varchar(50), " 
			+ " author    			varchar(28), "
			+ " listPrice			DECIMAL(10,1), " 
			+ " discount			DECIMAL(7,3), "
			+ " coverImage   		LongBlob, " 
			+ " fileName			varchar(30), "
			+ " mimeType			varchar(30), "
			+ " bookNo 				varchar(20), " 
			+ " stock 				int, " 
			+ " category			varchar(28), "
			+ " companyId			int, "
			+ " PRIMARY KEY 		(bookId)  " 
			+ " )  ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci  ";

	private static final String CREATE_Book_SQLServer = "CREATE TABLE Book " 
			+ "(bookId int NOT NULL IDENTITY, "
			+ " title				varchar(50), " 
			+ " author    			varchar(28), " 
			+ " listPrice			Money, "
			+ " discount			Money, " 
			+ " coverImage   		Image," 
			+ " fileName			varchar(30), "
			+ " mimeType			varchar(30), "
			+ " bookNo 				varchar(20), " 
			+ " stock 				int, " 
			+ " category			varchar(28), "
			+ " companyId			int, "
			+ " PRIMARY KEY 		(bookId)  " 
			+ " )";

	private static final String CREATE_Member_MySQL = " CREATE TABLE Member " 
			+ "(seqNo int NOT NULL Auto_Increment, "
			+ " memberId			varchar(20), " 
			+ " name    			varchar(32), "
			+ " password			varchar(32), " 
			+ " address 			varchar(64), "
			+ " email 				varchar(64), " 
			+ " tel  				varchar(15), "
			+ " userType			varchar(10), " 
			+ " registerTime    	DateTime, "
			+ " totalAmt     		DECIMAL(10, 1), " 
			+ " memberImage  		LongBlob, "
			+ " fileName     		varchar(20), " 
			+ " mimeType			varchar(30), "
			+ " comment      		LongText, "
			+ " unpaid_amount    	DECIMAL(8, 1), " 
			+ " PRIMARY KEY 		(seqNo) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";

	private static final String CREATE_Member_SQLServer = " CREATE TABLE Member " 
			+ "(seqNo int NOT NULL IDENTITY, "
			+ " memberID			varchar(20), " 
			+ " name    			varchar(32), "
			+ " password			varchar(32), " 
			+ " address 			varchar(64), "
			+ " email 				varchar(64), " 
			+ " tel  				varchar(15), "
			+ " userType			varchar(10), " 
			+ " registerTime    	DateTime, "
			+ " totalAmt     		MONEY, " 
			+ " memberImage  		IMAGE, "
			+ " fileName     		varchar(20), " 
			+ " mimeType			varchar(30), "
			+ " comment      	    Text, " 
			+ " unpaid_amount     MONEY, "
			+ " PRIMARY KEY 		(seqNo) " 
			+ " )";


	
	
	
	
	

	public static String getDbUrl() {
		String url = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			url = DBURL_MySQL;
		} else if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			url = DBURL_SQLServer;
		}
		System.out.println("url=" + url);
		return url;
	}

	public static String getUser() {
		String user = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			user = USERID_MySQL;
		} else if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			user = USERID_SQLServer;
		}
		return user;
	}

	public static String getPassword() {
		String pswd = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			pswd = PSWD_MySQL;
		} else if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			pswd = PSWD_SQLServer;
		}
		return pswd;
	}

}
