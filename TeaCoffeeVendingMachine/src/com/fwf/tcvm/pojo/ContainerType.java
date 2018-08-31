package com.fwf.tcvm.pojo;

public enum ContainerType {

	     Water("Water",1), 
	     Milk("Milk", 2),
	     Coffee("Coffee", 3),
	     Tea("Tea", 4),
	     Sugar("Sugar", 5);

	     private int id;
	     //private String name;

	     ContainerType(String name, int id) {
	    //	 this.name = name;
	         this.id = id;
	     }

	     public int getId() { 
	         return id;
	     }
	     
/*	     public String getName() { 
	         return name;
	     }*/
	     
	     public static ContainerType getById(int id){
	    	 
	    	 ContainerType[] values = ContainerType.values();
	    	 
	    	 for (ContainerType containerType : values) {
				if(id==containerType.getId())
					return containerType;
			 }
	    	 
	    	 return null;
	     }

}
