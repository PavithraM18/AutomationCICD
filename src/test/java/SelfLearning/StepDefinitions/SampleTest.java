package SelfLearning.StepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import SelfLearning.TestComponents.BaseTest;

public class SampleTest {

	public static void main(String[] args) throws IOException {
		BaseTest bs =new BaseTest();
			
		//List<HashMap<String, String>> data = bs.getJsonDataToMap("C:\\Users\\pavithra.mathivanan\\OneDrive - InnovaSolutions\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\SelfLearning\\data\\PurchaseOrder.json");
		List<HashMap<String, String>> data1 = bs.getJsonDataToMap("F:\\Auto\\PurchaseOrder.json");
		System.out.println(data1.get(1));
		
		
		//data.get(0);
		//System.out.println(data.get(0).);

	}

}
