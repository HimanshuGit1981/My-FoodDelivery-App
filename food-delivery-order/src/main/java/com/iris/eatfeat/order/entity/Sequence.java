package com.iris.eatfeat.order.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {

	@Id
	private String _id;
	
	private int sequence;
}
