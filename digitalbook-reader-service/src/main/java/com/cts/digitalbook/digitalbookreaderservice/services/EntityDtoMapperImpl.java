package com.cts.digitalbook.digitalbookreaderservice.services;

import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribedBookDetails;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;

@Service
public class EntityDtoMapperImpl implements EntityDtoMapper {
	
	

	@Override
	public SubscribedBookDetails subscriptionBookDetails(SubscriptionEntity subscriptionEntity) {

		SubscribedBookDetails subscribedBookDetails = new SubscribedBookDetails();
			
		subscribedBookDetails.setBookId(subscriptionEntity.getBookId());
		subscribedBookDetails.setReaderId(subscriptionEntity.getReaderId().getReaderId());
		
		

		return subscribedBookDetails;
	}

}
