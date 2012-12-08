package storm.contrib.spring.topology.component.bolt;

import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.TopologyBuilder;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public class BasicBolt extends GenericBolt {

    private IBasicBolt stormBolt;

    public BasicBolt(final String componentId, final IBasicBolt stormBolt) {
        super(componentId);
        this.stormBolt = stormBolt;
    }

    public IBasicBolt getStormBolt() {
        return stormBolt;
    }

    public void addToTopology(final TopologyBuilder builder) {
        BoltDeclarer boltDeclarer;
        if (parallelismHint == null) {
            boltDeclarer = builder.setBolt(componentId, stormBolt);
        } else {
            boltDeclarer = builder.setBolt(componentId, stormBolt, parallelismHint);
        }
        addBoltGroupingsToBolt(boltDeclarer);
        addConfigToComponent(boltDeclarer);
    }
}
