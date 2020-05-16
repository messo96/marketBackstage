package marketBackstage;



import database.DBManager;
import gui.login;

public class Main {

	public static void main(String[] args) {
		DBManager d = new DBManager();
		login log = new login();
		log.start();
		d.open();
		d.close();
		
		/* java.util.Date d = new java.util.Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Time time = new Time(d.getTime());
		 System.out.println(java.sql.Date.valueOf(sdf.format(d)));
		 System.out.println(time);
	     	*/

	}

}
