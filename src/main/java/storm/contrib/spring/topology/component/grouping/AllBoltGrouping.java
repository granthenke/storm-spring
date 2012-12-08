package storm.contrib.spring.topology.component.grouping;

import backtype.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class AllBoltGrouping extends GenericBoltGrouping implements IBoltGrouping {

    public AllBoltGrouping(final String componentId, final String streamId) {
        super(componentId, streamId);
    }

    public AllBoltGrouping(final String componentId) {
        super(componentId);
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        if (streamId == null) {
            boltDeclarer.allGrouping(componentId);
        } else {
            boltDeclarer.allGrouping(componentId, streamId);
        }
    }
}
