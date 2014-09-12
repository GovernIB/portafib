/**
 * UpdateStep.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.indra.www.portafirmasws.cws;
@SuppressWarnings({"rawtypes","unused"})
public class UpdateStep  extends es.indra.www.portafirmasws.cws.Step  implements java.io.Serializable {
    private es.indra.www.portafirmasws.cws.Signer[] signers;

    public UpdateStep() {
    }

    public UpdateStep(
           es.indra.www.portafirmasws.cws.Signer[] signers) {
        this.signers = signers;
    }


    /**
     * Gets the signers value for this UpdateStep.
     * 
     * @return signers
     */
    public es.indra.www.portafirmasws.cws.Signer[] getSigners() {
        return signers;
    }


    /**
     * Sets the signers value for this UpdateStep.
     * 
     * @param signers
     */
    public void setSigners(es.indra.www.portafirmasws.cws.Signer[] signers) {
        this.signers = signers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateStep)) return false;
        UpdateStep other = (UpdateStep) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.signers==null && other.getSigners()==null) || 
             (this.signers!=null &&
              java.util.Arrays.equals(this.signers, other.getSigners())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getSigners() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSigners());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSigners(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateStep.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "UpdateStep"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "Signer"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "signer"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
