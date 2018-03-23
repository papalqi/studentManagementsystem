/**
 * <p>Title: OutputExcel.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��19��
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
 * <p>Title: OutputExcel<��p>
 * <p>Description: <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��19��
 */
public class OutputExcel {
	
	public static HSSFWorkbook getGradeExcel(List<Vxueshengkpaiming> list){
		// ����һ��������
        HSSFWorkbook wb = new HSSFWorkbook();
      //����һ�����Ӳ�����
        HSSFSheet sheet = wb.createSheet("ѧ���ɼ�");
      //������һ�У�Ҳ���Գ�Ϊ��ͷ��
        HSSFRow row = sheet.createRow(0);
     // ����һ����ʽ  
        HSSFCellStyle style = wb.createCellStyle();
      //��ʽ�������
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ѧ��"); 
        cell.setCellStyle(style);
        cell = row.createCell(1);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("�γ�����");  
        cell.setCellStyle(style); 
        cell = row.createCell(3);  
        cell.setCellValue("��ѧ���");  
        cell.setCellStyle(style); 
        cell = row.createCell(4);  
        cell.setCellValue("ƽʱ�ɼ�");  
        cell.setCellStyle(style); 
        cell = row.createCell(5);  
        cell.setCellValue("��ĩ�ɼ�"); 
        cell.setCellStyle(style); 
        cell = row.createCell(6);  
        cell.setCellValue("�ۺϳɼ�"); 
        cell.setCellStyle(style); 
        cell = row.createCell(7);  
        cell.setCellValue("�༶");  
        cell.setCellStyle(style); 
        cell = row.createCell(8);  
        cell.setCellValue("רҵ");  
        cell.setCellStyle(style); 
        cell = row.createCell(9); 
        cell.setCellValue("ѧԺ");  
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
