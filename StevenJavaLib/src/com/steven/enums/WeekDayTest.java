package com.steven.enums;

  
    /**   
     * 
     * 此类描述的是：学习如何使用java的枚举类来实现 星期 的表示   
     * @author: 刘壮洪   
     * @version: 2014-1-30 下午11:00:31    
     */   
    
public class WeekDayTest {

	public enum WeekDay{
		SUN, TUE, WED, THU, FRI, SAT;
	}
	
	/**   
	 * 
	 * @author: 刘壮洪  
	 * @version: 2014-1-30 下午11:00:22   
	 */

	public static void main(String[] args) {
		
		System.out.println(WeekDay.SAT);
		System.out.println(WeekDay.SAT.ordinal());
		
		WeekDay sunDay = WeekDay.valueOf("SUN");
		System.out.println(sunDay);
	}
}
