package org.yukung.perfect_java.chap5;

public class MyHost {

	private static String priv_class_field = "private class field";
	private String priv_inst_field = "private instance field";

	public static void main(String[] args) {
		System.out.println(MyHelper.nested_priv_class_field);

		MyHelper helper = new MyHelper();
		System.out.println(helper.nested_priv_inst_field);

		MyHelper.class_doit();

		MyHost my = new MyHost();
		my.doit();
	}

	private void doit() {
		MyHelper helper = new MyHelper();
		helper.doit(this);
	}


	private static class MyHelper {

		private static String nested_priv_class_field = "nested private class field";
		private String nested_priv_inst_field = "nested private instance field";

		private static void class_doit() {
//			System.out.println(priv_class_field);	// not recommended
			System.out.println(MyHost.priv_class_field);
		}

		private void doit(MyHost my) {
			System.out.println(my.priv_inst_field);
		}
	}
}
