package com.iris.eatfeat.order.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.iris.eatfeat.order.entity.Sequence;

@Service
public class SequenceGenerator {

	@Autowired
	private MongoOperations mongoOperations;

	public int generateNextOrderId() {

		Query query = Query.query(where("_id").is("sequence"));

		Sequence counter = mongoOperations.findAndModify(query, new Update().inc("sequence", 1),
				options().returnNew(true).upsert(true), Sequence.class);

		return counter.getSequence();
	}

}
