import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import service.CalculatorLocator;
import soap.SoapAPI;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Feature("SOAP")
public class SOAPCalculatorTest {
    private CalculatorLocator locator = new CalculatorLocator();

    @Test
    @DisplayName("Can add numbers")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    public void canAddNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12().add(1,2);
        Assertions.assertEquals(3, result, "Add operation is broken");
    }

    @Test
    @DisplayName("Can divide numbers")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    public void canDivideNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12().divide(10,5);
        Assertions.assertEquals(2, result, "Divide operation is broken");
    }

    @Test
    @DisplayName("Can multiply numbers")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    public void canMultipleNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12().multiply(10,5);
        Assertions.assertEquals(50, result, "Multiply operation is broken");
    }

    @Test
    @DisplayName("Can add numbers using wrapped soap api")
    public  void canAddNumbersWithWrapperClass(){
        SoapAPI soapAPI = new SoapAPI();
        Assertions.assertEquals(6, soapAPI.add(1,5));
    }
}
