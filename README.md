Storm Spring
=============================================

Storm Spring provides the functionality to build Storm topologies in a Spring XML configuration
and submit them to a storm cluster or run them locally.

To find out more information about Storm see the [Storm Github Repository][4]

This allows you to modify topology bolts, spouts, configurations, and properties without
recompiling.


Creating Topologies
-------------------

Defining topologies using Storm Spring is very similar to defining a topology in the Storm TopologyBuilder api.
To start lets take a look at the ExclamationTopology in the [Storm Starter Project][2].

Using TopologyBuilder a topology is defined in javaas follows:

```java
TopologyBuilder builder = new TopologyBuilder();
builder.setSpout("words", new TestWordSpout(), 10);
builder.setBolt("exclaim1", new ExclamationBolt(), 3)
        .shuffleGrouping("words");
builder.setBolt("exclaim2", new ExclamationBolt(), 2)
        .shuffleGrouping("exclaim1");
```

The same topology wired into Storm Spring is as follows:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-2.5.xsd">

    <!-- Topology Submission -->
    <bean id="exclamationTopologySubmission" class="storm.contrib.spring.topology.SingleTopologySubmission"
          c:topologyId="exclamationTopology" c:stormTopology-ref="exclamationTopology"
          p:config-ref="config"/>

    <!-- Topology Config -->
    <bean id="config" class="backtype.storm.Config" p:debug="false"/>

     <!-- Assembled Topologies -->
    <bean id="exclamationTopology" class="storm.contrib.spring.topology.TopologyFactory"
          c:spout-ref="topologySpouts" c:bolt-ref="topologyBolts"/>
    <util:list id="topologySpouts">
        <bean id="wordSpout" class="storm.contrib.spring.topology.component.spout.RichSpout"
              c:componentId="word" c:stormSpout-ref="testWordSpoutImpl" p:parallelismHint="10"/>
    </util:list>
    <util:list id="topologyBolts">
        <bean id="exclamationBolt1" class="storm.contrib.spring.topology.component.bolt.RichBolt"
              c:componentId="exclaim1" c:stormBolt-ref="exclamationBoltImpl"
              p:boltGroupings-ref="exclamationGrouping1" p:parallelismHint="3"/>
        <bean id="exclamationBolt2" class="storm.contrib.spring.topology.component.bolt.RichBolt"
              c:componentId="exclaim2" c:stormBolt-ref="exclamationBoltImpl"
              p:boltGroupings-ref="exclamationGrouping2" p:parallelismHint="2"/>
    </util:list>

    <!-- Grouping For Each Bolt -->
    <bean id="exclamationGrouping1" class="storm.contrib.spring.topology.component.grouping.ShuffleBoltGrouping"
          c:componentId="word"/>
    <bean id="exclamationGrouping2" class="storm.contrib.spring.topology.component.grouping.ShuffleBoltGrouping"
          c:componentId="exclaim1"/>

    <!-- Bolt And RichSpout Implementations -->
    <bean id="testWordSpoutImpl" class="backtype.storm.testing.TestWordSpout"/>
    <bean id="exclamationBoltImpl" class="storm.sample.bolt.ExclamationBolt"/>
</beans>
```

### Toplogy Components

The Storm Spring has equvalents of all of the standard Storm components for use in spring xml. Below are a few examples which are used in the XML above:

* backtype.storm.topology.TopologyBuilder -> storm.contrib.spring.topology.TopologyFactory
* backtype.storm.topology.IRichSpout -> storm.contrib.spring.topology.component.spout.RichSpout
* backtype.storm.topology.IRichBolt -> storm.contrib.spring.topology.component.bolt.RichBolt

To define inputs to the a Bolt there are classes for all Bolt Groupings in Storm Spring. Below are a few examples:

* BoltDeclarer.ShuffleGrouping() -> storm.contrib.spring.topology.component.grouping.ShuffleBoltGrouping
* BoltDeclarer.FieldsGrouping() -> storm.contrib.spring.topology.component.grouping.FieldsBoltGrouping


Submitting Topologies
---------------------

Once you have created a topology in Spring. There are a few more components in Storm Spring that may be useful when submitting topologies.

### ToplogySubmission

A TopologySubmission is composed of 1 or more topologies and a Storm configuration. An example is shown in the XML above. This bean can be utilized by the
TopologySubmitter discussed below.

### ToplogySubmitter

The ToplogySubmitter is usefull for submitting various topologies and configurations to a storm cluster. To submit to a cluster locally for testing you can use the
LocalToplogySubmitter as well.
Note: LocalToplogySubmitter has a third argument for runtime

To submit a TopologySubmission bean to a storm cluster using ToplogySubmitter follow the following command format:

```
storm jar ${Storm-Jar-With-Dependecies} storm.contrib.spring.topology.TopologySubmitter ${spring-XML-Location} ${TopologySubmission-Bean-Id}
```

A sample command from the Storm Spring Sample Project is:

```
storm jar storm-spring-sample-jar-with-dependencies.jar storm.contrib.spring.topology.TopologySubmitter classpath:/spring-context.xml topologySubmission
```

Utilizing TopologySubmitter you can submit any number of topologies and configurations to storm without neededing to recompile source.


Sample
------

To see an example of how to use Storm Spring to define topologys in Spring XML please see the [Storm Spring Sample Project][3].


Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b my_branch`)
3. Commit your changes (`git commit -am "Made Changes"`)
4. Push to the branch (`git push origin my_branch`)
5. Create an [Issue][1] with a link to your branch

[1]: https://github.com/granthenke/storm-spring/issues
[2]: https://github.com/nathanmarz/storm-starter
[3]: https://github.com/granthenke/storm-spring-sample
[4]: https://github.com/nathanmarz/storm