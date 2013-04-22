package org.yukung.perfect_java.chap5;

public abstract class TemplateMethodPatternSample {
	
	public static void main(String[] args) {
		TemplateMethodPatternSample pattern1 = new TemplateMethodPatternImpl1();
		pattern1.doit();
		
		TemplateMethodPatternSample pattern2 = new TemplateMethodPatternImpl2();
		pattern2.doit();
	}
	
	protected abstract void doTask();
	
	void doit() { // 骨格実装
		System.out.println("共通処理1");
		doTask(); // 継承したクラスに固有の処理
		System.out.println("共通処理2");
	}
	
}

class TemplateMethodPatternImpl1 extends TemplateMethodPatternSample {
	
	@Override
	protected void doTask() {
		System.out.println("固有処理1");
	}
}

class TemplateMethodPatternImpl2 extends TemplateMethodPatternSample {
	
	@Override
	protected void doTask() {
		System.out.println("固有処理2");
	}
	
}
