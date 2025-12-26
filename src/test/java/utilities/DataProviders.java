package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider - 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\UserLoginTestData.xlsx";	// taking excel file from testData(fldr)
		
		ExcelUtility xlutil=new ExcelUtility(path);	// create an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];	// create for two dimension array which stored
		
		for(int i=1; i<=totalrows; i++)	// 1	// read the data from xl storing in two dimensional array
		{
			for(int j=0; j<totalcols; j++)	// 0	i is row and j i col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;	// returning two dimension array
		
	} 
}
