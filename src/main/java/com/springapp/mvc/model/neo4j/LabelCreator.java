package com.springapp.mvc.model.neo4j;

/**
 * Created by Hasitha on 6/5/2015.
 */

import org.neo4j.graphdb.Label;

public enum LabelCreator implements Label {
    Customer,Account,Corporate;
}