package storm.contrib.spring.topology.component.spout;

import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.SpoutDeclarer;
import backtype.storm.topology.TopologyBuilder;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public class RichSpout extends GenericSpout {

    private final IRichSpout stormSpout;

    public RichSpout(final String componentId, final IRichSpout stormSpout) {
        super(componentId);
        this.stormSpout = stormSpout;
    }

    public IRichSpout getStormSpout() {
        return stormSpout;
    }

    public void addToTopology(final TopologyBuilder builder) {
        SpoutDeclarer spoutDeclarer;
        if (parallelismHint == null) {
            spoutDeclarer = builder.setSpout(componentId, stormSpout);
        } else {
            spoutDeclarer = builder.setSpout(componentId, stormSpout, parallelismHint);
        }
        addConfigToComponent(spoutDeclarer);
    }
}
