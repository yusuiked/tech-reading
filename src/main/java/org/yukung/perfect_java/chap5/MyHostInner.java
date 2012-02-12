package org.yukung.perfect_java.chap5;

public class MyHostInner {
	
	private static String priv_class_field = "private class field";
	
	
	public static void main(String[] args) {
		System.out.println(MyHelper.nested_priv_class_field);
		
		MyHostInner my = new MyHostInner();
		my.doit();
	}
	
	
	private String priv_inst_field = "private instance field";
	
	private int same_name_field = 3;
	
	
	private void doit() {
		MyHelper helper = new MyHelper();
		helper.doit();
	}
	
	
	private class MyHelper {
		
		private static final String nested_priv_class_field = "nested private class field";
		
		private String nested_priv_inst_field = "nested private instance field";
		
		private int same_name_field = 2;
		
		
		private void doit() {
			System.out.println(priv_inst_field);
			
			System.out.println(same_name_field); //=2
			System.out.println(same_name_field); //=>2
			System.out.println(MyHostInner.this.same_name_field); //=>3
		}
	}
}
