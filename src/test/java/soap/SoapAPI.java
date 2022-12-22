package soap;

import service.CalculatorLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class SoapAPI {
    private CalculatorLocator locator = new CalculatorLocator();

   //To generate Java classes from WSDL use the command: mvn axistools:wsdl2java

    public int add(int a, int b){
        int sum = 0;
        try {
           sum = locator.getCalculatorSoap12().add(a, b);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
