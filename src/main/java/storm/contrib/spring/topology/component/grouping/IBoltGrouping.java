package storm.contrib.spring.topology.component.grouping;

import backtype.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public interface IBoltGrouping {

    public void addToBolt(final BoltDeclarer boltDeclarer);

}
