package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowTemplateSimpleFlowTemplate {

  protected String intermediateServerFlowTemplateId;

  protected String name;

  protected String description;

  protected List<FlowTemplateSimpleBlock> blocks;

  public FlowTemplateSimpleFlowTemplate() {
    super();
  }

  public FlowTemplateSimpleFlowTemplate(String intermediateServerFlowTemplateId, String name,
      String description, List<FlowTemplateSimpleBlock> blocks) {
    super();
    this.intermediateServerFlowTemplateId = intermediateServerFlowTemplateId;
    this.name = name;
    this.description = description;
    this.blocks = blocks;
  }

  public String getIntermediateServerFlowTemplateId() {
    return intermediateServerFlowTemplateId;
  }

  public void setIntermediateServerFlowTemplateId(String intermediateServerFlowTemplateId) {
    this.intermediateServerFlowTemplateId = intermediateServerFlowTemplateId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<FlowTemplateSimpleBlock> getBlocks() {
    return blocks;
  }

  public void setBlocks(List<FlowTemplateSimpleBlock> blocks) {
    this.blocks = blocks;
  }

  public static String toString(FlowTemplateSimpleFlowTemplate flux) {
    StringBuffer str = new StringBuffer();

    str.append("FluxID => " + flux.getIntermediateServerFlowTemplateId()).append("\n");
    str.append("Name:  " + flux.getName()).append("\n");
    List<FlowTemplateSimpleBlock> blocks = flux.getBlocks();

    for (FlowTemplateSimpleBlock block : blocks) {
      str.append(" =========== BLOC [" + block.getOrder() + " ] ==============").append("\n");
      str.append(FlowTemplateSimpleBlock.toString(block)).append("\n");
    }

    return str.toString();

  }

}
