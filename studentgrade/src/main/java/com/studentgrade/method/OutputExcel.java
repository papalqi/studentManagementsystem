/**
 * <p>Title: OutputExcel.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月19日
 * @version 1.0
 */
package com.studentgrade.method;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.studentgrade.bean.Vxueshengkpaiming;

/**
 * <p>Title: OutputExcel<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月19日
 */
public class OutputExcel {
	
	public static HSSFWorkbook getGradeExcel(List<Vxueshengkpaiming> list){
		// 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
      //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("学生成绩");
      //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
     // 生成一个样式  
        HSSFCellStyle style = wb.createCellStyle();
      //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号"); 
        cell.setCellStyle(style);
        cell = row.createCell(1);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("课程名字");  
        cell.setCellStyle(style); 
        cell = row.createCell(3);  
        cell.setCellValue("教学班号");  
        cell.setCellStyle(style); 
        cell = row.createCell(4);  
        cell.setCellValue("平时成绩");  
        cell.setCellStyle(style); 
        cell = row.createCell(5);  
        cell.setCellValue("期末成绩"); 
        cell.setCellStyle(style); 
        cell = row.createCell(6);  
        cell.setCellValue("综合成绩"); 
        cell.setCellStyle(style); 
        cell = row.createCell(7);  
        cell.setCellValue("班级");  
        cell.setCellStyle(style); 
        cell = row.createCell(8);  
        cell.setCellValue("专业");  
        cell.setCellStyle(style); 
        cell = row.createCell(9); 
        cell.setCellValue("学院");  
        cell.setCellStyle(style); 
        
        
        for (int i = 0; i < list.size(); i++) {
        	row = sheet.createRow(i + 1);
        	row.createCell(0).setCellValue(list.get(i).getIstudentid().toString());
            row.createCell(1).setCellValue(list.get(i).getSname());
            row.createCell(2).setCellValue(list.get(i).getScoursename());
            row.createCell(3).setCellValue(list.get(i).getIteachclassid().toString());
            if (list.get(i).getIscore1()!=null) {
				row.createCell(4).setCellValue(list.get(i).getIscore1().toString());
			} 
            if (list.get(i).getIscore2()!=null) {
				row.createCell(5).setCellValue(list.get(i).getIscore2().toString());
			} 
            if (list.get(i).getIallscore()!=null) {
				row.createCell(6).setCellValue(list.get(i).getIallscore().toString());
			} 
            row.createCell(7).setCellValue(list.get(i).getSclassname());
            row.createCell(8).setCellValue(list.get(i).getSmajorname());
            row.createCell(9).setCellValue(list.get(i).getScollegename());

		}
        return wb;
	}
}
