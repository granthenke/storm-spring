package storm.contrib.spring.topology.component.bolt;

import backtype.storm.topology.IComponent;
import backtype.storm.topology.TopologyBuilder;
import storm.contrib.spring.topology.component.IComponentConfig;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/5/12
 */
public interface IBolt<T extends IComponent> extends IComponentConfig {

    public String getComponentId();

    public T getStormBolt();

    public void addToTopology(final TopologyBuilder builder);

}
