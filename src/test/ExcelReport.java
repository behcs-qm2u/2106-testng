package test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.common.excelreport.ExcelReportGenerator;

public class ExcelReport {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException  {
		// TODO Auto-generated method stub
		
		ExcelReportGenerator.generateExcelReport("ExcelReport.xlsx", "/home/behcs/eclipse-may2021-ws/TestNG_Example/");

	}

}
