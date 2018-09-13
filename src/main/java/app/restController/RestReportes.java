package app.restController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.utilidades.GeneradorReportes;
//import app.utilidades.JasperRerportsSimpleConfig;
import app.utilidades.SimpleReportExporter;
import app.utilidades.SimpleReportFiller;
import app.utilidades.URIS;

@RequestMapping("/RestResports/")
@RestController
public class RestReportes {
	
	@Autowired
	DataSource dataSource;
	@RequestMapping(value="listar")
	public void  reporte (HttpServletResponse res) {
		SimpleReportFiller simpleReportFiller=new SimpleReportFiller();

		URIS uris=new URIS();
		String url=uris.jasperReport+"reporte1.jasper"; 
		
//		try {
//			simpleReportFiller.setReporte(getClass().getResource(url));
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getLocalizedMessage();
//		}
//	    
//	    
	    Map<String, Object> parameters = new HashMap<>();
//	    simpleReportFiller.setParameters(parameters);
//	    
//	    simpleReportFiller.setDataSource(dataSource);
//	    
//	    simpleReportFiller.fillReport();
//	    
//	    SimpleReportExporter simpleExporter = new SimpleReportExporter();
//        simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());
//        
//        simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
//        simpleExporter.exportToPdf("employeeReport.pdf", "baeldung");
//        
		GeneradorReportes g=new GeneradorReportes();
		try{
			
//			g.generarReporte(res, getClass().getResource(url), "xls", parameters, dataSource.getConnection(), "MinameReport", "inline");	
			g.generarReporte(res, getClass().getResource(url), "pdf", parameters, dataSource.getConnection(), "MinameReport", "inline");	

		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
