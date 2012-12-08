package storm.contrib.spring.topology.component.grouping;

import backtype.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class GlobalBoltGrouping extends GenericBoltGrouping implements IBoltGrouping {

    public GlobalBoltGrouping(final String componentId, final String streamId) {
        super(componentId, streamId);
    }

    public GlobalBoltGrouping(final String componentId) {
        super(componentId);
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        if (streamId == null) {
            boltDeclarer.globalGrouping(componentId);
        } else {
            boltDeclarer.globalGrouping(componentId, streamId);
        }
    }
}
