package util;

import java.text.DecimalFormat;

public class MyFormat {
	public String moneyFormat(int price) {
		DecimalFormat myFormat = new DecimalFormat("\\#,###");
		String formattedMoney = myFormat.format(price);
		return formattedMoney;
	}
}