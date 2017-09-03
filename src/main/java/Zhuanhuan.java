import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.pfw.framework.modules.web.struts2.Struts2Utils;


public class Zhuanhuan {

	public static void main(String[] args) throws Exception {
		
		//cjzhuanhuan();//初级转换成excel
		//fxscExcel();//将excel转换成text
		//anquankdzZH();//康花路安全考试转换
		anquankdzALFXZH();//康花路安全考试案例分析转换
	}
	/*
	 * 
	 * 安全分析
	 */
	public static void anquankdzALFXZH() throws Exception
	{
		String readmeFile = "D:\\work\\Jinye\\项目\\船厂技工\\【康桥校区APP系统升级】\\上海特种设备作业人员理论考试习题集（电梯司机）\\案例分析.txt";
		File f1 = new File(readmeFile);
		
		String fl = "案例分析";
		
		String scdz = "D:\\work\\Jinye\\项目\\船厂技工\\【康桥校区APP系统升级】\\上海特种设备作业人员理论考试习题集（电梯司机）\\aqks-dt.xls";
		File f2 = new File(scdz);
		FileInputStream fileInputStream = new FileInputStream(f2);
		POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
				fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		
		String str = null;
		BufferedReader br = new BufferedReader(new FileReader(f1));
		int rownum = 0;
		
		HSSFRow row = null;
		int i = 4;
		
		String tx = "";
		String avstr = "";
		while ((str = br.readLine()) != null) {
			
			if(!str.equals(""))
			{
				System.out.println(str);
				if(str.indexOf("tx-") >= 0)
				{
					tx = "["+str.substring(str.indexOf("tx-")+3)+"]";
					System.out.println("----tx:"+tx);
					continue;
				}
				
				if((str.indexOf("A、") > 0 || str.indexOf("B、") > 0 || str.indexOf("C、") > 0 || str.indexOf("D、") > 0 || str.indexOf("E、") > 0 || str.indexOf("F、") > 0))
				{
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(str.replace("*", "").trim());
					if(str.indexOf("*") >= 0)
					{
						String zqdastr = "";
						if(i == 4)
						{
							zqdastr = "A";
						}
						if(i == 5)
						{
							zqdastr = "B";
						}
						if(i == 6)
						{
							zqdastr = "C";
						}
						if(i == 7)
						{
							zqdastr = "D";
						}
						if(i == 8)
						{
							zqdastr = "E";
						}
						if(i == 9)
						{
							zqdastr = "F";
						}
						
						if(row.getCell(3) != null)
						{
							cell = row.getCell(3);
							zqdastr = cell.getStringCellValue() + zqdastr;
						}else{
							cell = row.createCell(3);
						}
						cell.setCellValue(zqdastr);
						
						cell = row.createCell(2);
						cell.setCellValue("正确答案："+zqdastr);
						
					}
					i++;
				}else{
					avstr = avstr + str.replace(" ", "").trim() + "\r\n";
					if(str.indexOf("事故原因分析") > 0)
					{
						i = 4;
						row = sheet.createRow(rownum+1);
						HSSFCell cell = row.createCell(1);
						cell.setCellValue(avstr);//题目写进第2列
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(fl);//分类写进excel第一列
						rownum++;
						avstr = "";
						continue;
					}
				}
			}
		}
		
		OutputStream os = new FileOutputStream(f2);
		hssfWorkbook.write(os);
		os.flush();
		os.close();
	
	}
	
	public static void anquankdzZH() throws Exception
	{
		String readmeFile = "D:\\work\\Jinye\\项目\\船厂技工\\【康桥校区APP系统升级】\\上海特种设备作业人员理论考试习题集（电梯司机）\\安全知识.txt";
		File f1 = new File(readmeFile);
		
		String fl = "安全知识";
		
		String scdz = "D:\\work\\Jinye\\项目\\船厂技工\\【康桥校区APP系统升级】\\上海特种设备作业人员理论考试习题集（电梯司机）\\aqks-dt.xls";
		File f2 = new File(scdz);
		FileInputStream fileInputStream = new FileInputStream(f2);
		POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
				fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		
		String str = null;
		BufferedReader br = new BufferedReader(new FileReader(f1));
		int rownum = 0;
		
		HSSFRow row = null;
		int i = 4;
		
		String tx = "";
		while ((str = br.readLine()) != null) {
			
			if(!str.equals(""))
			{
				System.out.println(str);
				if(str.indexOf("tx-") >= 0)
				{
					tx = "["+str.substring(str.indexOf("tx-")+3)+"]";
					System.out.println("----tx:"+tx);
					continue;
				}
				
				if(str.indexOf("、") > 0)
				{
					String sftm = str.substring(0,str.indexOf("、"));
					boolean isNunicodeDigits=StringUtils.isNumeric(sftm);
					if(isNunicodeDigits)//表示是题目
					{
						i = 4;
						row = sheet.createRow(rownum+1);
						HSSFCell cell = row.createCell(1);
						str = str.replace(" ", "").trim().substring(str.indexOf("、")+1) + tx;
						cell.setCellValue(str);//题目写进第2列
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(fl);//分类写进excel第一列
						rownum++;
						continue;
					}else
					{
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(str.replace("*", "").trim());
						if(str.indexOf("*") >= 0)
						{
							String zqdastr = "";
							if(i == 4)
							{
								zqdastr = "A";
							}
							if(i == 5)
							{
								zqdastr = "B";
							}
							if(i == 6)
							{
								zqdastr = "C";
							}
							if(i == 7)
							{
								zqdastr = "D";
							}
							if(i == 8)
							{
								zqdastr = "E";
							}
							if(i == 9)
							{
								zqdastr = "F";
							}
							
							if(row.getCell(3) != null)
							{
								cell = row.getCell(3);
								zqdastr = cell.getStringCellValue() + zqdastr;
							}else{
								cell = row.createCell(3);
							}
							cell.setCellValue(zqdastr);
							
							cell = row.createCell(2);
							cell.setCellValue("正确答案："+zqdastr);
							
						}
						i++;
					}
					
				}else{
					
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(str.replace("*", "").trim());
					if(str.indexOf("*") >= 0)
					{
						String zqdastr = "";
						if(i == 4)
						{
							zqdastr = "A";
						}
						if(i == 5)
						{
							zqdastr = "B";
						}
						if(i == 6)
						{
							zqdastr = "C";
						}
						if(i == 7)
						{
							zqdastr = "D";
						}
						if(i == 8)
						{
							zqdastr = "E";
						}
						if(i == 9)
						{
							zqdastr = "F";
						}
						
						if(row.getCell(3) != null)
						{
							cell = row.getCell(3);
							zqdastr = cell.getStringCellValue() + zqdastr;
						}else{
							cell = row.createCell(3);
						}
						cell.setCellValue(zqdastr);
						
						cell = row.createCell(2);
						cell.setCellValue("正确答案："+zqdastr);
						
					}
					i++;
					
				}
			}
		}
		
		OutputStream os = new FileOutputStream(f2);
		hssfWorkbook.write(os);
		os.flush();
		os.close();
	
	}
	public static void fxscExcel() throws Exception
	{
		String scdz = "D:\\work\\Jinye\\product\\doc\\题库系统\\16年题目\\16年最新\\初级\\dgdz.xls";
		File f3 = new File(scdz);
		FileInputStream fileInputStream = new FileInputStream(f3);
		POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
				fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		
		String readmeFile = "D:\\work\\Jinye\\product\\doc\\题库系统\\16年题目\\16年最新\\初级\\dgdz.txt";
		File f1 = new File(readmeFile);
		FileWriter fw = new FileWriter(f1, true);
		PrintWriter pw = new PrintWriter(fw);
		
		for (int crow = 1; crow <= sheet.getLastRowNum(); crow++)
		{
			HSSFRow crrowObj = sheet.getRow(crow);
			String tm = crrowObj.getCell(1).toString().trim();
			pw.println(crow+"、 "+tm);
			
			String zqda = crrowObj.getCell(3).toString().trim();
			
			String zqdasx = "";
			if(zqda.equals("A"))
			{
				zqdasx = "1";
			}
			if(zqda.equals("B"))
			{
				zqdasx = "2";
			}
			if(zqda.equals("C"))
			{
				zqdasx = "3";
			}
			if(zqda.equals("D"))
			{
				zqdasx = "4";
			}
			if(zqda.equals("E"))
			{
				zqdasx = "5";
			}
			if(zqda.equals("F"))
			{
				zqdasx = "6";
			}
			for(int i = 1;i <= 6;i++)
			{
				if(crrowObj.getCell(i+3) != null)
				{
					String da = crrowObj.getCell(i+3).toString().trim();
					
					if(da != null && !da.equals(""))
					{
						if(zqdasx.equals(String.valueOf(i)))
						{
							da = da + "            *";
						}
						pw.println("        " + da);
					}
				}
				
			}
			
			pw.flush();
		}
		
		fw.flush();
		pw.close();
		fw.close();
		
	}
	public static void cjzhuanhuan()  throws Exception
	{
		String choFile = "D:\\work\\Jinye\\product\\doc\\题库系统\\16年题目\\16年最新\\初级\\choice.xls";
		File f2 = new File(choFile);
		FileInputStream fileInputStream2 = new FileInputStream(f2);
		POIFSFileSystem pOIFSFileSystem2 = new POIFSFileSystem(
				fileInputStream2);
		HSSFWorkbook hssfWorkbook2 = new HSSFWorkbook(pOIFSFileSystem2);
		HSSFSheet sheet2 = hssfWorkbook2.getSheetAt(0);
		
		String scdz = "D:\\work\\Jinye\\product\\doc\\题库系统\\16年题目\\16年最新\\初级\\dgdz.xls";
		File f3 = new File(scdz);
		FileInputStream fileInputStream = new FileInputStream(f3);
		POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
				fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		
		//遍历choice.xls
		for (int crow = 1; crow <= sheet2.getLastRowNum(); crow++)
		//for (int crow = 1; crow <= 3; crow++)
		{
			HSSFRow crrowObj = sheet2.getRow(crow);
			String sstmid = crrowObj.getCell(0).toString().trim();
			
			String damc = crrowObj.getCell(1).toString().trim();
			String sfzqdm = crrowObj.getCell(2).toString().trim();
			String sxh = crrowObj.getCell(3).toString().trim();
			
			//for (int row = 1; row <= 3; row++)
			for (int row = 1; row <= sheet.getLastRowNum(); row++)
			{
				HSSFRow rowObj = sheet.getRow(row);
				String tmid = rowObj.getCell(10).toString().trim();
				
				
				if(sstmid.equals(tmid))
				{
					int lxh = 0;
					String zqdadm = "";
						
					if(sxh.equals("1"))
					{
						lxh = 4;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "A";
						}
					}
					if(sxh.equals("2"))
					{
						lxh = 5;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "B";
						}
					}
					if(sxh.equals("3"))
					{
						lxh = 6;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "C";
						}
					}
					if(sxh.equals("4"))
					{
						lxh = 7;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "D";
						}
					}
					if(sxh.equals("5"))
					{
						lxh = 8;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "E";
						}
					}
					if(sxh.equals("6"))
					{
						lxh = 9;
						if(sfzqdm.equals("1"))
						{
							zqdadm = "F";
						}
					}
					
					HSSFCell cell = rowObj.createCell(lxh);
					cell.setCellValue(damc);
					
					if(!zqdadm.equals(""))
					{
						cell = rowObj.createCell(2);
						cell.setCellValue("正确答案："+zqdadm);
						
						cell = rowObj.createCell(3);
						cell.setCellValue(zqdadm);
					}
					
				}
			}
		}
		
		OutputStream os = new FileOutputStream(f3);
		hssfWorkbook.write(os);
		os.flush();
		os.close();
		
		
	}
	
	public static void zjzhuanhuan()  throws Exception
	{
		// TODO Auto-generated method stub
				String readmeFile = "D:\\work\\Jinye\\product\\doc\\16年最新\\电工中级.txt";
				File f1 = new File(readmeFile);
				
				String scdz = "D:\\work\\Jinye\\product\\doc\\16年最新\\dgdz.xls";
				File f2 = new File(scdz);
				FileInputStream fileInputStream = new FileInputStream(f2);
				POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
						fileInputStream);
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				
				String str = null;
				BufferedReader br = new BufferedReader(new FileReader(f1));
				int rownum = 0;
				
				HSSFRow row = null;
				int i = 4;
				while ((str = br.readLine()) != null) {
					
					if(!str.equals(""))
					{
						System.out.println(str);
						if(str.indexOf("[单选题]") > 0 || str.indexOf("[判断题]") > 0)
						{
							i = 4;
							row = sheet.createRow(rownum+1);
							HSSFCell cell = row.createCell(1);
							cell.setCellValue((str.replace(" ", "")).trim());
							
							rownum++;
						}else{
							HSSFCell cell = row.createCell(i);
							cell.setCellValue(str.replace("*", "").trim());
							if(str.indexOf("*") >= 0)
							{
								cell = row.createCell(3);
								String zqdastr = "";
								if(i == 4)
								{
									zqdastr = "A";
								}
								if(i == 5)
								{
									zqdastr = "B";
								}
								if(i == 6)
								{
									zqdastr = "C";
								}
								if(i == 7)
								{
									zqdastr = "D";
								}
								if(i == 8)
								{
									zqdastr = "E";
								}
								if(i == 9)
								{
									zqdastr = "F";
								}
								cell.setCellValue(zqdastr);
								cell = row.createCell(2);
								cell.setCellValue("正确答案："+zqdastr);
								
							}
							i++;
						}
					}
				}
				
				OutputStream os = new FileOutputStream(f2);
				hssfWorkbook.write(os);
				os.flush();
				os.close();
	}

}
