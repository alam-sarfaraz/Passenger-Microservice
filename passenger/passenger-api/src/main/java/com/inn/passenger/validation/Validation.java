package com.inn.passenger.validation;

public class Validation {

	public static String generatePnr() {
		String a = Integer.toString((int) (Math.random() * 10));
		String b = Integer.toString((int) (Math.random() * 10));
		String c = Integer.toString((int) (Math.random() * 10));
		String d = Integer.toString((int) (Math.random() * 10));
		String e = Integer.toString((int) (Math.random() * 10));
		return a + b + c + d + e;
	}

	public static String berthAllocated(Integer seatNo) {

	  String berth = null;
	  switch (seatNo) {
	    case 1:
	      berth ="LOWER_BERTH";
	      break;
	    case 2:
	      berth ="MIDDLE_BERTH";
	      break;
	    case 3:
	      berth = "UPPER_BERTH";
	      break;
	    case 4:
	      berth = "SIDE_LOWER_BERTH";
	      break;
	    case 5:
	      berth = "SIDE_UPPER_BERTH";
	      break;
	    case 6:
	      berth = "LOWER_BERTH";
	      break;
	    case 7:
	      berth = "MIDDLE_BERTH";
	      break;
	    case 8:
	      berth = "UPPER_BERTH";
        break;
	    case 9:
	      berth = "SIDE_LOWER_BERTH";
        break;
	    case 10:
	      berth = "SIDE_UPPER_BERTH";
        break;
	  }
    return berth;
	}
}
