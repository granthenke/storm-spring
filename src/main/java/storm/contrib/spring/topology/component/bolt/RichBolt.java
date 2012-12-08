package storm.contrib.spring.topology.component.bolt;

import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.TopologyBuilder;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/5/12
 */
public class RichBolt extends GenericBolt {

    private IRichBolt stormBolt;

    public RichBolt(final String componentId, final IRichBolt stormBolt) {
        super(componentId);
        this.stormBolt = stormBolt;
    }

    public IRichBolt getStormBolt() {
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
