package app.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneradorReportes {

	public  void generarReporte(HttpServletResponse response, URL reportPath,String format,Map<?, ?> parameters,Connection connection,String NameReport,String OnOffLine) throws JRException, SQLException, IOException{

		
		byte[] fichero = generarReporte2(reportPath, format, parameters, connection);
		
		ServletOutputStream out = null;
	    
		try {
	    
			String mime = "application/pdf";
			
			String extension=".pdf";
	    	
			if (format.equalsIgnoreCase("xls")){
	    	
				mime = "application/x-ms-excel";
	    		
				response.setContentType("application/download");
	    		
				response.addHeader("Content-Disposition", "attachment;filename="+NameReport+".xls");
				
				extension=".xls";
	    	
			}else if (format.equalsIgnoreCase("rtf")){
	    		
					mime = "text/rtf";
	    		
					response.addHeader("Content-Disposition", "attachment;filename="+NameReport+".rtf");
					
					extension=".rtf";
	    	
			}else 	if (format.equalsIgnoreCase("html")){
	    	
						mime = "text/html";
						
		 				extension=".html";
	    	
					}
	    	
			response.setContentType(mime);
			
			response.setHeader("Content-disposition", OnOffLine+"; filename="+NameReport+extension);
			//response.setHeader("Content-Length", OnOffLine+"; filename="+NameReport+extension);
	    	
			response.setContentLength(fichero.length);
             
			out = response.getOutputStream();
             
			out.write(fichero, 0, fichero.length);
        
		} catch (Exception e) {
        
			e.printStackTrace();
        
		} finally {
        
//			connection.close();
            connection.close();
			out.flush();
            
			out.close();
        
		}

	}
	/**
	 * Devuelve el reporte en un array[] de byte
	 * @param reportPath
	 * @param format
	 * @param parameters
	 * @param connection
	 * @return
	 * @throws JRException
	 * @throws SQLException
	 * @throws IOException 
	 */
	public  byte[] generarReporte2(URL reportPath,String format,Map<?, ?> parameters,Connection connection) throws JRException, SQLException, IOException{
		
//		int numberOfBytesToRead = 200;
//		byte[] buffer = new byte[numberOfBytesToRead];
//		reportPath.openStream().read(buffer);
		
		JasperReport report2 = (JasperReport)JRLoader.loadObject(reportPath);
		
		@SuppressWarnings("unchecked")
		JasperPrint fill = JasperFillManager.fillReport(report2, (Map<String, Object>) parameters, connection);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		if(format.equalsIgnoreCase("pdf") || format.equalsIgnoreCase("")){
		
			JasperExportManager.exportReportToPdfStream(fill, out);
		}else 
			
			if(format.equalsIgnoreCase("xls")){
			
				JRXlsExporter exporter = new JRXlsExporter(); 
			
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, fill); 
			
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out); 
			
//				exporter.exportReport(); 
			    try {
		            exporter.exportReport();
		        } catch (JRException ex) {
		            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
		        }
		
		}else 
			if(format.equalsIgnoreCase("html")){
			
				JRHtmlExporter exporter = new JRHtmlExporter(); 
			
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, fill); 
			
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out); 
		
				exporter.exportReport(); 
		}else 
				if(format.equals("rtf")){
			
					JRRtfExporter exporter = new JRRtfExporter(); 
					
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, fill); 
			
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out); 
			
					exporter.exportReport(); 
		
				} else 
					
					if(format.equals("txt")){
			
						Map<?, ?> txt_formatoparams = (Map<?, ?>) parameters.get("TXT_FORMATO_PARAMS");
			
						JRExporter exporter = new JRTextExporter();
			
						//Integer pageHeight=new Integer(fill.getPageHeight());			

						//Integer pageWidth = new Integer(fill.getPageWidth());			
			
						exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, fill);			
			
						exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, out);
			
			
						if(txt_formatoparams != null){
				
							exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH,txt_formatoparams.get(JRTextExporterParameter.PAGE_WIDTH));				
				
							exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, txt_formatoparams.get(JRTextExporterParameter.PAGE_HEIGHT));				
				
							exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, txt_formatoparams.get(JRTextExporterParameter.CHARACTER_WIDTH));				
							
							exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, txt_formatoparams.get(JRTextExporterParameter.CHARACTER_HEIGHT));				

							//exporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, txt_formatoparams.get(JRTextExporterParameter.BETWEEN_PAGES_TEXT));				
				
							exporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, txt_formatoparams.get(JRTextExporterParameter.LINE_SEPARATOR));
			
						}
			
			
						exporter.exportReport();
			
		}
		
		return out.toByteArray();		
	
	}

}