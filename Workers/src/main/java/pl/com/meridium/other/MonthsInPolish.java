package pl.com.meridium.other;

public class MonthsInPolish {
	
	public static String MonthInPolish(int month) {
		
		String m;
		switch (month) {
	    case 0:
	    	m="Styczeń";
	    	
	    	break;
	    case 1:
	    	m="Luty";
	    	
	    	break;
	    case 2:
	    	m="Marzec";
	    	
	    	break;
	    case 3:
	    	m="Kwiecień";
	    	
	    	break;
	    case 4:
	    	m="Maj";
	    	
	    	break;
	    case 5:
	    	m="Czerwiec";
	    	
	    	break;
	    case 6:
	    	m="Lipiec";
	    	
	    	break;
	    case 7:
	    	m="Sierpień";
	    	
	    	break;
	    case 8:
	    	m="Wrzesień";
	    	
	    	break;
	    case 9:
	    	m="Październik";
	    	
	    	break;
	    case 10:
	    	m="Listopad";
	    	
	    	break;
	    case 11:
	    	m="Grudzień";
	    	
	    	break;
	    	default:
	    		m="Miesiąc nie istnieje";
	    		
		    	break;	
	    	
	    }
		return m;
	}
	
	
    
    
}
