//package com.kodlamaio.rentACar.core.utilities.aspects;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Aspect
//@Component
//
//public class LoggingAspect {
//
//	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))") // önce kullanacağım // için before ..
//																				// bütün metodlarda çalışması için
//	public void beforeLog(JoinPoint joinPoint) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//			StringBuilder stringBuilder = new StringBuilder();
//
//			stringBuilder.append("\n{");
//			stringBuilder.append("\n\"date\":" + mapper.writeValueAsString(LocalDate.now().toString()));
//			stringBuilder.append(
//					"\n\"className\":" + mapper.writeValueAsString(joinPoint.getTarget().getClass().getSimpleName()));
//			stringBuilder.append("\n\"methodName\":" + signature.getMethod().getName());
//
//			if (signature.getMethod().getName() != "getAll") {
//				stringBuilder.append("\n\"parameters\":" + mapper.writeValueAsString(joinPoint.getArgs()));
//
//			} else {
//				stringBuilder.append("\"parameters\":" + "none");
//
//			}
//			
//			
//			
//			
//			
//			stringBuilder.append("\n}");
//			File file = new File("C:\\logs\\operations.json");
//
//			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
//				bufferedWriter.write(stringBuilder.toString());
//
//			} catch (IOException e) {
//				System.out.println("Unable to read file " + file.toString());
//			}
//
//		} catch (Throwable e) {
//
//			e.printStackTrace();
//		}
//		;
//	}
//}

//	stringBuilder.append("{ " + "\n");
//	
//	stringBuilder.append("," + "\n " + "}");

//	
/*
 * @Before("execution (*com.kodlamaio.rentACar.business.concretes.BrandManager.deleteById(int)"
 * ) // önce kullanacağım // için before public void beforeLog(JoinPoint
 * joinPoint) { // Advice MethodSignature signature = (MethodSignature)
 * joinPoint.getSignature();
 * 
 * System.out.println("before brand manager deleteById");
 * //System.out.println(joinPoint.getArgs()[0]); //ilk parametresi
 * //System.out.println(joinPoint.getSignature()); // metod imzası gösterir
 * System.out.println(joinPoint.FIELD_GET);
 * System.out.println(signature.getParameterNames()[0]);
 * 
 * }
 * 
 */

/*
 * 
 * @Before("public void pointcut() {}") //önce kullanacağım için public void
 * beforeLog() { // Advice
 * 
 * System.out.println("before brand manager delete");
 * 
 * }
 * 
 * @After("public void pointcut() {}") //önce kullanacağım için public void
 * afterLog() { // Advice
 * 
 * System.out.println("before brand manager delete");
 * 
 * }
 * 
 * @Pointcut("execution (* com.kodlamaio.rentACar.business.concretes.BrandManager.deleteById(int)"
 * ) public void pointcut() {} //damy metodu
 * 
 */

//}
//
