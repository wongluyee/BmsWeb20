package util;

import java.text.DecimalFormat;

public class MyFormat {
	public String moneyFormat(int price) {
		DecimalFormat myFormat = new DecimalFormat("\\#,###.00");
		String formattedMoney = myFormat.format(price);
		return formattedMoney;
	}
}
