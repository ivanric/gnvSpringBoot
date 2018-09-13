//package app.utilidades;
//
////import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class MostrarReporte {//http://www.baeldung.com/spring-jasper
//
//	
//	public void insertar(String nombreReporte,Map<String, Object> parametros){
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(JasperRerportsSimpleConfig.class);
//        ctx.refresh();
//
//        SimpleReportFiller simpleReportFiller = ctx.getBean(SimpleReportFiller.class);
////        SimpleReportFiller simpleReportFiller = new SimpleReportFiller();
//        simpleReportFiller.setReportFileName(nombreReporte);
//        simpleReportFiller.compileReport();
//
//        simpleReportFiller.setReportFileName("employeeReport.jrxml");
//        simpleReportFiller.compileReport();
//
//
//        simpleReportFiller.setParameters(parametros);
//        simpleReportFiller.fillReport();
//	}
//
//	public MostrarReporte() {
////		super();
//		// TODO Auto-generated constructor stub
//	}
//}
