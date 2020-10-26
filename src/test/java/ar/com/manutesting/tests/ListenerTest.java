package ar.com.manutesting.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
	private Logger log = LoggerFactory.getLogger(ListenerTest.class);
	
	@Override		
    public void onFinish(ITestContext contexto) {					
		log.info("Finalizó el grupo de tests "+contexto.getName());	
    }		

    @Override		
    public void onStart(ITestContext contexto) {	
    	log.info("Comenzó el grupo de tests "+contexto.getName());				
    }		
    
    @Override		
    public void onTestStart(ITestResult resultado) {					
    	log.info("Comenzó el test "+resultado.getName());
    }

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult resultado) {	
    	log.info("Finalizó con error pero dentro del porcentaje esperado el test "+resultado.getName());
    }	
    
    @Override		
    public void onTestFailedWithTimeout(ITestResult resultado) {
    	log.info("Finalizó con error por timeout el test "+resultado.getName());
    }	

    @Override		
    public void onTestFailure(ITestResult resultado) {	
    	log.info("Finalizó con error el test "+resultado.getName());
    }		

    @Override		
    public void onTestSkipped(ITestResult resultado) {					
    	log.info("El test "+resultado.getName()+" no se ejecutó.");
    }		

    @Override		
    public void onTestSuccess(ITestResult resultado) {	
    	log.info("Finalizó correctamente el test "+resultado.getName());
    }	
}