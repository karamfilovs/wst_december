/**
 * Calculator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service;

public interface Calculator extends javax.xml.rpc.Service {
    public java.lang.String getCalculatorSoap12Address();

    public service.CalculatorSoap_PortType getCalculatorSoap12() throws javax.xml.rpc.ServiceException;

    public service.CalculatorSoap_PortType getCalculatorSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getCalculatorSoapAddress();

    public service.CalculatorSoap_PortType getCalculatorSoap() throws javax.xml.rpc.ServiceException;

    public service.CalculatorSoap_PortType getCalculatorSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
