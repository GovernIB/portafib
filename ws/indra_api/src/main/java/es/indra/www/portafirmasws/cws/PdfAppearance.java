/**
 * PdfAppearance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.indra.www.portafirmasws.cws;
@SuppressWarnings({"rawtypes", "unused"})
public class PdfAppearance  implements java.io.Serializable {
    private es.indra.www.portafirmasws.cws.SignatureImage signatureImage;

    private es.indra.www.portafirmasws.cws.Position[] positions;

    public PdfAppearance() {
    }

    public PdfAppearance(
           es.indra.www.portafirmasws.cws.SignatureImage signatureImage,
           es.indra.www.portafirmasws.cws.Position[] positions) {
           this.signatureImage = signatureImage;
           this.positions = positions;
    }


    /**
     * Gets the signatureImage value for this PdfAppearance.
     * 
     * @return signatureImage
     */
    public es.indra.www.portafirmasws.cws.SignatureImage getSignatureImage() {
        return signatureImage;
    }


    /**
     * Sets the signatureImage value for this PdfAppearance.
     * 
     * @param signatureImage
     */
    public void setSignatureImage(es.indra.www.portafirmasws.cws.SignatureImage signatureImage) {
        this.signatureImage = signatureImage;
    }


    /**
     * Gets the positions value for this PdfAppearance.
     * 
     * @return positions
     */
    public es.indra.www.portafirmasws.cws.Position[] getPositions() {
        return positions;
    }


    /**
     * Sets the positions value for this PdfAppearance.
     * 
     * @param positions
     */
    public void setPositions(es.indra.www.portafirmasws.cws.Position[] positions) {
        this.positions = positions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PdfAppearance)) return false;
        PdfAppearance other = (PdfAppearance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.signatureImage==null && other.getSignatureImage()==null) || 
             (this.signatureImage!=null &&
              this.signatureImage.equals(other.getSignatureImage()))) &&
            ((this.positions==null && other.getPositions()==null) || 
             (this.positions!=null &&
              java.util.Arrays.equals(this.positions, other.getPositions())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSignatureImage() != null) {
            _hashCode += getSignatureImage().hashCode();
        }
        if (getPositions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPositions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPositions(), i);
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
        new org.apache.axis.description.TypeDesc(PdfAppearance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "PdfAppearance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureImage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signature-image"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "SignatureImage"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "positions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "Position"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "positions"));
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
