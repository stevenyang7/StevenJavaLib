package com.steven.random;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Logger;

  
/**   
 * 生成“日期+随机数” 
 * @author: 刘壮洪   
 * @version: 2013-10-5 下午1:13:48    
 */   
public class DateRandomSuffix {

	
	protected final Logger log = Logger.getLogger(DateRandomSuffix.class.getName());
	
	  
	  
    /**   
     * 此方法描述的是：   
     * @param suffix 文件后缀名
     * @author: 刘壮洪  
     * @version: 2013-10-5 下午4:50:39   
     */   
	public String generateRandomFilename(String suffix){
		String RandomFilename = "";
		Random rand = new Random();//生成随机数
		int random = rand.nextInt();
		Calendar calCurrent = Calendar.getInstance();
	    int intDay = calCurrent.get(Calendar.DATE);
	    int intMonth = calCurrent.get(Calendar.MONTH) + 1;
	    int intYear = calCurrent.get(Calendar.YEAR);
	    String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
	        String.valueOf(intDay) + "_";
	    log.debug("生成的文件名前缀为："+now);
	    RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random) + "." + suffix;
	    return RandomFilename;
	}
	
	/**   
	 * @author: 刘壮洪  
	 * @version: 2013-10-5 下午1:12:32   
	 */

	public static void main(String[] args) {
		DateRandomSuffix random = new DateRandomSuffix();
		String result = random.generateRandomFilename("txt");
		System.out.println("随机生成的文件名为：" + result);
	}

}
