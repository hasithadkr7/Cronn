package com.springapp.mvc.model.neo4j;

/**
 * Created by Hasitha on 6/5/2015.
 */

import org.neo4j.graphdb.RelationshipType;

public enum RelationshipCreator implements RelationshipType{
    WORKS_IN, MARRIED_TO;
}