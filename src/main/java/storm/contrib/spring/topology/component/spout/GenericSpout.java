package storm.contrib.spring.topology.component.spout;

import storm.contrib.spring.topology.component.ComponentConfig;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/5/12
 */
public abstract class GenericSpout extends ComponentConfig implements ISpout {

    protected final String componentId;
    protected Integer parallelismHint;

    protected GenericSpout(final String componentId) {
        this.componentId = componentId;
        this.parallelismHint = null;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setParallelismHint(final Integer parallelismHint) {
        this.parallelismHint = parallelismHint;
    }

    public Integer getParallelismHint() {
        return parallelismHint;
    }

}
